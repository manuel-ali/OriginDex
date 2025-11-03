package com.origindex.testgame.game.logic.battle;

import com.origindex.testgame.game.entity.ActiveMove;

public class AccuracyResolver {
    /**
     * Method that checks the accuracy from an attack.
     * @param move The used movement.
     * @param precision The movement accuracy.
     * @param evasion The evasion stat from the rival.
     * @return true if the attack hits, false if fails.
     */
    public static boolean resolveAccuracy(ActiveMove move, double precision, double evasion){
        double probability = move.getMove().getAccuracy() * (precision / evasion);
        double random = Math.random() * 100;

        return random <= probability;
    }
}
