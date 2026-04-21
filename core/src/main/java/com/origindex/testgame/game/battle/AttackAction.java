package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

public class AttackAction extends BattleAction{
    private final ActiveMove activeMove;

    public AttackAction(ActivePokemon user, ActiveMove activeMove) {
        super(user, activeMove.getMove().getPriority());
        this.activeMove = activeMove;
    }

    public ActiveMove getActiveMove() {
        return activeMove;
    }

    @Override
    public TurnResult execute(BattleLogic battleLogic) {
        return battleLogic.executeAttack(this);
    }
}
