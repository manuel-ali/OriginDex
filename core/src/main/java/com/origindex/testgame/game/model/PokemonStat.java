package com.origindex.testgame.game.model;

public class PokemonStat {
    private Stat stat;
    private int baseStat;
    private int effort;

    public PokemonStat(Stat stat, int baseStat, int effort) {
        this.stat = stat;
        this.baseStat = baseStat;
        this.effort = effort;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public int getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(int baseStat) {
        this.baseStat = baseStat;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    @Override
    public String toString() {
        return "PokemonStat{" +
            "stat=" + stat +
            ", baseStat=" + baseStat +
            ", effort=" + effort +
            '}';
    }
}
