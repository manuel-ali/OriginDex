package com.origindex.testgame.game.entity;

import com.origindex.testgame.game.model.Move;
import com.origindex.testgame.game.model.Pokemon;

import java.util.List;

public class ActivePokemon {
    private Pokemon specie;
    private String nickname;
    private int level;
    private int currentHP;
    private int actualXP;
    private int nextLevelXP;
    private List<PokemonStatValue> stats;
    private List<Move> learnedMoves;
    private boolean isFainted;

    public ActivePokemon(Pokemon specie, String nickname, int level, int currentHP, int actualXP, int nextLevelXP,
                         List<PokemonStatValue> stats, List<Move> learnedMoves, boolean isFainted) {
        this.specie = specie;
        this.nickname = nickname;
        this.level = level;
        this.currentHP = currentHP;
        this.actualXP = actualXP;
        this.nextLevelXP = nextLevelXP;
        this.stats = stats;
        this.learnedMoves = learnedMoves;
        this.isFainted = isFainted;
    }

    public Pokemon getSpecie() {
        return specie;
    }

    public void setSpecie(Pokemon specie) {
        this.specie = specie;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getActualXP() {
        return actualXP;
    }

    public void setActualXP(int actualXP) {
        this.actualXP = actualXP;
    }

    public int getNextLevelXP() {
        return nextLevelXP;
    }

    public void setNextLevelXP(int nextLevelXP) {
        this.nextLevelXP = nextLevelXP;
    }

    public List<PokemonStatValue> getStats() {
        return stats;
    }

    public void setStats(List<PokemonStatValue> stats) {
        this.stats = stats;
    }

    public List<Move> getLearnedMoves() {
        return learnedMoves;
    }

    public void setLearnedMoves(List<Move> learnedMoves) {
        this.learnedMoves = learnedMoves;
    }

    public boolean isFainted() {
        return isFainted;
    }

    public void setFainted(boolean fainted) {
        isFainted = fainted;
    }

    @Override
    public String toString() {
        return "ActivePokemon{" +
            "specie=" + specie +
            ", nickname='" + nickname + '\'' +
            ", level=" + level +
            ", currentHP=" + currentHP +
            ", actualXP=" + actualXP +
            ", nextLevelXP=" + nextLevelXP +
            ", stats=" + stats +
            ", learnedMoves=" + learnedMoves +
            ", isFainted=" + isFainted +
            '}';
    }
}
