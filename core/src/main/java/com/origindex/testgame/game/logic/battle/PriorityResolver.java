package com.origindex.testgame.game.logic.battle;

import com.origindex.testgame.game.entity.ActiveMove;

public class PriorityResolver {
    /**
     * Handles if the player attacks first
     * Checks the priority of the used moves between the player and the rival
     * If the priority is the same, it comparates the speed stat
     * @param playerMove Used move by the player
     * @param enemyMove Used move by the rival
     * @param playerSpeed Speed from the player pokemon
     * @param enemySpeed Speed from the rival pokemon
     * @return true if the player attacks first, false if otherwise
     */
    public static boolean playerAttacksFirst(ActiveMove playerMove, ActiveMove enemyMove, int playerSpeed, int enemySpeed) {
        int playerPriority = playerMove.getMove().getPriority();
        int enemyPriority = enemyMove.getMove().getPriority();

        if (playerPriority < enemyPriority) return true;
        if (playerPriority > enemyPriority) return false;

        return playerSpeed >= enemySpeed;
    }
}
