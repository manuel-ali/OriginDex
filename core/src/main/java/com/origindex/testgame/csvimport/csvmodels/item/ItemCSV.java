package com.origindex.testgame.csvimport.csvmodels.item;

public class ItemCSV {
    private final int id;
    private final String identifier; //Nombre del objeto
    private final int categoryId, //Id de la categoría del objeto (stat-boosts, medicine, etc.)
        cost; //Precio del objeto
    private final Integer flingPower, //Potencia que hace el objeto al lanzarlo cuando el pokemon que lo lleva equipado utiliza el movimiento Fling
        flingEffectId; //Id del efecto especial que hace el objeto al lanzarlo cuando el pokemon que lo lleva equipado utiliza el movimiento Fling

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

    public String getIdentifier() {
        return identifier;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getCost() {
        return cost;
    }

    public Integer getFlingPower() {
        return flingPower;
    }

    public Integer getFlingEffectId() {
        return flingEffectId;
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
