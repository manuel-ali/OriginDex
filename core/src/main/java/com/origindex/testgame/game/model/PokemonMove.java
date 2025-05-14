package com.origindex.testgame.game.model;

public class PokemonMove {
    private VersionGroup versionGroup;
    private Move move;
    private int level;
    private PokemonMoveMethod method;
    private Integer orderIndex, mastery;

    public PokemonMove(VersionGroup versionGroup, Move move, int level, PokemonMoveMethod method, Integer orderIndex, Integer mastery) {
        this.versionGroup = versionGroup;
        this.move = move;
        this.level = level;
        this.method = method;
        this.orderIndex = orderIndex;
        this.mastery = mastery;
    }

    public VersionGroup getVersionGroup() {
        return versionGroup;
    }

    public void setVersionGroup(VersionGroup versionGroup) {
        this.versionGroup = versionGroup;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public PokemonMoveMethod getMethod() {
        return method;
    }

    public void setMethod(PokemonMoveMethod method) {
        this.method = method;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Integer getMastery() {
        return mastery;
    }

    public void setMastery(Integer mastery) {
        this.mastery = mastery;
    }

    @Override
    public String toString() {
        return "PokemonMove{" +
            "versionGroup=" + versionGroup +
            ", move=" + move +
            ", level=" + level +
            ", method=" + method +
            ", orderIndex=" + orderIndex +
            ", mastery=" + mastery +
            '}';
    }
}
