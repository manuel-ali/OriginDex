package com.origindex.testgame.csvimport.csvmodels;

public class GenerationCSV {
    private final int id,
        mainRegionId; //Id de la region principal de la generación
    private final String identifier; //Nombre de la generación

    public GenerationCSV(int id, int mainRegionId, String identifier) {
        this.id = id;
        this.mainRegionId = mainRegionId;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public int getMainRegionId() {
        return mainRegionId;
    }

    public String getIdentifier() {
        return identifier;
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
