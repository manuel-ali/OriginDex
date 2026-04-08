package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

public class AccuracyResolver {
    /**
     * Method that checks the accuracy from an attack.
     * @param attacker The attacker Pokémon.
     * @param defender The defender Pokémon.
     * @param move The used movement.
     * @return true if the attack hits, false if fails.
     */
    public static boolean resolveAccuracy(ActivePokemon attacker, ActivePokemon defender, ActiveMove move) {
        Integer moveAccuracy = move.getMove().getAccuracy();

        if (moveAccuracy == null){
            return true;
        }

        double attackerAccuracy = attacker.getAccuracyEvasionStageModifier(attacker.getModifiedAccuracy());
        double defenderEvasion = defender.getAccuracyEvasionStageModifier(defender.getModifiedEvasion());
        double probability = moveAccuracy * (attackerAccuracy / defenderEvasion);
        double random = Math.random() * 100;

        probability = Math.min(probability, 100);

        return random <= probability;
    }
}
