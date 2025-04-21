package com.origindex.testgame.csvimport.csvmodels.pokemon;

/**
 * Clase que representa la tabla pokemon_type de la base de datos.
 * Esta tabla relaciona cada pokemon con sus tipos.
 */
public class PokemonTypeCSV {
    private final int pokemonId, //Id del pokemon
            typeId, //Id del tipo del pokemon (agua, fuego, etc.)
            slot; //Slot del tipo del pokemon (1 o 2, ya que un pokemon puede tener 2 tipos)

    public PokemonTypeCSV(int pokemonId, int typeId, int slot) {
        this.pokemonId = pokemonId;
        this.typeId = typeId;
        this.slot = slot;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public int getTypeId() {
        return typeId;
    }

    public int getSlot() {
        return slot;
    }

    @Override
    public String toString() {
        return "PokemonTypeCSV{" +
            "pokemonId=" + pokemonId +
            ", typeId=" + typeId +
            ", slot=" + slot +
            '}';
    }
}
