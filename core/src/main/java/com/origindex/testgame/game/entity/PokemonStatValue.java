package com.origindex.testgame.game.entity;

import com.origindex.testgame.game.model.Stat;

public class PokemonStatValue {
    private Stat stat;
    private int baseStat;
    private int iv;
    private int ev;
    private int finalStat;

    public PokemonStatValue(Stat stat, int baseStat, int level) {
        this.stat = stat;
        this.baseStat = baseStat;
        this.iv = 31;
        this.ev = 0;
        this.finalStat = calculateStat(stat.getIdentifier(), baseStat, iv, ev, level);
    }

    private int calculateStat(String statName, int baseStat, int iv, int ev, int level) {
        if (statName.equals("hp")) {
            return ((2 * baseStat + iv + (ev / 4)) * level) / 100 + level + 10;
        } else {
            return ((2 * baseStat + iv + (ev / 4)) * level) / 100 + 5;
        }
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

    public int getIv() {
        return iv;
    }

    public void setIv(int iv) {
        this.iv = iv;
    }

    public int getEv() {
        return ev;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }

    public int getFinalStat() {
        return finalStat;
    }

    public void setFinalStat(int finalStat) {
        this.finalStat = finalStat;
    }

    @Override
    public String toString() {
        return "PokemonStatValue{" +
            "stat=" + stat +
            ", baseStat=" + baseStat +
            ", iv=" + iv +
            ", ev=" + ev +
            ", finalStat=" + finalStat +
            '}';
    }
}
