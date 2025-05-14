package com.origindex.testgame.csvimport.csvmodels.moves;

public class MoveMetaCSV {
    private final int moveId, metaCategoryID, metaAilmentID;
    private final Integer minHits, maxHits, minTurns, maxTurns;
    private final int drain, healing, critRate, ailmentChance, flinchChance, statChance;

    public MoveMetaCSV(int moveId, int metaCategoryID, int metaAilmentID, Integer minHits, Integer maxHits, Integer minTurns,
                       Integer maxTurns, int drain, int healing, int critRate, int ailmentChance, int flinchChance, int statChance) {
        this.moveId = moveId;
        this.metaCategoryID = metaCategoryID;
        this.metaAilmentID = metaAilmentID;
        this.minHits = minHits;
        this.maxHits = maxHits;
        this.minTurns = minTurns;
        this.maxTurns = maxTurns;
        this.drain = drain;
        this.healing = healing;
        this.critRate = critRate;
        this.ailmentChance = ailmentChance;
        this.flinchChance = flinchChance;
        this.statChance = statChance;
    }

    public int getMoveId() {
        return moveId;
    }

    public int getMetaCategoryID() {
        return metaCategoryID;
    }

    public int getMetaAilmentID() {
        return metaAilmentID;
    }

    public Integer getMinHits() {
        return minHits;
    }

    public Integer getMaxHits() {
        return maxHits;
    }

    public Integer getMinTurns() {
        return minTurns;
    }

    public Integer getMaxTurns() {
        return maxTurns;
    }

    public int getDrain() {
        return drain;
    }

    public int getHealing() {
        return healing;
    }

    public int getCritRate() {
        return critRate;
    }

    public int getAilmentChance() {
        return ailmentChance;
    }

    public int getFlinchChance() {
        return flinchChance;
    }

    public int getStatChance() {
        return statChance;
    }

    @Override
    public String toString() {
        return "MoveMetaCSV{" +
            "moveId=" + moveId +
            ", metaCategoryID=" + metaCategoryID +
            ", metaAilmentID=" + metaAilmentID +
            ", minHits=" + minHits +
            ", maxHits=" + maxHits +
            ", minTurns=" + minTurns +
            ", maxTurns=" + maxTurns +
            ", drain=" + drain +
            ", healing=" + healing +
            ", critRate=" + critRate +
            ", ailmentChance=" + ailmentChance +
            ", flinchChance=" + flinchChance +
            ", statChance=" + statChance +
            '}';
    }
}
