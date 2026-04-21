package com.origindex.testgame.game.battle;

public sealed interface TurnResult permits NoPPsResult, MissResult, StatusResult, NoEffectResult, FaintedResult, DamageResult{
}
