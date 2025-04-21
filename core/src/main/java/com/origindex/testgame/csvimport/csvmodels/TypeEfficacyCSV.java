package com.origindex.testgame.csvimport.csvmodels;

public class TypeEfficacyCSV {
    private final int damageTypeId, //Tipo del movimiento del pokemon atacante
            targetTypeId, //Tipo del Pokémon objetivo
            damageFactor; //Factor de daño (0, 50, 100, 200, 400)

    public TypeEfficacyCSV(int damageTypeId, int targetTypeId, int damageFactor) {
        this.damageTypeId = damageTypeId;
        this.targetTypeId = targetTypeId;
        this.damageFactor = damageFactor;
    }

    public int getDamageTypeId() {
        return damageTypeId;
    }

    public int getTargetTypeId() {
        return targetTypeId;
    }

    public int getDamageFactor() {
        return damageFactor;
    }

    @Override
    public String toString() {
        return "TypeEfficacyCSV{" +
            "damageTypeId=" + damageTypeId +
            ", targetTypeId=" + targetTypeId +
            ", damageFactor=" + damageFactor +
            '}';
    }
}
