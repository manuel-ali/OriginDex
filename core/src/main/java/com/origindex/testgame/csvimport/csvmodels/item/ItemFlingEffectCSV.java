package com.origindex.testgame.csvimport.csvmodels.item;

public class ItemFlingEffectCSV {
    private final int id;
    private final String identifier; //Efecto del movimiento al lanzar el objeto (Veneno, parálisis, etc.)

    public ItemFlingEffectCSV(int id, String identifier) {
        this.id = id;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "ItemFlingEffectCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
