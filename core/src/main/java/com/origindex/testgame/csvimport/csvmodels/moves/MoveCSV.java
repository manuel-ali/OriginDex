package com.origindex.testgame.csvimport.csvmodels.moves;

//Clase de importación de moves.csv para la base de datos
public class MoveCSV {
    private int id;
    private String identifier;
    private int generationId,typeId;
    private Integer power,pp,accuracy,priority,targetId,damageClassId,effectId,effectChance,contestTypeId,contestEffectId,superContestEffectId;

    public MoveCSV(int id, String identifier, int generationId, int typeId, Integer power, Integer pp,
                   Integer accuracy, Integer priority, Integer targetId, Integer damageClassId, Integer effectId,
                   Integer effectChance, Integer contestTypeId, Integer contestEffectId, Integer superContestEffectId) {
        this.id = id;
        this.identifier = identifier;
        this.generationId = generationId;
        this.typeId = typeId;
        this.power = power;
        this.pp = pp;
        this.accuracy = accuracy;
        this.priority = priority;
        this.targetId = targetId;
        this.damageClassId = damageClassId;
        this.effectId = effectId;
        this.effectChance = effectChance;
        this.contestTypeId = contestTypeId;
        this.contestEffectId = contestEffectId;
        this.superContestEffectId = superContestEffectId;
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

    public int getGenerationId() {
        return generationId;
    }

    public void setGenerationId(int generationId) {
        this.generationId = generationId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getDamageClassId() {
        return damageClassId;
    }

    public void setDamageClassId(Integer damageClassId) {
        this.damageClassId = damageClassId;
    }

    public Integer getEffectId() {
        return effectId;
    }

    public void setEffectId(Integer effectId) {
        this.effectId = effectId;
    }

    public Integer getEffectChance() {
        return effectChance;
    }

    public void setEffectChance(Integer effectChance) {
        this.effectChance = effectChance;
    }

    public Integer getContestTypeId() {
        return contestTypeId;
    }

    public void setContestTypeId(Integer contestTypeId) {
        this.contestTypeId = contestTypeId;
    }

    public Integer getContestEffectId() {
        return contestEffectId;
    }

    public void setContestEffectId(Integer contestEffectId) {
        this.contestEffectId = contestEffectId;
    }

    public Integer getSuperContestEffectId() {
        return superContestEffectId;
    }

    public void setSuperContestEffectId(Integer superContestEffectId) {
        this.superContestEffectId = superContestEffectId;
    }

    @Override
    public String toString() {
        return "MoveCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", generationId=" + generationId +
            ", typeId=" + typeId +
            ", power=" + power +
            ", pp=" + pp +
            ", accuracy=" + accuracy +
            ", priority=" + priority +
            ", targetId=" + targetId +
            ", damageClassId=" + damageClassId +
            ", effectId=" + effectId +
            ", effectChance=" + effectChance +
            ", contestTypeId=" + contestTypeId +
            ", contestEffectId=" + contestEffectId +
            ", superContestEffectId=" + superContestEffectId +
            '}';
    }
}
