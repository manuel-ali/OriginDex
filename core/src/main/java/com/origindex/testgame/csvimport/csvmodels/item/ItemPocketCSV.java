package com.origindex.testgame.csvimport.csvmodels.item;

public class ItemPocketCSV {
    private final int id;
    private final String identifier; //Nombre del bolsillo en la que va el objeto (Poke balls, Machines, etc.)

    public ItemPocketCSV(int id, String identifier) {
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
        return "ItemPocketsCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
