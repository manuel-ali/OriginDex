package com.origindex.testgame.game.logic.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

import java.util.List;

public class AttackResolver {
    /**
     * Method that handles the Pokemon attack
     * @param pokemon attacker pokemon
     * @param move used movement
     * @param enemy enemy pokemon
     * @param damage applied damage
     * @param isHitting checks if the move is hitting
     */
    public static void resolvePokemonAttack(ActivePokemon pokemon, ActiveMove move, ActivePokemon enemy, int damage, boolean isHitting, List<String> messages){
        String nickname = pokemon.getNickname();
        String enemyNickname = enemy.getNickname();

        //Comprobamos si el pokemon ha fallado el ataque
        if (!isHitting){
            showMissMessage(nickname, move); //Mostramos mensaje de fallo
            return; //No se aplica daño
        }

        applyDamage(enemy, damage); //Aplicamos daño al pokemon enemigo
        checkFaintedPokemon(enemy); //Comprobamos si el pokemon enemigo ha caído
        decrementPp(move); //Restamos el PP del movimiento
        showPokemonDamage(nickname, enemyNickname, damage); //Mostramos mensaje de daño
        showPokemonHp(enemyNickname, enemy); //Mostramos mensaje de HP
    }

    /**
     * Method that shows the damage message
     * @param nickname attacker pokemon name
     * @param enemyNickname defender pokemon name
     * @param damage damage dealt
     */
    private static void showPokemonDamage(String nickname, String enemyNickname, int damage){
        System.out.println(nickname + " hit " + enemyNickname + " for " + damage + " damage!");
    }

    /**
     * Method that shows the enemy HP.
     * @param enemyNickname defender pokemon name
     * @param enemy defender pokemon
     */
    private static void showPokemonHp(String enemyNickname, ActivePokemon enemy){
        System.out.println(enemyNickname + " HP: " + enemy.getCurrentHP() + "/" + enemy.getMaxHP());
    }

    /**
     * Method that shows the missed attack message
     * @param nickname attacker pokemon name
     * @param move used move
     */
    private static void showMissMessage(String nickname, ActiveMove move){
        System.out.println(nickname + " missed " + move.getMove().getIdentifier());
    }


    /**
     * Method that checks if the pokemon is fainted
     * @param target defender pokemon
     */
    private static void checkFaintedPokemon(ActivePokemon target){
        if (target.getCurrentHP() <= 0){
            System.out.println(target.getNickname() + " fainted!");
            target.setFainted(true);
        }
    }

    /**
     * Method that handles to subtract PP to the used move
     * @param move used move
     */
    private static  void decrementPp(ActiveMove move){
        int newPP = Math.max(move.getCurrentPP() - 1, 0);
        move.setCurrentPP(newPP);
    }

    /**
     * Method that applies the damage to the defender pokemon
     * @param target defender pokemon
     * @param damage damage to apply
     */
    private static void applyDamage(ActivePokemon target, int damage){
        int newHp = Math.max(target.getCurrentHP() - damage, 0);
        target.setCurrentHP(newHp);
    }
}
