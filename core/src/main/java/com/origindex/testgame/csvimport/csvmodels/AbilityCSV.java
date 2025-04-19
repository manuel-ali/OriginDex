package com.origindex.testgame.csvimport.csvmodels;

//Clase de importación de abilities.csv para la base de datos
public class AbilityCSV {
    private int id;
    private String identifier;
    private int generationId;
    private boolean isMainSeries;

    public AbilityCSV(int id, String identifier, int generationId, boolean isMainSeries) {
        this.id = id;
        this.identifier = identifier;
        this.generationId = generationId;
        this.isMainSeries = isMainSeries;
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

    public int getGenerationId() {
        return generationId;
    }

    public void setGenerationId(int generationId) {
        this.generationId = generationId;
    }

    public boolean isMainSeries() {
        return isMainSeries;
    }

    public void setMainSeries(boolean mainSeries) {
        isMainSeries = mainSeries;
    }

    @Override
    public String toString() {
        return "AbilityCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", generationId=" + generationId +
            ", isMainSeries=" + isMainSeries +
            '}';
    }
}
