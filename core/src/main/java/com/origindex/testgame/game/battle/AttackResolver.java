package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

public class AttackResolver {
    /**
     * Method that resolves the Pokémon attack.
     * @param target The target Pokémon.
     * @param move The used Move.
     * @param damage The damage to apply.
     */
    public static void resolvePokemonAttack(ActivePokemon target, ActiveMove move, int damage){
        applyDamage(target, damage);//Aplicamos daño al pokemon enemigo
        checkFaintedPokemon(target); //Comprobamos si el pokemon enemigo ha caído
    }

    /**
     * Method that checks if the Pokémon is fainted.
     * @param target The target Pokémon.
     */
    private static void checkFaintedPokemon(ActivePokemon target){
        if (target.getCurrentHP() <= 0){
            target.setFainted(true);
        }
    }

    /**
     * Method that applies the damage to the target Pokémon.
     * @param target The target Pokémon.
     * @param damage The damage to apply.
     */
    private static void applyDamage(ActivePokemon target, int damage){
        int newHp = Math.max(target.getCurrentHP() - damage, 0);
        target.setCurrentHP(newHp);
    }
}
