package com.origindex.testgame.csvimport.csvmodels.pokemon;

public class PokemonMoveMethodCSV {
    private final int id;
    private final String identifier; //Método por el cual se aprende el movimiento (nivel, MT, etc.)

    public PokemonMoveMethodCSV(int id, String identifier) {
        this.id = id;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "PokemonMoveMethodCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
