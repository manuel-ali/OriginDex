package com.origindex.testgame.csvimport.csvmodels.pokemon;

public class GrowthRateCSV {
    private int id;
    private String identifier; //Nombre de la tasa de crecimiento (lento, medio, etc)

    public GrowthRateCSV(int id, String identifier) {
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
        return "GrowthRateCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
