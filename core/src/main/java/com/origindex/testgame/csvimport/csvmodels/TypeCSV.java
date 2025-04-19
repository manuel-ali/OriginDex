package com.origindex.testgame.csvimport.csvmodels;

//Clase de importación de types.csv para la base de datos
public class TypeCSV {
    private int id;
    private String identifier;
    private int generationId;
    private Integer damageClassId;

    public TypeCSV(int id, String identifier, int generationId, Integer damageClassId) {
        this.id = id;
        this.identifier = identifier;
        this.generationId = generationId;
        this.damageClassId = damageClassId;
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

    public int getGenerationId() {
        return generationId;
    }

    public void setGenerationId(int generationId) {
        this.generationId = generationId;
    }

    public Integer getDamageClassId() {
        return damageClassId;
    }

    public void setDamageClassId(Integer damageClassId) {
        this.damageClassId = damageClassId;
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
