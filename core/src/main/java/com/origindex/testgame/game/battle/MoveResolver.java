package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

import java.util.List;

public class MoveResolver {
    public static ActiveMove chooseEnemyMove(ActivePokemon pokemon){
        List<ActiveMove> moves = pokemon.getLearnedMoves();
        int chosenMove = (int) (Math.random() * moves.size());
        while (!hasValidPP(moves.get(chosenMove))){
            chosenMove = (int) (Math.random() * moves.size());
        }
        return moves.get(chosenMove);
    }

    /**
     * Checks if the chosen move has valid PP's.
     * @param move The chosen Move.
     * @return true if the move has more than 0 PP, false if otherwise.
     */
    public static boolean hasValidPP(ActiveMove move){
        return move.getCurrentPP() > 0;
    }
}
