package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;
import com.origindex.testgame.view.battle.BattleListener;
import com.origindex.testgame.view.battle.PokemonData;
import com.origindex.testgame.view.battle.sprites.MoveData;

import java.util.*;

public class BattleController {
    private final BattleLogic BATTLELOGIC;
    private Battle battle;
    private BattleListener listener;

    public BattleController(BattleLogic battleLogic, Battle battle) {
        this.BATTLELOGIC = battleLogic;
        this.battle = battle;
    }

    public void startBattle() {
        BATTLELOGIC.startBattle();
    }

    public void onBattleEnded() {
        if (listener != null) {
            listener.onBattleEnded();
        }
    }

    public void onTurnFinished() {
        if (listener != null) {
            if (battle.isFinished()){
                onBattleEnded();
                return;
            }
            List<ActiveMove> activeMoves = battle.getPokemonPlayer().getLearnedMoves();
            List<MoveData> moveData = getMoveData(activeMoves);
            listener.onShowMoves(moveData);
            listener.onShowMessage(BattleText.getTurnStartMessage(battle.getPokemonPlayer()));
        }
    }

    public void onFightButtonSelected() {
        List<ActiveMove> activeMoves = battle.getPokemonPlayer().getLearnedMoves();
        List<MoveData> moveData = getMoveData(activeMoves);
        if (listener != null) listener.onShowMoves(moveData);
    }

    public void onBagButtonSelected() {
        if (listener != null) listener.onShowMessage("Bag is not available");
    }

    public void onPokemonButtonSelected() {
        if (listener != null) listener.onShowMessage("Pokemon is not available");
    }

    public void onRunButtonSelected() {
        if (listener != null) listener.onShowMessage("Run is not available");
    }

    public void onPlayerMoveSelected(MoveData moveData) {
        ActiveMove playerMove = getActiveMoveFromMoveData(moveData);
        ActiveMove enemyMove = battle.getPokemonEnemy().getLearnedMoves().get((int) (Math.random() * 4)); //Test random move selection for enemy, should be improved later
        BattleAction playerAction = new AttackAction(battle.getPokemonPlayer(), playerMove);
        BattleAction enemyAction = new AttackAction(battle.getPokemonEnemy(), enemyMove);
        List<BattleAction> actions = BATTLELOGIC.determineTurnOrder(playerAction, enemyAction);

        List<TurnResult> turnResults = new ArrayList<>();

        for (BattleAction action : actions) {
            turnResults.add(action.execute(BATTLELOGIC));
        }

        Queue<BattleStep> steps = new LinkedList<>();

        for (TurnResult result : turnResults) {
            switch (result){
                case NoPPsResult noPPsResult -> {
                    steps.add(handleNoPPsResult(noPPsResult));
                }
                case MissResult missResult -> {
                    steps.add(handleMissResult(missResult));
                }
                case StatusResult statusResult -> {
                    steps.addAll(handleStatusResult(statusResult));
                }
                case NoEffectResult noEffectResult -> {
                    steps.add(handleNoEffectResult(noEffectResult));
                }
                case FaintedResult faintedResult -> {
                    steps.addAll(handleFaintedResult(faintedResult));
                }
                case DamageResult damageResult -> {
                    steps.addAll(handleDamageResult(damageResult));
                }
            }
        }

       listener.executeSteps(steps);
    }

    private BattleStep handleNoPPsResult(NoPPsResult noPPsResult) {
        String s = BattleText.getPPMessage(noPPsResult.activeMove());

        return BattleStep.message(s);
    }

    private BattleStep handleMissResult(MissResult missResult) {
        String s = BattleText.getMissMessage(missResult.attacker(), missResult.activeMove());

        return BattleStep.message(s);
    }

    private List<BattleStep> handleStatusResult(StatusResult statusResult) {
        List<BattleStep> steps = new ArrayList<>();

        String attackerNickname = statusResult.attacker().getNickname();
        String moveIdentifier = statusResult.activeMove().getMove().getIdentifier();
        BattleStep.Target target = statusResult.target().isPlayer() ? BattleStep.Target.PLAYER : BattleStep.Target.ENEMY;

        String s = BattleText.getChosenMoveMessage(attackerNickname, moveIdentifier, target);
        steps.add(BattleStep.message(s));

        steps.add(BattleStep.animation(BattleStep.AnimationType.STATUS, target));

        return steps;
    }

