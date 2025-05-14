package com.origindex.testgame.game.model;

public class MoveMetaAilment {
    private int id;
    private String identifier;

    public MoveMetaAilment(int id, String identifier) {
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
        return "MoveMetaAilment{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
