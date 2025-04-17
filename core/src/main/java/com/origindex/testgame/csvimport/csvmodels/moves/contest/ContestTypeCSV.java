package com.origindex.testgame.csvimport.csvmodels.moves.contest;

public class ContestTypeCSV {
    private int id;
    private String identifier; //Categoria del movimiento en concurso Pokemon

    public ContestTypeCSV(int id, String identifier) {
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
        return "ContestTypesCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
