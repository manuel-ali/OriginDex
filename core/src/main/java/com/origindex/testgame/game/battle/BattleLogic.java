package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.battle.handlers.StatusMoveHandler;
import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

import java.util.List;
import java.util.Random;

public class BattleLogic {
    private final Random random = new Random();
    private Battle battle;

    /**
     * Method that handles the battle turn
     */
    public TurnResult executeAttack(AttackAction action) {
        ActivePokemon user = action.getUser();
        ActivePokemon enemy = getEnemy(user);
        ActiveMove move = action.getActiveMove();

        TurnResult noPPsResult = handleNoPPsResult(move);
        if (noPPsResult != null){
            return noPPsResult;
        }

        decrementPp(move);

        TurnResult missResult = handleMissResult(user, enemy, move);
        if (missResult != null){
            return missResult;
        }

        ActivePokemon target = handleMoveTarget(user, enemy, move);

        TurnResult statusResult = handleStatusMoveResult(user, target, move);
        if (statusResult != null){
            return statusResult;
        }

        double effectivenessModifier = TypeEffectivenessResolver.getTypeEffectivenessModifier(target, move);

        if (effectivenessModifier == 0){
            return new NoEffectResult(user, target, effectivenessModifier);
        }

        double stabModifier = DamageModifierCalculator.getStabModifier(user, move);
        double totalModifier = DamageModifierCalculator.calculateTotalDamageModifier(stabModifier, effectivenessModifier);

        int damage = DamageCalculator.calculateDamage(user, move, target, totalModifier);

        AttackResolver.resolvePokemonAttack(target, move, damage);

        TurnResult faintedResult = handleFaintedResult(user, target, move, effectivenessModifier);
        if (faintedResult != null){
            return faintedResult;
        }

        BattleStep.Target moveUser = user.isPlayer() ? BattleStep.Target.PLAYER : BattleStep.Target.ENEMY;
        BattleStep.Target moveReceiver = target.isPlayer() ? BattleStep.Target.PLAYER : BattleStep.Target.ENEMY;

        return new DamageResult(user, target, move, damage, effectivenessModifier, moveUser, moveReceiver);
    }

    private ActivePokemon getEnemy(ActivePokemon user){
        return battle.getEnemy(user);
    }

    public List<BattleAction> determineTurnOrder(BattleAction a1, BattleAction a2) {
        int a1Priority = a1.getPriority();
        int a2Priority = a2.getPriority();

        if (a1Priority > a2Priority){
            return List.of(a1, a2);
        }

        if (a1Priority < a2Priority){
            return List.of(a2, a1);
        }

        int a1Speed = a1.getUser().getModifiedSpeed();
        int a2Speed = a2.getUser().getModifiedSpeed();

        if (a1Speed > a2Speed){
            return List.of(a1, a2);
        }

        if (a1Speed < a2Speed){
            return List.of(a2, a1);
        }

        if (random.nextBoolean()){
            return List.of(a1, a2);
        } else {
            return List.of(a2, a1);
        }
    }

    /**
     * Method that handles to subtract PP to the used Move.
     * @param move The used Move.
     */
    private static void decrementPp(ActiveMove move){
        int newPP = Math.max(move.getCurrentPP() - 1, 0);
        move.setCurrentPP(newPP);
    }

    private TurnResult handleNoPPsResult(ActiveMove move) {
        if (!MoveResolver.hasValidPP(move)){
            return new NoPPsResult(move);
        }

        return null;
    }

    private TurnResult handleFaintedResult(ActivePokemon user, ActivePokemon enemy, ActiveMove move, double effectivenessModifier){
        boolean userFainted = user.isFainted();
        boolean enemyFainted = enemy.isFainted();

        if (userFainted || enemyFainted) {
            BattleStep.Target moveUser = user.isPlayer() ? BattleStep.Target.PLAYER : BattleStep.Target.ENEMY;
            BattleStep.Target moveReceiver = enemy.isPlayer() ? BattleStep.Target.PLAYER : BattleStep.Target.ENEMY;

            return new FaintedResult(user, enemy, move, moveUser, moveReceiver, effectivenessModifier);
        }

        return null;
    }

    private TurnResult handleMissResult(ActivePokemon attacker, ActivePokemon defender, ActiveMove move){
        boolean isHitting = AccuracyResolver.resolveAccuracy(attacker, defender, move);

        if (!isHitting){
            return new MissResult(attacker, move);
        }

        return null;
    }

    private ActivePokemon handleMoveTarget(ActivePokemon attacker, ActivePokemon defender, ActiveMove move){
        String target = move.getMove().getTarget().getIdentifier();

        return MoveTargetResolver.resolveMoveTarget(attacker, defender, target);
    }

    private TurnResult handleStatusMoveResult(ActivePokemon attacker, ActivePokemon target, ActiveMove move){
        boolean isStatusMove = move.getMove().isStatusMove();

        if (isStatusMove){
            StatusMoveHandler.handleStatusMove(target, move);
            return new StatusResult(attacker, target, move);
        }

        return null;
    }

    public void startBattle() {
        battle.initBattle();
    }

    public BattleLogic(Battle battle) {
        this.battle = battle;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }
}
