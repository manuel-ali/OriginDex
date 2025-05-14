package com.origindex.testgame.game.model;

public class MoveDamageClass {
    private int id;
    private String identifier;

    public MoveDamageClass(int id, String identifier) {
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
        return "MoveDamageClass{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
