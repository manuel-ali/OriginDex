package com.origindex.testgame.csvimport.csvmodels.moves.contest;

public class ContestEffectCSV {
    private int id,
        appeal, //Puntos que gana el movimiento
        jam; //Cuanto "molesta" el movimiento al oponente

    public ContestEffectCSV(int id, int appeal, int jam) {
        this.id = id;
        this.appeal = appeal;
        this.jam = jam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppeal() {
        return appeal;
    }

    public void setAppeal(int appeal) {
        this.appeal = appeal;
    }

    public int getJam() {
        return jam;
    }

    public void setJam(int jam) {
        this.jam = jam;
    }

    @Override
    public String toString() {
        return "ContestEffectsCSV{" +
            "id=" + id +
            ", appeal=" + appeal +
            ", jam=" + jam +
            '}';
    }
}
