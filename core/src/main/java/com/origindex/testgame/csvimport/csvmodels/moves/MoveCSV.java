package com.origindex.testgame.csvimport.csvmodels.moves;

//Clase de importación de moves.csv para la base de datos
public class MoveCSV {
    private final int id;
    private final String identifier;
    private final int generationId, //Id de la generación en la que se introdujo el movimiento
        typeId; //Id del tipo de movimiento (agua, fuego, etc.)
    private final Integer power, //Potencia del movimiento
        pp, //Puntos de poder del movimiento (veces que se puede usar el movimiento)
        accuracy; //Precisión del movimiento (probabilidad de que acierte)
    private final int priority, //Prioridad del movimiento (si es más rápido que el movimiento del oponente)
        targetId, //Id del objetivo del movimiento (enemigo, aliado, todos, etc.)
        damageClassId; //Id de la clase de daño del movimiento (físico, especial, estado)
    private final Integer effectId, //Id de la descripción del efecto del movimiento
        effectChance; //Probabilidad de que se produzca el efecto secundario del movimiento (quemar, hacer retroceder, etc.)

    public MoveCSV(int id, String identifier, int generationId, int typeId, Integer power, Integer pp,
                   Integer accuracy, int priority, int targetId, int damageClassId, Integer effectId,
                   Integer effectChance) {
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

    public int getPriority() {
        return priority;
    }

    public int getTargetId() {
        return targetId;
    }

    public int getDamageClassId() {
        return damageClassId;
    }

    public Integer getEffectId() {
        return effectId;
    }

    public Integer getEffectChance() {
        return effectChance;
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
            '}';
    }
}
