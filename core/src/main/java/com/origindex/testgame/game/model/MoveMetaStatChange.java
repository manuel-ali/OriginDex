package com.origindex.testgame.game.model;

public class MoveMetaStatChange {
    private Stat stat;
    private int change;

    public MoveMetaStatChange(Stat stat, int change) {
        this.stat = stat;
        this.change = change;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    @Override
    public String toString() {
        return "MoveMetaStatChange{" +
            "stat=" + stat +
            ", change=" + change +
            '}';
    }
}
