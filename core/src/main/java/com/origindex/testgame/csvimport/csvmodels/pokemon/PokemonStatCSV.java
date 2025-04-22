package com.origindex.testgame.csvimport.csvmodels.pokemon;

/**
 * Esta clase representa la estadística de un Pokémon en el juego.
 * Relaciona el ID del Pokémon con el ID de la estadística (HP, ataque, defensa, etc.),
 */
public class PokemonStatCSV {
    private final int pokemonId, //Id del pokemon
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

    public int getStatId() {
        return statId;
    }

    public int getBaseStat() {
        return baseStat;
    }

    public int getEffort() {
        return effort;
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
