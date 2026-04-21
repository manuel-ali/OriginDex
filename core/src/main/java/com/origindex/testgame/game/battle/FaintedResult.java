package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;

public record FaintedResult(ActivePokemon user, ActivePokemon enemy, ActiveMove activeMove, BattleStep.Target moveUser, BattleStep.Target moveReceiver, double effectivenessModifier)
    implements TurnResult{
}
