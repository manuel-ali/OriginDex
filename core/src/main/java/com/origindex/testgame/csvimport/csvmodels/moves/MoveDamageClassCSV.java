package com.origindex.testgame.csvimport.csvmodels.moves;

public class MoveDamageClassCSV {
    private int id;
    private String identifier; //Nombre del tipo de daño (físico, especial, estado)

    public MoveDamageClassCSV(int id, String identifier) {
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
        return "MoveDamageClassesCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
