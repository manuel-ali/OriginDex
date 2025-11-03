package com.origindex.testgame.game.logic.battle;

import com.origindex.testgame.game.entity.ActivePokemon;

public class Battle {
    private ActivePokemon pokemonPlayer;
    private ActivePokemon pokemonEnemy;
    private int turn;
    private boolean isFinished;

    public void nextTurn() {
        this.turn++;
    }

    public Battle(ActivePokemon pokemonPlayer, ActivePokemon pokemonEnemy) {
        this.pokemonPlayer = pokemonPlayer;
        this.pokemonEnemy = pokemonEnemy;
        this.turn = 1;
        this.isFinished = false;
    }

    public ActivePokemon getPokemonPlayer() {
        return pokemonPlayer;
    }

    public void setPokemonPlayer(ActivePokemon pokemonPlayer) {
        this.pokemonPlayer = pokemonPlayer;
    }

    public ActivePokemon getPokemonEnemy() {
        return pokemonEnemy;
    }

    public void setPokemonEnemy(ActivePokemon pokemonEnemy) {
        this.pokemonEnemy = pokemonEnemy;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
