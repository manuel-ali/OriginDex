package com.origindex.testgame.csvimport.csvmodels.moves;

public class MoveDamageClassCSV {
    private final int id;
    private final String identifier; //Nombre del tipo de daño (físico, especial, estado)

    public MoveDamageClassCSV(int id, String identifier) {
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
        return "MoveDamageClassesCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
