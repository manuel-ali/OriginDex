package com.origindex.testgame.csvimport.csvmodels.pokemon;

public class GrowthRateCSV {
    private final int id;
    private final String identifier; //Nombre del tipo de crecimiento (lento, medio, etc.)

    public GrowthRateCSV(int id, String identifier) {
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
        return "GrowthRateCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
