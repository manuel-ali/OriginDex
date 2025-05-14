package com.origindex.testgame.game.model;

public class MoveTarget {
    private int id;
    private String identifier;

    public MoveTarget(int id, String identifier) {
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
        return "MoveTarget{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
