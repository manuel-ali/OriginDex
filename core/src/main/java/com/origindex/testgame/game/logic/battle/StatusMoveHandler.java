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
     * Applies the effects of a status move that modifies stats (e.g., Attack ↑, Defense ↓).
     * Updates the affected Pokémon's stat map and recalculates their final stat values.
     * Also generates battle log messages describing the changes.
     *
     * @param pokemon The Pokémon that used the move.
     * @param enemyStatMap The current stat modifier map of the target Pokémon.
     * @param pkmnStats The base and final stats of the attacking Pokémon.
     * @param move The move being used.
     * @param enemy The opposing Pokémon affected by the move.
     * @param messages The battle message list where status updates are added.
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
     * Applies a stat stage modifier (increase or decrease) to a specific stat.
     * Updates both the stat modifier map and the Pokémon’s final stat value,
     * then adds a message describing the change to the battle log.
     *
     * @param pkmnStatMap The map storing current stage modifiers for each stat.
     * @param pkmnStats The list of Pokémon stats containing base and final values.
     * @param stat The stat being modified.
     * @param modifier The amount of stage change to apply (positive or negative).
     * @param nickname The Pokémon’s nickname for display in messages.
     * @param messages The list where the result message will be added.
     */
    private static void handleStatusModifier(Map<Stat, Integer> pkmnStatMap, List<PokemonStatValue> pkmnStats, Stat stat, int modifier, String nickname, List<String> messages){
        Map<Integer, Double> statsMap = getStatsMap();
        for (PokemonStatValue statValue: pkmnStats){
            if (statValue.getStat().equals(stat)){
                int newModifier = pkmnStatMap.get(statValue.getStat()) + (modifier);
                pkmnStatMap.put(statValue.getStat(), newModifier);
                statValue.setFinalStat((int) (statValue.getFinalStat() * statsMap.get(newModifier)));
                messages.add(getStatusModifierString(statValue.getStat(), nickname, modifier));
            }
        }
    }

    /**
     * Builds a descriptive message for a stat change event.
     *
     * @param stat The affected stat.
     * @param nickname The Pokémon's nickname.
     * @param modifier The change amount applied.
     * @return A formatted string describing the stat change.
     */
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
     * Returns a mapping between stage modifier values (-6 to +6)
     * and their corresponding stat multipliers.
     *
     * @return A map of stage modifiers to stat multipliers.
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
