package com.origindex.testgame.csvimport.csvmodels;

//Clase de importación de stats.csv para la base de datos
public class StatCSV {
    private final int id;
    private final Integer damageClassId; //Tipo de daño del movimiento hasta la 3 gen (físico, especial o estado)
    private final String identifier; //Nombre del stat
    private final boolean isBattleOnly; //Indica si el stat solo tiene relevancia en batalla
    private final Integer gameIndex; //Índice del stat en el juego

    public StatCSV(int id, Integer damageClassId, String identifier, boolean isBattleOnly, Integer gameIndex) {
        this.id = id;
        this.damageClassId = damageClassId;
        this.identifier = identifier;
        this.isBattleOnly = isBattleOnly;
        this.gameIndex = gameIndex;
    }

    public int getId() {
        return id;
    }

    public Integer getDamageClassId() {
        return damageClassId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean isBattleOnly() {
        return isBattleOnly;
    }

    public Integer getGameIndex() {
        return gameIndex;
    }

    @Override
    public String toString() {
        return "StatCSV{" +
            "id=" + id +
            ", damageClassId=" + damageClassId +
            ", identifier='" + identifier + '\'' +
            ", isBattleOnly=" + isBattleOnly +
            ", gameIndex=" + gameIndex +
            '}';
    }
}
