package com.origindex.testgame.game.logic;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;
import com.origindex.testgame.game.model.Pokemon;
import com.origindex.testgame.game.model.PokemonMove;
import com.origindex.testgame.repository.PokemonRepository;

import java.util.ArrayList;
import java.util.List;

public class PokemonFactory {
    public static ActivePokemon getRandomPokemon() {
        int[] pokemonIds = {1, 4, 7};
        int randomIndex = (int) (Math.random() * pokemonIds.length);
        int pokemonId = pokemonIds[randomIndex];
        Pokemon pokemon = PokemonRepository.getCompletePokemonById(pokemonId);
        List<ActiveMove> moves = initializeActiveMove(pokemon.getMoves());

        return new ActivePokemon(pokemon, pokemon.getIdentifier(), 5, 43, 100, moves);
    }

    public static List<ActiveMove> initializeActiveMove(List<PokemonMove> moves) {
        List<ActiveMove> activeMoves = new ArrayList<>();
        for (PokemonMove move : moves) {
            activeMoves.add(new ActiveMove(move.getMove(), move.getMove().getPp()));
        }

        return activeMoves;
    }
}
