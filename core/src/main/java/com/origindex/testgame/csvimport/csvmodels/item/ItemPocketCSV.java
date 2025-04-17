package com.origindex.testgame.csvimport.csvmodels.item;

public class ItemPocketCSV {
    private int id;
    private String identifier; //Nombre del bolsillo en la que va el objeto (pokeballs, machines, etc)

    public ItemPocketCSV(int id, String identifier) {
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
        return "ItemPocketsCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
