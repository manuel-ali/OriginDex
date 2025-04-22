package com.origindex.testgame.csvimport.csvmodels;

//Clase de importación de types.csv para la base de datos
public class TypeCSV {
    private final int id;
    private final String identifier; //Nombre del tipo
    private final int generationId; //Id de la generación en la que se introdujo el tipo
    private final Integer damageClassId; //Id de la clase de daño del tipo hasta 3 gen (fisico, especial o estado)

    public TypeCSV(int id, String identifier, int generationId, Integer damageClassId) {
        this.id = id;
        this.identifier = identifier;
        this.generationId = generationId;
        this.damageClassId = damageClassId;
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

    public Integer getDamageClassId() {
        return damageClassId;
    }

    @Override
    public String toString() {
        return "TypesCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", generationId=" + generationId +
            ", damageClassId=" + damageClassId +
            '}';
    }
}
