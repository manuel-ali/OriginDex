package com.origindex.testgame.csvimport.csvmodels.moves;

public class MoveEffectCSV {
    private final int id;

    public MoveEffectCSV(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MoveEffects{" +
            "id=" + id +
            '}';
    }
}
