package com.origindex.testgame.csvimport.csvmodels;

public class RegionCSV {
    private final int id;
    private final String identifier; //Nombre de la region (Kanto, Johto, etc.)

    public RegionCSV(int id, String identifier) {
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
        return "RegionCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
