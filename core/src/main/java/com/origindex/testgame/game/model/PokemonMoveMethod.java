package com.origindex.testgame.game.model;

public class PokemonMoveMethod {
    private int id;
    private String identifier;

    public PokemonMoveMethod(int id, String identifier) {
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
        return "PokemonMoveMethod{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
