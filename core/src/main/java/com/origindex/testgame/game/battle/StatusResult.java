package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

public record StatusResult(ActivePokemon attacker, ActivePokemon target, ActiveMove activeMove) implements TurnResult {
}
