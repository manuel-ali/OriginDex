package com.origindex.testgame.game.logic.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

public class DamageCalculator {
    /**
     * Calculates the damage to the used attack
     * @param pokemon attacker pokemon
     * @param move used move
     * @param enemy defender pokemon
     * @param modifier damage modifier (effectivity, STAB, etc)
     * @return calculated damage
     */
    public static int calculateDamage(ActivePokemon pokemon, ActiveMove move, ActivePokemon enemy, double modifier) {
        int level = pokemon.getLevel();
        int power = move.getMove().getPower();
        double damage;

        if (move.getMove().isSpecialMove()){ // If the attack is special, use the special stats
            int specialAttack = pokemon.getSpecialAttackStat();
            int rivalSpecialDefense = enemy.getSpecialDefenseStat();
            damage = handleDamageFormula(level, power, specialAttack, rivalSpecialDefense) * modifier;
        }else {
            int attack = pokemon.getAttackStat();
            int rivalDefense = enemy.getDefenseStat();
            damage = handleDamageFormula(level, power, attack, rivalDefense) * modifier;
        }

        return (int) damage;
    }

    /**
     * Calculates the damage to the used attack with a formula
     * @param level attacker pokemon level
     * @param power power of the used move
     * @param attack attack from the attacker pokemon
     * @param defense defense from the defender pokemon
     * @return damage calculated in double format
     */
    public static double handleDamageFormula(int level, int power, int attack, int defense) {
        return (((2.0 * level) / 5 + 2) * power * attack / (double) defense) / 50 + 2;    }
}
