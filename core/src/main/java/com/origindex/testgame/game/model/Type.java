package com.origindex.testgame.game.model;

import java.util.Objects;

public class Type {
    private int id;
    private String identifier;
    private Generation generation;
    private MoveDamageClass damageClass;

    public Type(int id, String identifier, Generation generation, MoveDamageClass damageClass) {
        this.id = id;
        this.identifier = identifier;
        this.generation = generation;
        this.damageClass = damageClass;
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

    public MoveDamageClass getDamageClass() {
        return damageClass;
    }

    public void setDamageClass(MoveDamageClass damageClass) {
        this.damageClass = damageClass;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return id == type.id && Objects.equals(identifier, type.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identifier);
    }

    @Override
    public String toString() {
        return "Type{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", generation=" + generation +
            ", damageClass=" + damageClass +
            '}';
    }
}
