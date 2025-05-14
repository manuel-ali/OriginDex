package com.origindex.testgame.csvimport.csvmodels.moves;

public class MoveMetaAilmentCSV {
    private final int id;
    private final String identifier;

    public MoveMetaAilmentCSV(int id, String identifier) {
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
        return "MoveMetaAilmentCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            '}';
    }
}
