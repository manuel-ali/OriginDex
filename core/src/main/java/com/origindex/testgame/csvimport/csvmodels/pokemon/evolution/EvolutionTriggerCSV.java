package com.origindex.testgame.csvimport.csvmodels.pokemon.evolution;

public class EvolutionTriggerCSV {
    private int id;
    private String identifier; //Nombre del trigger de evolución (nivel, piedra, etc.)

    public EvolutionTriggerCSV(int id, String identifier) {
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
        return "EvolutionTriggerCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
