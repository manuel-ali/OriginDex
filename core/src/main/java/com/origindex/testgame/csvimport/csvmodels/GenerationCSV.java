package com.origindex.testgame.csvimport.csvmodels;

public class GenerationCSV {
    private int id;
    private int main_region_id; //ID de la region principal de la generacion
    private String identifier; //Nombre de la generacion

    public GenerationCSV(int id, int main_region_id, String identifier) {
        this.id = id;
        this.main_region_id = main_region_id;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMain_region_id() {
        return main_region_id;
    }

    public void setMain_region_id(int main_region_id) {
        this.main_region_id = main_region_id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "GenerationsCSV{" +
            "id=" + id +
            ", main_region_id=" + main_region_id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
