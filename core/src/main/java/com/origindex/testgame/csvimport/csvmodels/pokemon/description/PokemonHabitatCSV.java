package com.origindex.testgame.csvimport.csvmodels.pokemon.description;

public class PokemonHabitatCSV {
    private final int id;
    private final String identifier; //Nombre del habitat (cave, forest, etc)

    public PokemonHabitatCSV(int id, String identifier) {
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
        return "PokemonHabitatCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
