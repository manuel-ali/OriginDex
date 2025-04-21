package com.origindex.testgame.csvimport.csvmodels.pokemon;

public class PokemonMoveMethodCSV {
    private int id;
    private String identifier; //Método por el cual se aprende el movimiento (nivel, MT, etc.)

    public PokemonMoveMethodCSV(int id, String identifier) {
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
        return "PokemonMoveMethodCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
