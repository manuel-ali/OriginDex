package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActivePokemon;

public record NoEffectResult(ActivePokemon attacker, ActivePokemon target, double effectivenessModifier) implements TurnResult{
}
