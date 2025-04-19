package com.origindex.testgame.csvimport.csvmodels.item;

public class ItemFlingEffectCSV {
    private int id;
    private String identifier; //Efecto del movimiento al lanzar el objeto (Veneno, parálisis, etc.)

    public ItemFlingEffectCSV(int id, String identifier) {
        this.id = id;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "ItemFlingEffectCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