    private BattleStep handleNoEffectResult(NoEffectResult noEffectResult) {
        String s = BattleText.getEffectivenessMessage(noEffectResult.effectivenessModifier());

        return BattleStep.message(s);
    }

    private List<BattleStep> handleFaintedResult(FaintedResult faintedResult) {
        List<BattleStep> steps = new ArrayList<>();

        String attackerNickname = faintedResult.user().getNickname();
        String moveIdentifier = faintedResult.activeMove().getMove().getIdentifier();

        BattleStep.Target moveUser = faintedResult.moveUser();

        String s = BattleText.getChosenMoveMessage(attackerNickname, moveIdentifier, moveUser);
        steps.add(BattleStep.message(s));

        if (faintedResult.enemy().isFainted()){
            BattleStep.Target moveReceiver = faintedResult.moveReceiver();
            steps.add(BattleStep.animation(BattleStep.AnimationType.FAINT, moveReceiver));

            String enemyNickname = faintedResult.enemy().getNickname();

            s = BattleText.getFaintedMessage(moveReceiver, enemyNickname);
            steps.add(BattleStep.message(s));
        }

        s = BattleText.getEffectivenessMessage(faintedResult.effectivenessModifier());
        if (s != null){
            steps.add(BattleStep.message(s));
        }

        battle.setFinished(true);

        return steps;
    }

    private List<BattleStep> handleDamageResult(DamageResult damageResult) {
        List<BattleStep> steps = new ArrayList<>();

        String attackerNickname = damageResult.user().getNickname();
        String moveIdentifier = damageResult.activeMove().getMove().getIdentifier();

        BattleStep.Target moveUser = damageResult.moveUser();

        String s = BattleText.getChosenMoveMessage(attackerNickname, moveIdentifier, moveUser);
        steps.add(BattleStep.message(s));

        BattleStep.Target target = damageResult.moveReceiver();
        steps.add(BattleStep.animation(BattleStep.AnimationType.HIT, target));

        s = BattleText.getEffectivenessMessage(damageResult.effectivenessModifier());
        if (s != null){
            steps.add(BattleStep.message(s));
        }

        PokemonData pokemonData = getPokemonData(damageResult.enemy());
        steps.add(BattleStep.update(target, pokemonData));

        return steps;
    }

    public PokemonData getPlayerData(){
        ActivePokemon player = BATTLELOGIC.getBattle().getPokemonPlayer();
        return getPokemonData(player);
    }

    public PokemonData getEnemyData(){
        ActivePokemon enemy = BATTLELOGIC.getBattle().getPokemonEnemy();
        return getPokemonData(enemy);
    }

    private PokemonData getPokemonData(ActivePokemon activePokemon) {
        int id = activePokemon.getSpecie().getId();
        String nickname = activePokemon.getNickname();
        int level = activePokemon.getLevel();
        int currentHP = activePokemon.getCurrentHP();
        int maxHp = activePokemon.getMaxHP();
        int nextLevelXp = activePokemon.getNextLevelXp();
        int currentXp = activePokemon.getCurrentXp();
        List<MoveData> moveData = getMoveData(activePokemon.getLearnedMoves());

        return new PokemonData(id, nickname, level, currentHP, maxHp, nextLevelXp, currentXp, moveData);
    }

    private List<MoveData> getMoveData(List<ActiveMove> activeMoves) {
        List<MoveData> moveData = new ArrayList<>();

        for (int slot = 0; slot < activeMoves.size(); slot++) {
            ActiveMove move = activeMoves.get(slot);

            int id = move.getMove().getId();
            String identifier = move.getMove().getIdentifier();
            int currentPp = move.getCurrentPP();
            int maxPp = move.getMove().getPp();

            moveData.add(new MoveData(id, identifier, currentPp, maxPp, slot));
        }

        return moveData;
    }

    private ActiveMove getActiveMoveFromMoveData(MoveData moveData){
        return battle.getPokemonPlayer().getLearnedMoves().get(moveData.slot());
    }

    public void setListener(BattleListener listener) {
        this.listener = listener;
    }
}
