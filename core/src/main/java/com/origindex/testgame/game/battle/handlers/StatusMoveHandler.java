package com.origindex.testgame.game.battle.handlers;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;
import com.origindex.testgame.game.entity.PokemonStatValue;
import com.origindex.testgame.game.model.MoveMetaStatChange;

import java.util.List;
import java.util.Map;

public class StatusMoveHandler {
    /**
     * Applies the effects of a status move that modifies stats (e.g., Attack ↑, Defense ↓).
     * Updates the affected Pokémon's stat map and recalculates their final stat values.
     * Also generates battle log messages describing the changes.
     *
     * @param target The target Pokémon affected by the used move.
     * @param move The move being used.
     */
    public static void handleStatusMove(ActivePokemon target, ActiveMove move) {
        List<PokemonStatValue> targetStatList = target.getStats();
        Map<String, Integer> targetStatStages = target.getStatStages();
        List<MoveMetaStatChange> moveMetaStatChanges = move.getMove().getStatChanges();

        for (MoveMetaStatChange statChange : moveMetaStatChanges) {
            String statIdentifier = statChange.getStat().getIdentifier();
            int statModifier = statChange.getChange();
            handleStatusModifier(targetStatStages, targetStatList, statIdentifier, statModifier);
        }
    }

    /**
     * Applies a stat stage modifier (increase or decrease) to a specific stat.
     * Updates both the stat modifier map and the Pokémon’s final stat value,
     * then adds a message describing the change to the battle log.
     *
     * @param targetStatStages The map storing current stage modifiers for each stat.
     * @param targetStats The list of Pokémon stats containing base and final values.
     * @param stat The stat being modified.
     * @param modifier The amount of stage change to apply (positive or negative).
     */
    private static void handleStatusModifier(Map<String, Integer> targetStatStages, List<PokemonStatValue> targetStats, String stat, int modifier){
        for (PokemonStatValue statValue: targetStats){
            if (statValue.getStat().getIdentifier().equals(stat)){
                String statIdentifier  = statValue.getStat().getIdentifier();
                int currentModifier = targetStatStages.get(statIdentifier);
                int newModifier = currentModifier + modifier;
                newModifier = Math.max(-6, Math.min(6, newModifier));
                targetStatStages.put(statIdentifier, newModifier);
            }
        }
    }
}
