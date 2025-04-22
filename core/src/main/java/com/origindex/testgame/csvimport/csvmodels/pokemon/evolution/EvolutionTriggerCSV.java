package com.origindex.testgame.csvimport.csvmodels.pokemon.evolution;

public class EvolutionTriggerCSV {
    private final int id;
    private final String identifier; //Nombre del trigger de evolución (nivel, piedra, etc.)

    public EvolutionTriggerCSV(int id, String identifier) {
        this.id = id;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "EvolutionTriggerCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
