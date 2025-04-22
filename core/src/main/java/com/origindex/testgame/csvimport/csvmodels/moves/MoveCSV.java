package com.origindex.testgame.csvimport.csvmodels.moves;

//Clase de importación de moves.csv para la base de datos
public class MoveCSV {
    private final int id;
    private final String identifier;
    private final int generationId, //Id de la generación en la que se introdujo el movimiento
        typeId; //Id del tipo de movimiento (agua, fuego, etc.)
    private final Integer power, //Potencia del movimiento
        pp, //Puntos de poder del movimiento (veces que se puede usar el movimiento)
        accuracy, //Precisión del movimiento (probabilidad de que acierte)
        priority, //Prioridad del movimiento (si es más rápido que el movimiento del oponente)
        targetId, //Id del objetivo del movimiento (enemigo, aliado, todos, etc.)
        damageClassId, //Id de la clase de daño del movimiento (físico, especial, estado)
        effectId, //Id de la descripción del efecto del movimiento
        effectChance, //Probabilidad de que se produzca el efecto secundario del movimiento (quemar, hacer retroceder, etc.)
        contestTypeId, //Id del tipo de concurso del movimiento (si es un movimiento de concurso)
        contestEffectId, //Id del efecto del movimiento en el concurso (si es un movimiento de concurso)
        superContestEffectId; //Id del efecto del movimiento en el super concurso (si es un movimiento de concurso)

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

    public String getIdentifier() {
        return identifier;
    }

    public int getGenerationId() {
        return generationId;
    }

    public int getTypeId() {
        return typeId;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getPp() {
        return pp;
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public Integer getPriority() {
        return priority;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public Integer getDamageClassId() {
        return damageClassId;
    }

    public Integer getEffectId() {
        return effectId;
    }

    public Integer getEffectChance() {
        return effectChance;
    }

    public Integer getContestTypeId() {
        return contestTypeId;
    }

    public Integer getContestEffectId() {
        return contestEffectId;
    }

    public Integer getSuperContestEffectId() {
        return superContestEffectId;
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
