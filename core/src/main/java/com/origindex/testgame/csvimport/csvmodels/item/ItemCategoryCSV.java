package com.origindex.testgame.csvimport.csvmodels.item;

public class ItemCategoryCSV {
    private int id,
        pocketId; //ID del bolsillo en el que se encuentra el objeto dentro de la mochila
    private String identifier; //Categoria del objeto (Pociones, Pokeballs, etc)

    public ItemCategoryCSV(int id, int pocketId, String identifier) {
        this.id = id;
        this.pocketId = pocketId;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPocketId() {
        return pocketId;
    }

    public void setPocketId(int pocketId) {
        this.pocketId = pocketId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "ItemCategoryCSV{" +
            "id=" + id +
            ", pocketId=" + pocketId +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
