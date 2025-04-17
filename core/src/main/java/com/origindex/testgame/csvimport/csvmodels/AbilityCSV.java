package com.origindex.testgame.csvimport.csvmodels;

//Clase de importacion de abilities.csv para la base de datos
public class AbilityCSV {
    private int id;
    private String identifier;
    private int generation_id;
    private boolean is_main_series;

    public AbilityCSV(int id, String identifier, int generation_id, boolean is_main_series) {
        this.id = id;
        this.identifier = identifier;
        this.generation_id = generation_id;
        this.is_main_series = is_main_series;
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

    public boolean isMainSeries() {
        return is_main_series;
    }

    public void setIs_main_series(boolean is_main_series) {
        this.is_main_series = is_main_series;
    }

    @Override
    public String toString() {
        return "AbilitiesCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", generation_id=" + generation_id +
            ", is_main_series=" + is_main_series +
            '}';
    }
}
