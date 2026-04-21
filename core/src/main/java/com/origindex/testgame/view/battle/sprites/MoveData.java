package com.origindex.testgame.view.battle.sprites;

public record MoveData(
    int id,
    String identifier,
    int currentPp,
    int maxPp,
    int slot
) {
    public String displayMove(){
        return identifier + " " + currentPp + "/" + maxPp;
    }
}
