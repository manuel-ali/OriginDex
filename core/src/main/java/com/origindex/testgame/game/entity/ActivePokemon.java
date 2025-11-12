package com.origindex.testgame.game.entity;

import com.origindex.testgame.game.model.Pokemon;
import com.origindex.testgame.game.model.PokemonStat;

import java.util.ArrayList;
import java.util.List;

public class ActivePokemon {
    private Pokemon specie;
    private String nickname;
    private int level;
    private int currentHP;
    private int maxHP;
    private int actualXP;
    private int nextLevelXP;
    private List<PokemonStatValue> stats;
    private List<ActiveMove> learnedMoves;
    private boolean isFainted;

    private List<PokemonStatValue> generateFinalStatsFromBase(){
        List<PokemonStat> baseStats = specie.getStats();
        List<PokemonStatValue> finalStats = new ArrayList<>();

        for (PokemonStat stat : baseStats) {
            PokemonStatValue pokemonStatValue = new PokemonStatValue(stat.getStat(), stat.getBaseStat(), level);
            finalStats.add(pokemonStatValue);
        }

        return finalStats;
    }

    private int getHPStat(){
        for (PokemonStatValue stat : stats) {
            if (stat.getStat().getIdentifier().equals("hp")) {
                return stat.getFinalStat();
            }
        }
        return 1; // Devuelvo 1 si no encuentro el stat de HP
    }

    public int getAttackStat(){
        for (PokemonStatValue stat : stats) {
            if (stat.getStat().getIdentifier().equals("attack")) {
                return stat.getFinalStat();
            }
        }
        return 1; // Devuelvo 1 si no encuentro el stat de Attack
    }

    public int getDefenseStat(){
        for (PokemonStatValue stat : stats) {
            if (stat.getStat().getIdentifier().equals("defense")) {
                return stat.getFinalStat();
            }
        }
        return 1; // Devuelvo 1 si no encuentro el stat de Defense
    }

    public int getSpecialAttackStat() {
        for (PokemonStatValue stat : stats) {
            if (stat.getStat().getIdentifier().equals("special-attack")) {
                return stat.getFinalStat();
            }
        }
        return 1; // Devuelvo 1 si no encuentro el stat de Special Attack
    }

    public int getSpecialDefenseStat() {
        for (PokemonStatValue stat : stats) {
            if (stat.getStat().getIdentifier().equals("special-defense")) {
                return stat.getFinalStat();
            }
        }
        return 1; // Devuelvo 1 si no encuentro el stat de Special Defense
    }

    public int getSpeedStat(){
        for (PokemonStatValue stat : stats) {
            if (stat.getStat().getIdentifier().equals("speed")) {
                return stat.getFinalStat();
            }
        }
        return 1; // Devuelvo 1 si no encuentro el stat de Speed
    }

    public int getAccuracy(){
        for (PokemonStatValue stat : stats) {
            if (stat.getStat().getIdentifier().equals("accuracy")) {
                return stat.getFinalStat();
            }
        }
        return 0; // Devuelvo 0 si no encuentro el stat de Accuracy
    }

    public int getEvasion(){
        for (PokemonStatValue stat : stats) {
            if (stat.getStat().getIdentifier().equals("evasion")) {
                return stat.getFinalStat();
            }
        }
        return 0; // Devuelvo 0 si no encuentro el stat de Evasion
    }

    public ActivePokemon(Pokemon specie, String nickname, int level, int actualXP, int nextLevelXP,
                         List<ActiveMove> learnedMoves) {
        this.specie = specie;
        this.nickname = nickname;
        this.level = level;
        this.actualXP = actualXP;
        this.nextLevelXP = nextLevelXP;
        this.learnedMoves = learnedMoves;
        this.stats = generateFinalStatsFromBase();
        this.currentHP = getHPStat();
        this.maxHP = getHPStat();
        this.isFainted = false;
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

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
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

    public List<ActiveMove> getLearnedMoves() {
        return learnedMoves;
    }

    public void setLearnedMoves(List<ActiveMove> learnedMoves) {
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
