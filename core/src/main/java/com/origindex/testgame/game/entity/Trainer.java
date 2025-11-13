package com.origindex.testgame.game.entity;

public abstract class Trainer {
    protected ActivePokemon activePokemon;

    public ActivePokemon getActivePokemon() {
        return activePokemon;
    }

    public void setActivePokemon(ActivePokemon activePokemon) {
        this.activePokemon = activePokemon;
    }
}
