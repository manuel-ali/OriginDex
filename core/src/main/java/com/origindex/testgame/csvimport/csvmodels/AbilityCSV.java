package com.origindex.testgame.csvimport.csvmodels;

//Clase de importación de abilities.csv para la base de datos
public class AbilityCSV {
    private final int id;
    private final String identifier; //Nombre de la habilidad
    private final int generationId; //Id de la generación en la que se introdujo la habilidad
    private final boolean isMainSeries; //Indica si es parte de la serie principal de juegos

    public AbilityCSV(int id, String identifier, int generationId, boolean isMainSeries) {
        this.id = id;
        this.identifier = identifier;
        this.generationId = generationId;
        this.isMainSeries = isMainSeries;
    }

    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getGenerationId() {
        return generationId;
    }

    public boolean isMainSeries() {
        return isMainSeries;
    }

    @Override
    public String toString() {
        return "AbilityCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", generationId=" + generationId +
            ", isMainSeries=" + isMainSeries +
            '}';
    }
}
