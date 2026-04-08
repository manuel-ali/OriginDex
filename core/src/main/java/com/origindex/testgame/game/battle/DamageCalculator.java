package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

public class DamageCalculator {
    /**
     * Calculates the damage to the used attack
     * @param attacker attacker Pokémon
     * @param move used move
     * @param target target Pokémon
     * @param modifier damage modifier (effectivity, STAB, etc)
     * @return calculated damage
     */
    public static int calculateDamage(ActivePokemon attacker, ActiveMove move, ActivePokemon target, double modifier) {
        int level = attacker.getLevel();
        int power = move.getMove().getPower();
        boolean isSpecialMove = move.getMove().isSpecialMove();
        double damage;

        if (isSpecialMove){ // If the move is special, use the special stats
            int specialAttack = attacker.getModifiedSpecialAttack();
            int rivalSpecialDefense = target.getModifiedSpecialDefense();
            damage = handleDamageFormula(level, power, specialAttack, rivalSpecialDefense) * modifier;
        }else {
            int attack = attacker.getModifiedAttack();
            int rivalDefense = target.getModifiedDefense();
            damage = handleDamageFormula(level, power, attack, rivalDefense) * modifier;
        }

        return (int) damage;
    }

    /**
     * Calculates the damage to the used attack with a formula.
     * @param level The attacker Pokémon level.
     * @param power The power of the used move
     * @param attack The attack from the attacker Pokémon
     * @param defense The defense from the defender Pokémon.
     * @return The calculated damage in double format.
     */
    public static double handleDamageFormula(int level, int power, int attack, int defense) {
        return (((2.0 * level) / 5 + 2) * power * attack / (double) defense) / 50 + 2;    }
}
