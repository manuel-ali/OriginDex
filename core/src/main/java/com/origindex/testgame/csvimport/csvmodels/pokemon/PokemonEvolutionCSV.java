package com.origindex.testgame.csvimport.csvmodels.pokemon;

/** Clase de importación de pokemon_evolution.csv para la base de datos.
 * Esta clase contiene al pokemon al que evoluciona y los requisitos para que evolucione.
 */
public class PokemonEvolutionCSV {
    private final int id,
        evolvedSpeciesId, //Id del pokemon al que evoluciona
        evolutionTriggerId; //Id de la forma en la que se da la evolución (nivel, piedra, etc.)
    private final Integer triggerItemId, //Id del objeto que se necesita para evolucionar
        minimumLevel, //Nivel mínimo necesario para evolucionar
        genderId, //Género que tiene que tener para evolucionar
        locationId, //Id de la localización donde se da la evolución
        heldItemId; //Id del objeto que tiene que llevar el pokemon para evolucionar
    private final String timeOfDay; //Momento del día en el que se da la evolución (día/noche)
    private final Integer knownMoveId, //Id del movimiento que tiene que conocer el pokemon para evolucionar
        knownMoveTypeId, //Id del tipo del movimiento que tiene que conocer el pokemon para evolucionar (agua, fuego, etc.)
        minimumHappiness, //Felicidad minima necesaria para evolucionar
        minimumBeauty, //Belleza minima necesaria para evolucionar
        minimumAffection, //Afecto mínimo necesario para evolucionar
        relativePhysicalStats, //Id cómo deben estar relacionadas las stats de Ataque y Defensa para que pueda evolucionar. (Ataque > Defensa | Ataque < Defensa | Ataque = Defensa)
        partySpeciesId, //Id del pokemon que tiene que estar en el equipo para que el pokemon evolucione
        partyTypeId, //Id del tipo del pokemon que tiene que estar en el equipo para que el pokemon evolucione
        tradeSpeciesId; //Id del pokemon que tiene que intercambiarse para que el pokemon evolucione
    private final boolean needsOverworldRain, //Si necesita lluvia en el mapa para evolucionar
        turnUpsideDown; //Indica si para evolucionar al pokemon se ha de girar la consola físicamente hacia abajo durante la subida de nivel

    public PokemonEvolutionCSV(int id, int evolvedSpeciesId, int evolutionTriggerId, Integer triggerItemId, Integer minimumLevel,
                               Integer genderId, Integer locationId, Integer heldItemId, String timeOfDay, Integer knownMoveId,
                               Integer knownMoveTypeId, Integer minimumHappiness, Integer minimumBeauty, Integer minimumAffection,
                               Integer relativePhysicalStats, Integer partySpeciesId, Integer partyTypeId, Integer tradeSpeciesId, boolean needsOverworldRain, boolean turnUpsideDown) {
        this.id = id;
        this.evolvedSpeciesId = evolvedSpeciesId;
        this.evolutionTriggerId = evolutionTriggerId;
        this.triggerItemId = triggerItemId;
        this.minimumLevel = minimumLevel;
        this.genderId = genderId;
        this.locationId = locationId;
        this.heldItemId = heldItemId;
        this.timeOfDay = timeOfDay;
        this.knownMoveId = knownMoveId;
        this.knownMoveTypeId = knownMoveTypeId;
        this.minimumHappiness = minimumHappiness;
        this.minimumBeauty = minimumBeauty;
        this.minimumAffection = minimumAffection;
        this.relativePhysicalStats = relativePhysicalStats;
        this.partySpeciesId = partySpeciesId;
        this.partyTypeId = partyTypeId;
        this.tradeSpeciesId = tradeSpeciesId;
        this.needsOverworldRain = needsOverworldRain;
        this.turnUpsideDown = turnUpsideDown;
    }

    public int getId() {
        return id;
    }

    public int getEvolvedSpeciesId() {
        return evolvedSpeciesId;
    }

    public int getEvolutionTriggerId() {
        return evolutionTriggerId;
    }

    public Integer getTriggerItemId() {
        return triggerItemId;
    }

    public Integer getMinimumLevel() {
        return minimumLevel;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public Integer getHeldItemId() {
        return heldItemId;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public Integer getKnownMoveId() {
        return knownMoveId;
    }

    public Integer getKnownMoveTypeId() {
        return knownMoveTypeId;
    }

    public Integer getMinimumHappiness() {
        return minimumHappiness;
    }

    public Integer getMinimumBeauty() {
        return minimumBeauty;
    }

    public Integer getMinimumAffection() {
        return minimumAffection;
    }

    public Integer getRelativePhysicalStats() {
        return relativePhysicalStats;
    }

    public Integer getPartySpeciesId() {
        return partySpeciesId;
    }

    public Integer getPartyTypeId() {
        return partyTypeId;
    }

    public Integer getTradeSpeciesId() {
        return tradeSpeciesId;
    }

    public boolean isNeedsOverworldRain() {
        return needsOverworldRain;
    }

    public boolean isTurnUpsideDown() {
        return turnUpsideDown;
    }

    @Override
    public String toString() {
        return "PokemonEvolutionCSV{" +
            "id=" + id +
            ", evolvedSpeciesId=" + evolvedSpeciesId +
            ", evolutionTriggerId=" + evolutionTriggerId +
            ", triggerItemId=" + triggerItemId +
            ", minimumLevel=" + minimumLevel +
            ", genderId=" + genderId +
            ", locationId=" + locationId +
            ", heldItemId=" + heldItemId +
            ", timeOfDay='" + timeOfDay + '\'' +
            ", knownMoveId=" + knownMoveId +
            ", knownMoveTypeId=" + knownMoveTypeId +
            ", minimumHappiness=" + minimumHappiness +
            ", minimumBeauty=" + minimumBeauty +
            ", minimumAffection=" + minimumAffection +
            ", relativePhysicalStats=" + relativePhysicalStats +
            ", partySpeciesId=" + partySpeciesId +
            ", partyTypeId=" + partyTypeId +
            ", tradeSpeciesId=" + tradeSpeciesId +
            ", needsOverworldRain=" + needsOverworldRain +
            ", turnUpsideDown=" + turnUpsideDown +
            '}';
    }
}
