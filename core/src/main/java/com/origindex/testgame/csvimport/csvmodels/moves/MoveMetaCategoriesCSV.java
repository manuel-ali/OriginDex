package com.origindex.testgame.csvimport.csvmodels.moves;

public class MoveMetaCategoriesCSV {
    private final int id;
    private final String identifier;

    public MoveMetaCategoriesCSV(int id, String identifier) {
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
        return "MoveMetaCategoriesCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
