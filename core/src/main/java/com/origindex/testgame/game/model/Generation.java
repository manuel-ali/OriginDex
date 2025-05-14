package com.origindex.testgame.game.model;

public class Generation {
    private int id;
    private Region mainRegion;
    private String identifier;

    public Generation(int id, Region mainRegion, String identifier) {
        this.id = id;
        this.mainRegion = mainRegion;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Region getMainRegion() {
        return mainRegion;
    }

    public void setMainRegion(Region mainRegion) {
        this.mainRegion = mainRegion;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "Generation{" +
            "id=" + id +
            ", mainRegion=" + mainRegion +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
