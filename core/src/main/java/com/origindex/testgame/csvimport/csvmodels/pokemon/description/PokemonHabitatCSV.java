package com.origindex.testgame.csvimport.csvmodels.pokemon.description;

public class PokemonHabitatCSV {
    private int id;
    private String identifier; //Nombre del habitat (cave, forest, etc)

    public PokemonHabitatCSV(int id, String identifier) {
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
        return "PokemonHabitatCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
