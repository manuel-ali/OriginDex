package com.origindex.testgame.csvimport.csvmodels;

public class VersionGroupCSV {
    private int id;
    private String identifier;
    private int generationId,
        orderIndex;

    public VersionGroupCSV(int id, String identifier, int generationId, int orderIndex) {
        this.id = id;
        this.identifier = identifier;
        this.generationId = generationId;
        this.orderIndex = orderIndex;
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

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    @Override
    public String toString() {
        return "VersionGroupCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", generationId=" + generationId +
            ", orderIndex=" + orderIndex +
            '}';
    }
}
