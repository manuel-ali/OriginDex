package com.origindex.testgame.csvimport.csvmodels.pokemon.description;

public class PokemonColorNameCSV {
    private int pokemonColorId, localLanguageId;
    private String name; //Nombre del color del Pokemon traducido segun el local_language_id

    public PokemonColorNameCSV(int pokemonColorId, int localLanguageId, String name) {
        this.pokemonColorId = pokemonColorId;
        this.localLanguageId = localLanguageId;
        this.name = name;
    }

    public int getPokemonColorId() {
        return pokemonColorId;
    }

    public void setPokemonColorId(int pokemonColorId) {
        this.pokemonColorId = pokemonColorId;
    }

    public int getLocalLanguageId() {
        return localLanguageId;
    }

    public void setLocalLanguageId(int localLanguageId) {
        this.localLanguageId = localLanguageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PokemonColorNameCSV{" +
            "pokemonColorId=" + pokemonColorId +
            ", localLanguageId=" + localLanguageId +
            ", name='" + name + '\'' +
            '}';
    }
}
