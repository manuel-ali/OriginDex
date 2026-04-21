package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

public class BattleText {
    public static String getTurnStartMessage(ActivePokemon pokemon){
        return "What should " + pokemon.getNickname() + " do?";
    }

    public static String getFaintedMessage(BattleStep.Target target, String nickname){
        if (target == BattleStep.Target.ENEMY){
            return "Enemy " + nickname + " fainted!";
        }
        return nickname + " fainted!";
    }

    public static String getPPMessage(ActiveMove move){
        String moveIdentifier = move.getMove().getIdentifier();
        return moveIdentifier + " don't have enough PP.";
    }

    public static String getMissMessage(ActivePokemon pokemon, ActiveMove move) {
        String nickname = pokemon.getNickname();
        String moveIdentifier = move.getMove().getIdentifier();

        return nickname + " missed " + moveIdentifier;
    }

    public static String getChosenMoveMessage(String nickname, String moveIdentifier, BattleStep.Target moveUser) {
        if (moveUser == BattleStep.Target.ENEMY){
            return "Enemy " + nickname + " used " + moveIdentifier;
        }
        return nickname + " used " + moveIdentifier;
    }

    /**
     * Appends a descriptive message based on the calculated effectiveness modifier.
     * Only triggers if the attack is super effective (2.0), not very effective (0.5) or has no effect (0.0).
     *
     * @param effectiveness The total effectiveness multiplier of the attack.
     * @return a message based on the effectiveness modifier.
     */
    public static String getEffectivenessMessage(double effectiveness){
        if (effectiveness == 0){
            return "It doesn't affect the enemy!";
        } else if (effectiveness > 1.0){
            return "It's super effective!";
        } else if (effectiveness < 1.0){
            return "It's not very effective...";
        }

        return null;
    }
}
