package com.origindex.testgame.csvimport.csvmodels;

//Clase de importacion de sats.csv para la base de datos
public class StatCSV {
    private int id;
    private Integer damage_class_id;
    private String identifier;
    private boolean is_battle_only;
    private Integer game_index;

    public StatCSV(int id, Integer damage_class_id, String identifier, boolean is_battle_only, Integer game_index) {
        this.id = id;
        this.damage_class_id = damage_class_id;
        this.identifier = identifier;
        this.is_battle_only = is_battle_only;
        this.game_index = game_index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDamage_class_id() {
        return damage_class_id;
    }

    public void setDamage_class_id(Integer damage_class_id) {
        this.damage_class_id = damage_class_id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public boolean isBattleOnly() {
        return is_battle_only;
    }

    public void setIs_battle_only(boolean is_battle_only) {
        this.is_battle_only = is_battle_only;
    }

    public Integer getGame_index() {
        return game_index;
    }

    public void setGame_index(Integer game_index) {
        this.game_index = game_index;
    }

    @Override
    public String toString() {
        return "SatsCSV{" +
            "id=" + id +
            ", damage_class_id=" + damage_class_id +
            ", identifier='" + identifier + '\'' +
            ", is_battle_only=" + is_battle_only +
            ", game_index=" + game_index +
            '}';
    }
}
