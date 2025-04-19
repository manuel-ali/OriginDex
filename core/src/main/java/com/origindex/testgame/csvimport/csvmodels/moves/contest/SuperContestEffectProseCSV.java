package com.origindex.testgame.csvimport.csvmodels.moves.contest;

public class SuperContestEffectProseCSV {
    private int superContestEffectId, //Id del efecto del movimiento en el superconcurso
        localLanguageId; //Id del idioma de la traducción
    private String flavorText; //Texto descriptivo del movimiento en el superconcurso

    public SuperContestEffectProseCSV(int superContestEffectId, int localLanguageId, String flavorText) {
        this.superContestEffectId = superContestEffectId;
        this.localLanguageId = localLanguageId;
        this.flavorText = flavorText;
    }

    public int getSuperContestEffectId() {
        return superContestEffectId;
    }

    public void setSuperContestEffectId(int superContestEffectId) {
        this.superContestEffectId = superContestEffectId;
    }

    public int getLocalLanguageId() {
        return localLanguageId;
    }

    public void setLocalLanguageId(int localLanguageId) {
        this.localLanguageId = localLanguageId;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    @Override
    public String toString() {
        return "SuperContestEffectsProseCSV{" +
            "superContestEffectId=" + superContestEffectId +
            ", localLanguageId=" + localLanguageId +
            ", flavorText='" + flavorText + '\'' +
            '}';
    }
}
