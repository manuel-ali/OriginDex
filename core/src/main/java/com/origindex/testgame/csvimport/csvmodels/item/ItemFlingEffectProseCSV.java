package com.origindex.testgame.csvimport.csvmodels.item;

public class ItemFlingEffectProseCSV {
    private int id;
    private int localLanguageId; //Idioma del texto
    private String effect; //Texto del efecto del objeto al lanzarlo

    public ItemFlingEffectProseCSV(int id, int localLanguageId, String effect) {
        this.id = id;
        this.localLanguageId = localLanguageId;
        this.effect = effect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocalLanguageId() {
        return localLanguageId;
    }

    public void setLocalLanguageId(int localLanguageId) {
        this.localLanguageId = localLanguageId;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "ItemFlingEffectProseCSV{" +
            "id=" + id +
            ", localLanguageId=" + localLanguageId +
            ", effect='" + effect + '\'' +
            '}';
    }
}
