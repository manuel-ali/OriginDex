package com.origindex.testgame.csvimport.csvmodels.pokemon.evolution;

public class EvolutionChainCSV {
    private int id,
        babyTriggerItemId; //Id del ítem necesario que tiene que tener uno de los padres al criar para que este nazca, (Azurill solo nace si uno de los padres tiene equipado el Sea Incense)

    public EvolutionChainCSV(int id, int babyTriggerItemId) {
        this.id = id;
        this.babyTriggerItemId = babyTriggerItemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBabyTriggerItemId() {
        return babyTriggerItemId;
    }

    public void setBabyTriggerItemId(int babyTriggerItemId) {
        this.babyTriggerItemId = babyTriggerItemId;
    }

    @Override
    public String toString() {
        return "EvolutionChainCSV{" +
            "id=" + id +
            ", babyTriggerItemId=" + babyTriggerItemId +
            '}';
    }
}
