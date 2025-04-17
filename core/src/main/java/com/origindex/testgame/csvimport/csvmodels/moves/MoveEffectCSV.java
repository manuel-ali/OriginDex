package com.origindex.testgame.csvimport.csvmodels.moves;

public class MoveEffectCSV {
    private int id;

    public MoveEffectCSV(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MoveEffects{" +
            "id=" + id +
            '}';
    }
}
