package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

public record DamageResult(ActivePokemon user, ActivePokemon enemy, ActiveMove activeMove, int damage, double effectivenessModifier, BattleStep.Target moveUser, BattleStep.Target moveReceiver) implements TurnResult {
}
