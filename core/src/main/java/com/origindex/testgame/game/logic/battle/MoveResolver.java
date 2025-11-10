package com.origindex.testgame.game.logic.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

import java.util.List;

public class MoveResolver {
    public static Boolean resolvePlayerMove(ActivePokemon pokemon, ActiveMove move, List<String> messages){
        return hasValidPP(move) ? true : false;
    }

    public static ActiveMove chooseEnemyMove(ActivePokemon pokemon, List<String> messages){
        List<ActiveMove> moves = pokemon.getLearnedMoves();
        int chosenMove = (int) (Math.random() * moves.size());
        while (!hasValidPP(moves.get(chosenMove))){
            chosenMove = (int) (Math.random() * moves.size());
        }
        return moves.get(chosenMove);
    }

    /**
     * Checks if the chosen move has PP
     * @param move chosen move
     * @return false if the move has <= 0 PP
     */
    private static boolean hasValidPP(ActiveMove move){
        if (move.getCurrentPP() <= 0){
            System.out.println(move.getMove().getIdentifier() + " don't have enough PP.");
            return false;
        }
        return true;
    }

    public static String showPPMessage(ActiveMove move){
        return move.getMove().getIdentifier() + " don't have enough PP.";
    }

    /**
     * Shows the chosen move
     * @param pokemon Entity that represents the playable pokemon in the battle
     * @param move Entity that represents the chosen move for the player
     */
    public static String showChosenMove(ActivePokemon pokemon, ActiveMove move){
        return pokemon.getNickname() + " used " + move.getMove().getIdentifier();
    }

    /**
     * Shows the available moves for the pokemon
     * Metodo que muestra por consola los movimientos que tiene el pokemon
     * @param moves move list the pokemon know
     */
    private static void showPokemonMoves(List<ActiveMove> moves){
        for (int i = 0; i < moves.size(); i++) {
            System.out.println((i + 1) + ". " + moves.get(i).getMove().getIdentifier() + "/ PP: " + moves.get(i).getCurrentPP() + "/" + moves.get(i).getMove().getPp());
        }
    }
}
