package com.origindex.testgame.csvimport.csvmodels.moves;

public class MoveTargetCSV {
    private final int id;
    private final String identifier; //Objetivo del movimiento (Enemigo, aliado, todos, etc.)

    public MoveTargetCSV(int id, String identifier) {
        this.id = id;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "MoveTargetsCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
