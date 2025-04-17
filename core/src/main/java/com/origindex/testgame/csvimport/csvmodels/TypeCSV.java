package com.origindex.testgame.csvimport.csvmodels;

//Clase de importacion de types.csv para la base de datos
public class TypeCSV {
    private int id;
    private String identifier;
    private int generation_id;
    private Integer damage_class_id;

    public TypeCSV(int id, String identifier, int generation_id, Integer damage_class_id) {
        this.id = id;
        this.identifier = identifier;
        this.generation_id = generation_id;
        this.damage_class_id = damage_class_id;
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

    public int getGeneration_id() {
        return generation_id;
    }

    public void setGeneration_id(int generation_id) {
        this.generation_id = generation_id;
    }

    public Integer getDamage_class_id() {
        return damage_class_id;
    }

    public void setDamage_class_id(Integer damage_class_id) {
        this.damage_class_id = damage_class_id;
    }

    @Override
    public String toString() {
        return "TypesCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", generation_id=" + generation_id +
            ", damage_class_id=" + damage_class_id +
            '}';
    }
}
