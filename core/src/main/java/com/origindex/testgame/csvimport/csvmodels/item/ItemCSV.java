package com.origindex.testgame.csvimport.csvmodels.item;

public class ItemCSV {
    private int id;
    private String identifier; //Nombre del objeto
    private int categoryId, //ID de la categoria del objeto
        cost; //Precio del objeto
    private Integer flingPower, //Potencia que hace el objeto al lanzarlo cuando el pokemon que lo lleva equipado utiliza el movimiento Fling
        flingEffectId; //ID del efecto especial que hace el objeto al lanzarlo cuando el pokemon que lo lleva equipado utiliza el movimiento Fling

    public ItemCSV(int id, String identifier, int categoryId, int cost, Integer flingPower, Integer flingEffectId) {
        this.id = id;
        this.identifier = identifier;
        this.categoryId = categoryId;
        this.cost = cost;
        this.flingPower = flingPower;
        this.flingEffectId = flingEffectId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Integer getFlingPower() {
        return flingPower;
    }

    public void setFlingPower(Integer flingPower) {
        this.flingPower = flingPower;
    }

    public Integer getFlingEffectId() {
        return flingEffectId;
    }

    public void setFlingEffectId(Integer flingEffectId) {
        this.flingEffectId = flingEffectId;
    }

    @Override
    public String toString() {
        return "ItemCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", categoryId=" + categoryId +
            ", cost=" + cost +
            ", flingPower=" + flingPower +
            ", flingEffectId=" + flingEffectId +
            '}';
    }
}
