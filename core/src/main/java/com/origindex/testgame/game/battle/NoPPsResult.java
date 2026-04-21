package com.origindex.testgame.game.battle;

import com.origindex.testgame.game.entity.ActiveMove;

public record NoPPsResult(ActiveMove activeMove) implements TurnResult {
}
