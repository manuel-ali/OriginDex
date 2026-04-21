package com.origindex.testgame.view.battle;

import com.origindex.testgame.view.battle.sprites.MoveData;

import java.util.List;

public record PokemonData(
    int id,
    String nickname,
    int level,
    int currentHp,
    int maxHp,
    int nextLevelXp,
    int actualXp,
    List<MoveData> moveData) {
}
