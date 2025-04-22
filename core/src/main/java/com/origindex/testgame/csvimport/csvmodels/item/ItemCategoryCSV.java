package com.origindex.testgame.csvimport.csvmodels.item;

public class ItemCategoryCSV {
    private final int id,
        pocketId; //Id del bolsillo en el que se encuentra el objeto dentro de la mochila
    private final String identifier; //Categoría del objeto (stat-boosts, medicine, etc)

    public ItemCategoryCSV(int id, int pocketId, String identifier) {
        this.id = id;
        this.pocketId = pocketId;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public int getPocketId() {
        return pocketId;
    }

    public String getIdentifier() {
        return identifier;
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
