package com.origindex.testgame.game.model;

public class VersionGroup {
    private int id;
    private String identifier;
    private Generation generation;
    private int orderIndex;

    public VersionGroup(int id, String identifier, Generation generation, int orderIndex) {
        this.id = id;
        this.identifier = identifier;
        this.generation = generation;
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

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    @Override
    public String toString() {
        return "VersionGroup{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", generation=" + generation +
            ", orderIndex=" + orderIndex +
            '}';
    }
}
