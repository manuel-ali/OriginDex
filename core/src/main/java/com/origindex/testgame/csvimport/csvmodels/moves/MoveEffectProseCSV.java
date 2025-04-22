package com.origindex.testgame.csvimport.csvmodels.moves;

public class MoveEffectProseCSV {
    private final int moveEffectId, //Id del efecto del movimiento
        localLanguageId; //Id del idioma de la traducción (inglés por defecto)
    private final String shortEffect, //Descripción corta del efecto del movimiento
        effect; //Descripción más extensa del efecto del movimiento

    public MoveEffectProseCSV(int moveEffectId, int localLanguageId, String shortEffect, String effect) {
        this.moveEffectId = moveEffectId;
        this.localLanguageId = localLanguageId;
        this.shortEffect = shortEffect;
        this.effect = effect;
    }

    public int getMoveEffectId() {
        return moveEffectId;
    }

    public int getLocalLanguageId() {
        return localLanguageId;
    }

    public String getShortEffect() {
        return shortEffect;
    }

    public String getEffect() {
        return effect;
    }

    @Override
    public String toString() {
        return "MoveEffectProseCSV{" +
            "moveEffectId=" + moveEffectId +
            ", localLanguageId=" + localLanguageId +
            ", shortEffect='" + shortEffect + '\'' +
            ", effect='" + effect + '\'' +
            '}';
    }
}
