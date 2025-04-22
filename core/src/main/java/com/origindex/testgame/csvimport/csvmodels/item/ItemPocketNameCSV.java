package com.origindex.testgame.csvimport.csvmodels.item;

public class ItemPocketNameCSV {
    private final int itemPocketId, //Id del bolsillo del objeto
        localLanguageId; //Id del idioma de la traducción
    private final String name; //Nombre del bolsillo del objeto

    public ItemPocketNameCSV(int itemPocketId, int localLanguageId, String name) {
        this.itemPocketId = itemPocketId;
        this.localLanguageId = localLanguageId;
        this.name = name;
    }

    public int getItemPocketId() {
        return itemPocketId;
    }

    public int getLocalLanguageId() {
        return localLanguageId;
    }

    public String getName() {
        return name;
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
