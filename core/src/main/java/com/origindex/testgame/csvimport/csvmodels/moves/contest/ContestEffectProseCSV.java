package com.origindex.testgame.csvimport.csvmodels.moves.contest;

public class ContestEffectProseCSV {
    private int contestEffectID, //ID del efecto del movimiento en el concurso
        localLanguageID; //ID del idioma para la traduccion
    private String flavorText, //Texto descriptivo del movimiento en el concurso
        effect; //Texto del efecto del movimiento en el concurso

    public ContestEffectProseCSV(int contestEffectID, int localLanguageID, String flavorText, String effect) {
        this.contestEffectID = contestEffectID;
        this.localLanguageID = localLanguageID;
        this.flavorText = flavorText;
        this.effect = effect;
    }

    public int getContestEffectID() {
        return contestEffectID;
    }

    public void setContestEffectID(int contestEffectID) {
        this.contestEffectID = contestEffectID;
    }

    public int getLocalLanguageID() {
        return localLanguageID;
    }

    public void setLocalLanguageID(int localLanguageID) {
        this.localLanguageID = localLanguageID;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "ContestEffectsProseCSV{" +
            "contestEffectID=" + contestEffectID +
            ", localLanguageID=" + localLanguageID +
            ", flavorText='" + flavorText + '\'' +
            ", effect='" + effect + '\'' +
            '}';
    }
}
