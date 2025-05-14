package com.origindex.testgame.game.model;

import java.util.List;

public class Move {
    private int id;
    private String identifier;
    private Generation generation;
    private Type type;
    private Integer power, pp, accuracy;
    private int priority;
    private MoveTarget target;
    private MoveDamageClass damageClass;
    //private MoveEffect effect;
    private Integer effectChance;
    private MoveMeta meta;
    private List<MoveMetaStatChange> statChanges;

    public Move(int id, String identifier, Generation generation, Type type, Integer power, Integer pp, Integer accuracy,
                int priority, MoveTarget target, MoveDamageClass damageClass, Integer effectChance,
                MoveMeta meta, List<MoveMetaStatChange> statChanges) {
        this.id = id;
        this.identifier = identifier;
        this.generation = generation;
        this.type = type;
        this.power = power;
        this.pp = pp;
        this.accuracy = accuracy;
        this.priority = priority;
        this.target = target;
        this.damageClass = damageClass;
        this.effectChance = effectChance;
        this.meta = meta;
        this.statChanges = statChanges;
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

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public MoveTarget getTarget() {
        return target;
    }

    public void setTarget(MoveTarget target) {
        this.target = target;
    }

    public MoveDamageClass getDamageClass() {
        return damageClass;
    }

    public void setDamageClass(MoveDamageClass damageClass) {
        this.damageClass = damageClass;
    }

    public Integer getEffectChance() {
        return effectChance;
    }

    public void setEffectChance(Integer effectChance) {
        this.effectChance = effectChance;
    }

    public MoveMeta getMeta() {
        return meta;
    }

    public void setMeta(MoveMeta meta) {
        this.meta = meta;
    }

    public List<MoveMetaStatChange> getStatChanges() {
        return statChanges;
    }

    public void setStatChanges(List<MoveMetaStatChange> statChanges) {
        this.statChanges = statChanges;
    }

    @Override
    public String toString() {
        return "Move{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", generation=" + generation +
            ", type=" + type +
            ", power=" + power +
            ", pp=" + pp +
            ", accuracy=" + accuracy +
            ", priority=" + priority +
            ", target=" + target +
            ", damageClass=" + damageClass +
            ", effectChance=" + effectChance +
            ", meta=" + meta +
            ", statChanges=" + statChanges +
            '}';
    }
}
