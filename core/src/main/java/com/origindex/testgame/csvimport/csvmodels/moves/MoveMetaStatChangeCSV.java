package com.origindex.testgame.csvimport.csvmodels.moves;

public class MoveMetaStatChangeCSV {
    private final int moveId, statId, change;

    public MoveMetaStatChangeCSV(int moveId, int statId, int change) {
        this.moveId = moveId;
        this.statId = statId;
        this.change = change;
    }

    public int getMoveId() {
        return moveId;
    }

    public int getStatId() {
        return statId;
    }

    public int getChange() {
        return change;
    }

    @Override
    public String toString() {
        return "MoveMetaStatChangeCSV{" +
            "moveId=" + moveId +
            ", statId=" + statId +
            ", change=" + change +
            '}';
    }
}
