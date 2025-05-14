package com.origindex.testgame.game.model;

public class Stat {
    private int id;
    private MoveDamageClass damageClass;
    private String identifier;
    private boolean isBattleOnly;
    private Integer gameIndex;

    public Stat(int id, MoveDamageClass damageClass, String identifier, boolean isBattleOnly, Integer gameIndex) {
        this.id = id;
        this.damageClass = damageClass;
        this.identifier = identifier;
        this.isBattleOnly = isBattleOnly;
        this.gameIndex = gameIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MoveDamageClass getDamageClass() {
        return damageClass;
    }

    public void setDamageClass(MoveDamageClass damageClass) {
        this.damageClass = damageClass;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public boolean isBattleOnly() {
        return isBattleOnly;
    }

    public void setBattleOnly(boolean battleOnly) {
        isBattleOnly = battleOnly;
    }

    public Integer getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(Integer gameIndex) {
        this.gameIndex = gameIndex;
    }

    @Override
    public String toString() {
        return "Stat{" +
            "id=" + id +
            ", damageClass=" + damageClass +
            ", identifier='" + identifier + '\'' +
            ", isBattleOnly=" + isBattleOnly +
            ", gameIndex=" + gameIndex +
            '}';
    }
}
