package com.origindex.testgame.csvimport.csvmodels.pokemon;

/** Clase de importación de pokemon_evolution.csv para la base de datos.
 * Esta clase contiene al pokemon al que evoluciona y los requisitos para que evolucione.
 */
public class PokemonEvolutionCSV {
    private int id;
    private int evolvedSpeciesId; //Id del pokemon al que evoluciona
    private int evolutionTriggerId; //Id de la forma en la que se da la evolución (nivel, piedra, etc.)
    private Integer triggerItemId, //Id del objeto que se necesita para evolucionar
        minimumLevel, //Nivel mínimo necesario para evolucionar
        genderId, //Género que tiene que tener para evolucionar
        locationId, //Id de la localización donde se da la evolución
        heldItemId; //Id del objeto que tiene que llevar el pokemon para evolucionar
    private String timeOfDay; //Momento del día en el que se da la evolución (día/noche)
    private Integer knownMoveId, //Id del movimiento que tiene que conocer el pokemon para evolucionar
        knownMoveTypeId, //Id del tipo del movimiento que tiene que conocer el pokemon para evolucionar (agua, fuego, etc.)
        minimumHappiness, //Felicidad minima necesaria para evolucionar
        minimumBeauty, //Belleza minima necesaria para evolucionar
        minimumAffection, //Afecto mínimo necesario para evolucionar
        relativePhysicalStats, //Id cómo deben estar relacionadas las stats de Ataque y Defensa para que pueda evolucionar. (Ataque > Defensa | Ataque < Defensa | Ataque = Defensa)
        partySpeciesId, //Id del pokemon que tiene que estar en el equipo para que el pokemon evolucione
        partyTypeId, //Id del tipo del pokemon que tiene que estar en el equipo para que el pokemon evolucione
        tradeSpeciesId; //Id del pokemon que tiene que intercambiarse para que el pokemon evolucione
    private boolean needsOverworldRain, //Si necesita lluvia en el mapa para evolucionar
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

    public void setId(int id) {
        this.id = id;
    }

    public int getEvolvedSpeciesId() {
        return evolvedSpeciesId;
    }

    public void setEvolvedSpeciesId(int evolvedSpeciesId) {
        this.evolvedSpeciesId = evolvedSpeciesId;
    }

    public int getEvolutionTriggerId() {
        return evolutionTriggerId;
    }

    public void setEvolutionTriggerId(int evolutionTriggerId) {
        this.evolutionTriggerId = evolutionTriggerId;
    }

    public Integer getTriggerItemId() {
        return triggerItemId;
    }

    public void setTriggerItemId(Integer triggerItemId) {
        this.triggerItemId = triggerItemId;
    }

    public Integer getMinimumLevel() {
        return minimumLevel;
    }

    public void setMinimumLevel(Integer minimumLevel) {
        this.minimumLevel = minimumLevel;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getHeldItemId() {
        return heldItemId;
    }

    public void setHeldItemId(Integer heldItemId) {
        this.heldItemId = heldItemId;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public Integer getKnownMoveId() {
        return knownMoveId;
    }

    public void setKnownMoveId(Integer knownMoveId) {
        this.knownMoveId = knownMoveId;
    }

    public Integer getKnownMoveTypeId() {
        return knownMoveTypeId;
    }

    public void setKnownMoveTypeId(Integer knownMoveTypeId) {
        this.knownMoveTypeId = knownMoveTypeId;
    }

    public Integer getMinimumHappiness() {
        return minimumHappiness;
    }

    public void setMinimumHappiness(Integer minimumHappiness) {
        this.minimumHappiness = minimumHappiness;
    }

    public Integer getMinimumBeauty() {
        return minimumBeauty;
    }

    public void setMinimumBeauty(Integer minimumBeauty) {
        this.minimumBeauty = minimumBeauty;
    }

    public Integer getMinimumAffection() {
        return minimumAffection;
    }

    public void setMinimumAffection(Integer minimumAffection) {
        this.minimumAffection = minimumAffection;
    }

    public Integer getRelativePhysicalStats() {
        return relativePhysicalStats;
    }

    public void setRelativePhysicalStats(Integer relativePhysicalStats) {
        this.relativePhysicalStats = relativePhysicalStats;
    }

    public Integer getPartySpeciesId() {
        return partySpeciesId;
    }

    public void setPartySpeciesId(Integer partySpeciesId) {
        this.partySpeciesId = partySpeciesId;
    }

    public Integer getPartyTypeId() {
        return partyTypeId;
    }

    public void setPartyTypeId(Integer partyTypeId) {
        this.partyTypeId = partyTypeId;
    }

    public Integer getTradeSpeciesId() {
        return tradeSpeciesId;
    }

    public void setTradeSpeciesId(Integer tradeSpeciesId) {
        this.tradeSpeciesId = tradeSpeciesId;
    }

    public boolean isNeedsOverworldRain() {
        return needsOverworldRain;
    }

    public void setNeedsOverworldRain(boolean needsOverworldRain) {
        this.needsOverworldRain = needsOverworldRain;
    }

    public boolean isTurnUpsideDown() {
        return turnUpsideDown;
    }

    public void setTurnUpsideDown(boolean turnUpsideDown) {
        this.turnUpsideDown = turnUpsideDown;
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
