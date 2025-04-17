package com.origindex.testgame.csvimport.csvmodels.moves;

//Clase de importacion de moves.csv para la base de datos
public class MoveCSV {
    private int id;
    private String identifier;
    private int generation_id,type_id;
    private Integer power,pp,accuracy,priority,target_id,damage_class_id,effect_id,effect_chance,contest_type_id,contest_effect_id,super_contest_effect_id;

    public MoveCSV(int id, String identifier, int generation_id, int type_id, Integer power, Integer pp,
                   Integer accuracy, Integer priority, Integer target_id, Integer damage_class_id, Integer effect_id,
                   Integer effect_chance, Integer contest_type_id, Integer contest_effect_id, Integer super_contest_effect_id) {
        this.id = id;
        this.identifier = identifier;
        this.generation_id = generation_id;
        this.type_id = type_id;
        this.power = power;
        this.pp = pp;
        this.accuracy = accuracy;
        this.priority = priority;
        this.target_id = target_id;
        this.damage_class_id = damage_class_id;
        this.effect_id = effect_id;
        this.effect_chance = effect_chance;
        this.contest_type_id = contest_type_id;
        this.contest_effect_id = contest_effect_id;
        this.super_contest_effect_id = super_contest_effect_id;
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

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getPp() {
        return pp;
    }

    public void setPp(Integer pp) {
        this.pp = pp;
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getTarget_id() {
        return target_id;
    }

    public void setTarget_id(Integer target_id) {
        this.target_id = target_id;
    }

    public Integer getDamage_class_id() {
        return damage_class_id;
    }

    public void setDamage_class_id(Integer damage_class_id) {
        this.damage_class_id = damage_class_id;
    }

    public Integer getEffect_id() {
        return effect_id;
    }

    public void setEffect_id(Integer effect_id) {
        this.effect_id = effect_id;
    }

    public Integer getEffect_chance() {
        return effect_chance;
    }

    public void setEffect_chance(Integer effect_chance) {
        this.effect_chance = effect_chance;
    }

    public Integer getContest_type_id() {
        return contest_type_id;
    }

    public void setContest_type_id(Integer contest_type_id) {
        this.contest_type_id = contest_type_id;
    }

    public Integer getContest_effect_id() {
        return contest_effect_id;
    }

    public void setContest_effect_id(Integer contest_effect_id) {
        this.contest_effect_id = contest_effect_id;
    }

    public Integer getSuper_contest_effect_id() {
        return super_contest_effect_id;
    }

    public void setSuper_contest_effect_id(Integer super_contest_effect_id) {
        this.super_contest_effect_id = super_contest_effect_id;
    }

    @Override
    public String toString() {
        return "MovesCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", generation_id=" + generation_id +
            ", type_id=" + type_id +
            ", power=" + power +
            ", pp=" + pp +
            ", accuracy=" + accuracy +
            ", priority=" + priority +
            ", target_id=" + target_id +
            ", damage_class_id=" + damage_class_id +
            ", effect_id=" + effect_id +
            ", effect_chance=" + effect_chance +
            ", contest_type_id=" + contest_type_id +
            ", contest_effect_id=" + contest_effect_id +
            ", super_contest_effect_id=" + super_contest_effect_id +
            '}';
    }
}
