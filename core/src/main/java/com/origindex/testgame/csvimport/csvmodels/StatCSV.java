package com.origindex.testgame.csvimport.csvmodels;

//Clase de importación de stats.csv para la base de datos
public class StatCSV {
    private int id;
    private Integer damageClassId;
    private String identifier;
    private boolean isBattleOnly;
    private Integer gameIndex;

    public StatCSV(int id, Integer damageClassId, String identifier, boolean isBattleOnly, Integer gameIndex) {
        this.id = id;
        this.damageClassId = damageClassId;
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

    public Integer getDamageClassId() {
        return damageClassId;
    }

    public void setDamageClassId(Integer damageClassId) {
        this.damageClassId = damageClassId;
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

    public void setIsBattleOnly(boolean isBattleOnly) {
        this.isBattleOnly = isBattleOnly;
    }

    public Integer getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(Integer gameIndex) {
        this.gameIndex = gameIndex;
    }

    @Override
    public String toString() {
        return "StatCSV{" +
            "id=" + id +
            ", damageClassId=" + damageClassId +
            ", identifier='" + identifier + '\'' +
            ", isBattleOnly=" + isBattleOnly +
            ", gameIndex=" + gameIndex +
            '}';
    }
}
