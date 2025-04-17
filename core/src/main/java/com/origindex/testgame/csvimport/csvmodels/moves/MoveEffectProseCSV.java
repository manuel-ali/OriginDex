package com.origindex.testgame.csvimport.csvmodels.moves;

public class MoveEffectProseCSV {
    private int move_effect_id; //ID del efecto del movimiento
    private int local_language_id; //ID del idioma de la traduccion (inglés por defecto)
    private String short_effect; //Descripción corta del efecto del movimiento
    private String effect; //Descripción mas extensa del efecto del movimiento

    public MoveEffectProseCSV(int move_effect_id, int local_language_id, String short_effect, String effect) {
        this.move_effect_id = move_effect_id;
        this.local_language_id = local_language_id;
        this.short_effect = short_effect;
        this.effect = effect;
    }

    public int getMove_effect_id() {
        return move_effect_id;
    }

    public void setMove_effect_id(int move_effect_id) {
        this.move_effect_id = move_effect_id;
    }

    public int getLocal_language_id() {
        return local_language_id;
    }

    public void setLocal_language_id(int local_language_id) {
        this.local_language_id = local_language_id;
    }

    public String getShort_effect() {
        return short_effect;
    }

    public void setShort_effect(String short_effect) {
        this.short_effect = short_effect;
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
            "move_effect_id=" + move_effect_id +
            ", local_language_id=" + local_language_id +
            ", short_effect='" + short_effect + '\'' +
            ", effect='" + effect + '\'' +
            '}';
    }
}
