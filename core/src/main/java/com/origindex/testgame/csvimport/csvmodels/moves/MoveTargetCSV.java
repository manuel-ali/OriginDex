package com.origindex.testgame.csvimport.csvmodels.moves;

public class MoveTargetCSV {
    private int id;
    private String identifier; //Objetivo del movimiento (Enemigo, aliado, todos, etc)

    public MoveTargetCSV(int id, String identifier) {
        this.id = id;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "MoveTargetsCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
