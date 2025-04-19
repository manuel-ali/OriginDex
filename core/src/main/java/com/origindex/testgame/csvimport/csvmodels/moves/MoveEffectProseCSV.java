package com.origindex.testgame.csvimport.csvmodels.moves;

public class MoveEffectProseCSV {
    private int moveEffectId; //Id del efecto del movimiento
    private int localLanguageId; //Id del idioma de la traducción (inglés por defecto)
    private String shortEffect; //Descripción corta del efecto del movimiento
    private String effect; //Descripción más extensa del efecto del movimiento

    public MoveEffectProseCSV(int moveEffectId, int localLanguageId, String shortEffect, String effect) {
        this.moveEffectId = moveEffectId;
        this.localLanguageId = localLanguageId;
        this.shortEffect = shortEffect;
        this.effect = effect;
    }

    public int getMoveEffectId() {
        return moveEffectId;
    }

    public void setMoveEffectId(int moveEffectId) {
        this.moveEffectId = moveEffectId;
    }

    public int getLocalLanguageId() {
        return localLanguageId;
    }

    public void setLocalLanguageId(int localLanguageId) {
        this.localLanguageId = localLanguageId;
    }

    public String getShortEffect() {
        return shortEffect;
    }

    public void setShortEffect(String shortEffect) {
        this.shortEffect = shortEffect;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
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
