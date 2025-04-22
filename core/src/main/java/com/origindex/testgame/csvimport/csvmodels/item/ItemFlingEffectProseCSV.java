package com.origindex.testgame.csvimport.csvmodels.item;

public class ItemFlingEffectProseCSV {
    private final int id,
        localLanguageId; //Idioma del texto
    private final String effect; //Texto del efecto del objeto al lanzarlo

    public ItemFlingEffectProseCSV(int id, int localLanguageId, String effect) {
        this.id = id;
        this.localLanguageId = localLanguageId;
        this.effect = effect;
    }

    public int getId() {
        return id;
    }

    public int getLocalLanguageId() {
        return localLanguageId;
    }

    public String getEffect() {
        return effect;
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
