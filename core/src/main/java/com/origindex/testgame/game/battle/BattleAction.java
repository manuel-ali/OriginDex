package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActivePokemon;

public abstract class BattleAction {
    private ActivePokemon user;
    private int priority;

    public BattleAction(ActivePokemon user, int priority) {
        this.user = user;
        this.priority = priority;
    }

    public ActivePokemon getUser() {
        return user;
    }

    public void setUser(ActivePokemon user) {
        this.user = user;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public abstract TurnResult execute(BattleLogic battleLogic);
}
