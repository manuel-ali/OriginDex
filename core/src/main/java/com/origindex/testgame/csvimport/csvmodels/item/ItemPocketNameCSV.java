package com.origindex.testgame.csvimport.csvmodels.item;

public class ItemPocketNameCSV {
    private int itemPocketId, //ID del bolsillo del objeto
        localLanguageId; //ID del idioma de la traducción
    private String name; //Nombre del bolsillo del objeto

    public ItemPocketNameCSV(int itemPocketId, int localLanguageId, String name) {
        this.itemPocketId = itemPocketId;
        this.localLanguageId = localLanguageId;
        this.name = name;
    }

    public int getItemPocketId() {
        return itemPocketId;
    }

    public void setItemPocketId(int itemPocketId) {
        this.itemPocketId = itemPocketId;
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
        return "ItemPocketNameCSV{" +
            "itemPocketId=" + itemPocketId +
            ", localLanguageId=" + localLanguageId +
            ", name='" + name + '\'' +
            '}';
    }
}
