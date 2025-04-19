package com.origindex.testgame.csvimport.csvmodels.item;

public class ItemCategoryProseCSV {
    private int itemCategoryId, //Id de la categoría del objeto
        localLanguageId;
    private String name; //Nombre de la categoría del objeto en el idioma local

    public ItemCategoryProseCSV(int itemCategoryId, int localLanguageId, String name) {
        this.itemCategoryId = itemCategoryId;
        this.localLanguageId = localLanguageId;
        this.name = name;
    }

    public int getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(int itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public int getLocalLanguageId() {
        return localLanguageId;
    }

    public void setLocalLanguageId(int localLanguageId) {
        this.localLanguageId = localLanguageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ItemCategroyProseCSV{" +
            "itemCategoryId=" + itemCategoryId +
            ", localLanguageId=" + localLanguageId +
            ", name='" + name + '\'' +
            '}';
    }
}
