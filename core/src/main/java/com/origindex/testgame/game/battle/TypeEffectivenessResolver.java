package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;
import com.origindex.testgame.game.model.Move;
import com.origindex.testgame.game.model.PokemonType;
import com.origindex.testgame.game.model.Type;
import com.origindex.testgame.game.model.TypeEfficacy;
import com.origindex.testgame.repository.TypeEfficacyRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeEffectivenessResolver {

    /**
     * Map that stores the effectivity relationships between move type and Pokémon types.
     * The key is a {@link TypeEfficacy} object combining the attacker type and the target type
     * and the value is a damage multiplier (e.g., 2.0 = super effective, 0.5 = not very effective, 0 = no effect).
     */
    private static final Map<TypeEfficacy, Double> typeEffectivenessMap = loadEffectivenessMap();

    /**
     * Calculates the total effectiveness modifier of a move against a target Pokémon.
     * If the target Pokémon multiple types, the modifiers for each type are multiplied together.
     * Also appends a descriptive message (e.g., "It's super effective!") according to the final result.
     *
     * @param target The Pokémon that receives the attack.
     * @param move The move used by the attacker.
     * @return The resulting damage multiplier based on type effectiveness (1.0 = normal effectiveness).
     */
    public static double getTypeEffectivenessModifier(ActivePokemon target, ActiveMove move){
        double effectivenessModifier = 1.0;
        List<PokemonType> targetTypes = target.getSpecie().getTypes();
        Move usedMove = move.getMove();

        for (PokemonType type : targetTypes){
            effectivenessModifier *= getEffectiveness(usedMove.getType(), type.getType());
        }

        return effectivenessModifier;
    }

    /**
     * Loads the effectiveness relationships between types, from the repository and stores them in a HashMap.
     * The values are converted to multipliers (e.g., 200 -> 2.0, 50 -> 0.5, 0 -> 0).
     *
     * @return A Map containing all type effectiveness relationships.
     */
    private static Map<TypeEfficacy, Double> loadEffectivenessMap(){
        List<TypeEfficacy> typeEffectiveness = TypeEfficacyRepository.getTypeEfficacies();
        Map<TypeEfficacy, Double> effectivenessMap = new HashMap<>();

        for (TypeEfficacy typeEfficacy: typeEffectiveness){
            TypeEfficacy key = new TypeEfficacy(typeEfficacy.getAttackerType(), typeEfficacy.getTargetType(), 0);
            effectivenessMap.put(key, typeEfficacy.getDamageFactor() / 100.0);
        }
        return effectivenessMap;
    }

    /**
     * Retrieves the effectiveness multiplier for a specific combination of attacker and target types.
     * Returns 1.0 if no matching relationship exists.
     *
     * @param attacker The used move type
     * @param target The target Pokémon type.
     * @return The damage multiplier for this type matchup.
     */
    private static double getEffectiveness(Type attacker, Type target){
        TypeEfficacy typeEfficacyKey = new TypeEfficacy(attacker, target, 0);
        if (typeEffectivenessMap.containsKey(typeEfficacyKey)) {
            return typeEffectivenessMap.get(typeEfficacyKey);
        }
        return 1.0;
    }
}
