package com.origindex.testgame.game.logic.battle;

import com.origindex.testgame.game.entity.ActivePokemon;
import com.origindex.testgame.game.model.Move;
import com.origindex.testgame.game.model.PokemonType;

import java.util.List;

public class DamageModifierCalculator {
    /**
     * Calculates the damage modifier for an attack (Stab, type effectivity, etc.).
     * @param attacker attacker pokemon
     * @param target target pokemon
     * @param move used move
     * @return damage modifier in double format
     */
    public static double calculateDamageModifier(ActivePokemon attacker, ActivePokemon target, Move move, List<String> messages){
        double modifier = 1.0;

        modifier *= isStab(attacker, move);
        modifier *= TypeEffectivenessResolver.getTypeEffectivenessModifier(target, move, messages);

        return modifier;
    }

    /**
     * Calculates the STAB damage modifier (Same Type Attack Bonus)
     * @param attacker attacker pokemon
     * @param move used move
     * @return damage modifier in double format
     */
    private static double isStab(ActivePokemon attacker, Move move){
        List<PokemonType> types = attacker.getSpecie().getTypes();
        String moveTypeIdentifier = move.getType().getIdentifier().toLowerCase();

        for (PokemonType type: types){
            String typeIdentifier = type.getType().getIdentifier().toLowerCase();
            if (typeIdentifier.equals(moveTypeIdentifier)){
                return 1.5; // STAB modifier
            }
        }
        return 1.0; // No STAB modifier
    }
}
