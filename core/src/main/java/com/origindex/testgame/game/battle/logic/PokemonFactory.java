package com.origindex.testgame.game.battle.logic;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;
import com.origindex.testgame.game.model.Move;
import com.origindex.testgame.game.model.Pokemon;
import com.origindex.testgame.game.model.PokemonMove;
import com.origindex.testgame.repository.PokemonRepository;

import java.util.ArrayList;
import java.util.List;

public class PokemonFactory {
    public static ActivePokemon getRandomPokemon(boolean isPlayer) {
        int[] pokemonIds = {1, 4, 7};
        int randomIndex = (int) (Math.random() * pokemonIds.length);
        int pokemonId = pokemonIds[randomIndex];
        Pokemon pokemon = PokemonRepository.getCompletePokemonById(pokemonId);
        String nickname = pokemon.getIdentifier();
        List<ActiveMove> moves = initializeActiveMove(pokemon.getMoves());

        return new ActivePokemon(pokemon, nickname, 5, 0, 100, moves, isPlayer);
    }

    public static List<ActiveMove> initializeActiveMove(List<PokemonMove> moves) {
        List<ActiveMove> activeMoves = new ArrayList<>();
        for (PokemonMove move : moves) {
            Move m = move.getMove();
            int pps = m.getPp();
            activeMoves.add(new ActiveMove(m, pps));
        }

        return activeMoves;
    }
}
