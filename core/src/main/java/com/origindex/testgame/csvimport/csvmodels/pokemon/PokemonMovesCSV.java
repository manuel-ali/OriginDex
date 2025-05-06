package com.origindex.testgame.csvimport.csvmodels.pokemon;

/** Clase de importación de pokemon_moves.csv para la base de datos.
 * Esta clase relaciona los pokemon con los movimientos que pueden aprender.
 */
public class PokemonMovesCSV {
    private final int pokemonId, //Id del pokemon que aprende el movimiento
        versionGroupId, //Versión del juego en la que aprende el movimiento (emerald, platinum, etc.)
        moveId, //Id del movimiento que aprende el pokemon
        level, //Nivel al que aprende el movimiento
        pokemonMoveMethodId; //Id del método por el que aprende el movimiento (nivel, máquina, etc.)
    private final Integer orderIndex; //Orden en el que aprende el movimiento (solo tiene importancia visual)
    private final Boolean mastery; //Indica si el movimiento puede ser dominado (Legends: Arceus.)

    public PokemonMovesCSV(int pokemonId, int versionGroupId, int moveId, int level, int pokemonMoveMethodId, Integer orderIndex, Boolean mastery) {
        this.pokemonId = pokemonId;
        this.versionGroupId = versionGroupId;
        this.moveId = moveId;
        this.level = level;
        this.pokemonMoveMethodId = pokemonMoveMethodId;
        this.orderIndex = orderIndex;
        this.mastery = mastery;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public int getVersionGroupId() {
        return versionGroupId;
    }

    public int getMoveId() {
        return moveId;
    }

    public int getLevel() {
        return level;
    }

    public int getPokemonMoveMethodId() {
        return pokemonMoveMethodId;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public Boolean getMastery() {
        return mastery;
    }

    @Override
    public String toString() {
        return "PokemonMovesCSV{" +
            "pokemonId=" + pokemonId +
            ", versionGroupId=" + versionGroupId +
            ", moveId=" + moveId +
            ", level=" + level +
            ", pokemonMoveMethodId=" + pokemonMoveMethodId +
            ", orderIndex=" + orderIndex +
            ", mastery=" + mastery +
            '}';
    }
}
