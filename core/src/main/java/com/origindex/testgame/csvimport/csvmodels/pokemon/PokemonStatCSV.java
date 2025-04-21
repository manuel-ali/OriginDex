package com.origindex.testgame.csvimport.csvmodels.pokemon;

/**
 * Esta clase representa la estadística de un Pokémon en el juego.
 * Relaciona el ID del Pokémon con el ID de la estadística (HP, ataque, defensa, etc.),
 */
public class PokemonStatCSV {
    private int pokemonId, //Id del pokemon
            statId, //Id de la estadística (HP, ataque, defensa, etc.)
            baseStat, //Valor base de la estadística
            effort; //Valor de esfuerzo (EV) que otorga al ser derrotado

    public PokemonStatCSV(int pokemonId, int statId, int baseStat, int effort) {
        this.pokemonId = pokemonId;
        this.statId = statId;
        this.baseStat = baseStat;
        this.effort = effort;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public int getStatId() {
        return statId;
    }

    public void setStatId(int statId) {
        this.statId = statId;
    }

    public int getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(int baseStat) {
        this.baseStat = baseStat;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    @Override
    public String toString() {
        return "PokemonStatCSV{" +
            "pokemonId=" + pokemonId +
            ", statId=" + statId +
            ", baseStat=" + baseStat +
            ", effort=" + effort +
            '}';
    }
}
