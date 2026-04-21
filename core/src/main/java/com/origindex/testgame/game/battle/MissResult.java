package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

public record MissResult(ActivePokemon attacker, ActiveMove activeMove) implements TurnResult {
}
