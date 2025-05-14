package com.origindex.testgame.game.model;

public class MoveMeta {
    private MoveMetaCategory metaCategory;
    private MoveMetaAilment metaAilment;
    private Integer minHits, maxHits, minTurns, maxTurns;
    private int drain, healing, critRate, ailmentChance, flinchChance, statChance;

    public MoveMeta(MoveMetaCategory metaCategory, MoveMetaAilment metaAilment, Integer minHits, Integer maxHits, Integer minTurns, Integer maxTurns,
                    int drain, int healing, int critRate, int ailmentChance, int flinchChance, int statChance) {
        this.metaCategory = metaCategory;
        this.metaAilment = metaAilment;
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

    public MoveMetaCategory getMetaCategory() {
        return metaCategory;
    }

    public void setMetaCategory(MoveMetaCategory metaCategory) {
        this.metaCategory = metaCategory;
    }

    public MoveMetaAilment getMetaAilment() {
        return metaAilment;
    }

    public void setMetaAilment(MoveMetaAilment metaAilment) {
        this.metaAilment = metaAilment;
    }

    public Integer getMinHits() {
        return minHits;
    }

    public void setMinHits(Integer minHits) {
        this.minHits = minHits;
    }

    public Integer getMaxHits() {
        return maxHits;
    }

    public void setMaxHits(Integer maxHits) {
        this.maxHits = maxHits;
    }

    public Integer getMinTurns() {
        return minTurns;
    }

    public void setMinTurns(Integer minTurns) {
        this.minTurns = minTurns;
    }

    public Integer getMaxTurns() {
        return maxTurns;
    }

    public void setMaxTurns(Integer maxTurns) {
        this.maxTurns = maxTurns;
    }

    public int getDrain() {
        return drain;
    }

    public void setDrain(int drain) {
        this.drain = drain;
    }

    public int getHealing() {
        return healing;
    }

    public void setHealing(int healing) {
        this.healing = healing;
    }

    public int getCritRate() {
        return critRate;
    }

    public void setCritRate(int critRate) {
        this.critRate = critRate;
    }

    public int getAilmentChance() {
        return ailmentChance;
    }

    public void setAilmentChance(int ailmentChance) {
        this.ailmentChance = ailmentChance;
    }

    public int getFlinchChance() {
        return flinchChance;
    }

    public void setFlinchChance(int flinchChance) {
        this.flinchChance = flinchChance;
    }

    public int getStatChance() {
        return statChance;
    }

    public void setStatChance(int statChance) {
        this.statChance = statChance;
    }

    @Override
    public String toString() {
        return "MoveMeta{" +
            "metaCategory=" + metaCategory +
            ", metaAilment=" + metaAilment +
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
