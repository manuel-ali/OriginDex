package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;
import com.origindex.testgame.game.model.Move;
import com.origindex.testgame.game.model.PokemonType;

import java.util.List;

public class DamageModifierCalculator {
    /**
     * Calculates the damage modifier for an attack (Stab, type effectivity, etc.).
     * @param stabModifier The stab modifier, 1.5 if the move and user move type are the same, 1.0 if not.
     * @param effectivenessModifier The effectiveness modifier can be 2.0, 1.0 or 0.
     * @return damage modifier in double format
     */
    public static double calculateTotalDamageModifier(double stabModifier, double effectivenessModifier){
        double modifier = 1.0;

        modifier *= stabModifier;
        modifier *= effectivenessModifier;

        return modifier;
    }

    /**
     * Calculates the STAB damage modifier (Same Type Attack Bonus)
     * @param attacker attacker pokemon
     * @param move used move
     * @return damage modifier in double format
     */
    public static double getStabModifier(ActivePokemon attacker, ActiveMove move){
        List<PokemonType> types = attacker.getSpecie().getTypes();
        Move usedMove = move.getMove();
        String moveTypeIdentifier = usedMove.getType().getIdentifier().toLowerCase();

        for (PokemonType type: types){
            String typeIdentifier = type.getType().getIdentifier().toLowerCase();
            if (typeIdentifier.equals(moveTypeIdentifier)){
                return 1.5; // STAB modifier
            }
        }
        return 1.0; // No STAB modifier
    }
}
