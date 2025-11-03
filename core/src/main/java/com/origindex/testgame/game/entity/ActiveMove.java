package com.origindex.testgame.game.entity;

import com.origindex.testgame.game.model.Move;

public class ActiveMove {
    private Move move;
    private int currentPP;

    public ActiveMove(Move move, int currentPP) {
        this.move = move;
        this.currentPP = currentPP;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public int getCurrentPP() {
        return currentPP;
    }

    public void setCurrentPP(int currentPP) {
        this.currentPP = currentPP;
    }

    @Override
    public String toString() {
        return "ActiveMove{" +
            "move=" + move +
            ", currentPP=" + currentPP +
            '}';
    }
}
