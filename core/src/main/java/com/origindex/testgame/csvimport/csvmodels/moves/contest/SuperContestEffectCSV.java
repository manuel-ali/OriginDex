package com.origindex.testgame.csvimport.csvmodels.moves.contest;

public class SuperContestEffectCSV {
    private int id,
        appeal; //Puntos que gana el movimiento

    public SuperContestEffectCSV(int id, int appeal) {
        this.id = id;
        this.appeal = appeal;
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

    @Override
    public String toString() {
        return "SuperContestEffectsCSV{" +
            "id=" + id +
            ", appeal=" + appeal +
            '}';
    }
}
