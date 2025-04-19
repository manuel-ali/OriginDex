package com.origindex.testgame.csvimport.csvmodels;

public class GenerationCSV {
    private int id;
    private int mainRegionId; //Id de la region principal de la generación
    private String identifier; //Nombre de la generación

    public GenerationCSV(int id, int mainRegionId, String identifier) {
        this.id = id;
        this.mainRegionId = mainRegionId;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMainRegionId() {
        return mainRegionId;
    }

    public void setMainRegionId(int mainRegionId) {
        this.mainRegionId = mainRegionId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "GenerationsCSV{" +
            "id=" + id +
            ", mainRegionId=" + mainRegionId +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
