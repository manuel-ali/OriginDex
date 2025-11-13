package com.origindex.testgame.game.model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MoveDamageClass that = (MoveDamageClass) o;
        return id == that.id && Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identifier);
    }

    @Override
    public String toString() {
        return "MoveDamageClass{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
