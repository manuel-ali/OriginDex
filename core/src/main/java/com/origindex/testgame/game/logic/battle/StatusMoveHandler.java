package com.origindex.testgame.game.logic.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;
import com.origindex.testgame.game.entity.PokemonStatValue;
import com.origindex.testgame.game.model.MoveMetaStatChange;
import com.origindex.testgame.game.model.Stat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusMoveHandler {
    /**
     * Handles the problem states (Poison, paralyzed, etc.)
     * @param pokemon Pokemon that used the move.
     * @param enemyStatMap Stats map from the enemy pokemon
     * @param pkmnStats Stats of the pokemon
     * @param move used move
     */
    public static void handleStatusMove(ActivePokemon pokemon, Map<Stat, Integer> enemyStatMap, List<PokemonStatValue> pkmnStats, ActiveMove move, ActivePokemon enemy, List<String> messages){
        for (MoveMetaStatChange statChange: move.getMove().getStatChanges()){
            if (statChange.getChange() > 0 ){
                handleStatusModifier(enemyStatMap, pkmnStats, statChange.getStat(), statChange.getChange(), pokemon.getNickname(), messages);
            }else if (statChange.getChange() < 0){
                handleStatusModifier(enemyStatMap, pkmnStats, statChange.getStat(), statChange.getChange(), enemy.getNickname(), messages);
            }
        }
    }

    /**
     * Handles the stats modifier (increase, decrease)
     * @param pkmnStatMap Stats map by the pokemon
     * @param pkmnStats Stats by the pokemon
     * @param stat Stat to modify
     * @param modifier Modifier to apply
     */
    private static void handleStatusModifier(Map<Stat, Integer> pkmnStatMap, List<PokemonStatValue> pkmnStats, Stat stat, int modifier, String nickname, List<String> messages){
        Map<Integer, Double> statsMap = getStatsMap();
        for (PokemonStatValue statValue: pkmnStats){
            if (statValue.getStat().equals(stat)){ //Comprobar que siempre estoy cambiando la stat del player
                int newModifier = pkmnStatMap.get(statValue.getStat()) + (modifier);
                pkmnStatMap.put(statValue.getStat(), newModifier);
                statValue.setFinalStat((int) (statValue.getFinalStat() * statsMap.get(newModifier)));
                messages.add(getStatusModifierString(statValue.getStat(), nickname, modifier));
            }
        }
    }

    private static String getStatusModifierString(Stat stat, String nickname, int modifier) {
        StringBuilder sb = new StringBuilder();
        sb.append(nickname).append(" ").append(stat.getIdentifier());
        if (modifier > 0) {
            sb.append(" increased by ").append(modifier);
        } else if (modifier < 0) {
            sb.append(" decreased by ").append(-modifier);
        } else {
            sb.append(" unchanged");
        }
        return sb.toString();
    }

    /**
     * Initialize the stats map
     * @return Stats map
     */
    private static Map<Integer, Double> getStatsMap(){
        Map<Integer, Double> statsMap = new HashMap<>();

        statsMap.put(6, 4.0);
        statsMap.put(5, 3.5);
        statsMap.put(4, 3.0);
        statsMap.put(3, 2.5);
        statsMap.put(2, 2.0);
        statsMap.put(1, 1.5);
        statsMap.put(0, 1.0);
        statsMap.put(-1, 0.67);
        statsMap.put(-2, 0.5);
        statsMap.put(-3, 0.4);
        statsMap.put(-4, 0.33);
        statsMap.put(-5, 0.29);
        statsMap.put(-6, 0.25);

        return statsMap;
    }
}
