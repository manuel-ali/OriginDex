package com.origindex.testgame.game.model;

public class TypeEfficacy {
    private Type attackerType;
    private Type targetType;
    private int damageFactor;

    public TypeEfficacy(Type attackerType, Type targetType, int damageFactor) {
        this.attackerType = attackerType;
        this.targetType = targetType;
        this.damageFactor = damageFactor;
    }

    public Type getAttackerType() {
        return attackerType;
    }

    public void setAttackerType(Type attackerType) {
        this.attackerType = attackerType;
    }

    public Type getTargetType() {
        return targetType;
    }

    public void setTargetType(Type targetType) {
        this.targetType = targetType;
    }

    public int getDamageFactor() {
        return damageFactor;
    }

    public void setDamageFactor(int damageFactor) {
        this.damageFactor = damageFactor;
    }

    @Override
    public String toString() {
        return "TypeEfficacy{" +
            "attackerType=" + attackerType +
            ", targetType=" + targetType +
            ", damageFactor=" + damageFactor +
            '}';
    }
}
