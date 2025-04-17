package com.origindex.testgame.csvimport.csvmodels.pokemon;

/** Clase de importacion de pokemon_evolution.csv para la base de datos.
 * Esta clase contiene al pokemon al que evoluciona y los requisitos para que evolucione.
 */
public class PokemonEvolutionCSV {
    private int id;
    private int evolved_species_id; //ID del pokemon al que evoluciona
    private int evolution_trigger_id; //ID de la forma en la que se da la evolucion (nivel, piedra, etc)
    private Integer trigger_item_id, //ID del objeto que se necesita para evolucionar
        minimum_level, //Nivel minimo necesario para evolucionar
        gender_id, //Genero que tiene que tener para evolucionar
        location_id, //ID de la localizacion donde se da la evolucion
        held_item_id; //ID del objeto que tiene que llevar el pokemon para evolucionar
    private String time_of_day; //Momento del dia en el que se da la evolucion (dia/noche)
    private Integer known_move_id, //ID del movimiento que tiene que conocer el pokemon para evolucionar
        known_move_type_id, //ID del tipo del movimiento que tiene que conocer el pokemon para evolucionar (agua, fuego, etc)
        minimum_happiness, //Felicidad minima necesaria para evolucionar
        minimum_beauty, //Belleza minima necesaria para evolucionar
        minimum_affection, //Afecto minimo necesario para evolucionar
        relative_physical_stats, //ID cómo deben estar relacionadas las stats de Ataque y Defensa para que pueda evolucionar. (Ataque > Defensa | Ataque < Defensa | Ataque = Defensa)
        party_species_id, //ID del pokemon que tiene que estar en el equipo para que el pokemon evolucione
        party_type_id, //ID del tipo del pokemon que tiene que estar en el equipo para que el pokemon evolucione
        trade_species_id; //ID del pokemon que tiene que intercambiarse para que el pokemon evolucionae
    private boolean needs_overworld_rain, //Si necesita lluvia en el mapa para evolucionar
        turn_upside_down; //Indica si poara evolucionar al pokemon se ha de girar la consola físicamente hacia abajo durante la subida de nivel

    public PokemonEvolutionCSV(int id, int evolved_species_id, int evolution_trigger_id, Integer trigger_item_id, Integer minimum_level,
                               Integer gender_id, Integer location_id, Integer held_item_id, String time_of_day, Integer known_move_id,
                               Integer known_move_type_id, Integer minimum_happiness, Integer minimum_beauty, Integer minimum_affection,
                               Integer relative_physical_stats, Integer party_species_id, Integer party_type_id, Integer trade_species_id, boolean needs_overworld_rain, boolean turn_upside_down) {
        this.id = id;
        this.evolved_species_id = evolved_species_id;
        this.evolution_trigger_id = evolution_trigger_id;
        this.trigger_item_id = trigger_item_id;
        this.minimum_level = minimum_level;
        this.gender_id = gender_id;
        this.location_id = location_id;
        this.held_item_id = held_item_id;
        this.time_of_day = time_of_day;
        this.known_move_id = known_move_id;
        this.known_move_type_id = known_move_type_id;
        this.minimum_happiness = minimum_happiness;
        this.minimum_beauty = minimum_beauty;
        this.minimum_affection = minimum_affection;
        this.relative_physical_stats = relative_physical_stats;
        this.party_species_id = party_species_id;
        this.party_type_id = party_type_id;
        this.trade_species_id = trade_species_id;
        this.needs_overworld_rain = needs_overworld_rain;
        this.turn_upside_down = turn_upside_down;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvolved_species_id() {
        return evolved_species_id;
    }

    public void setEvolved_species_id(int evolved_species_id) {
        this.evolved_species_id = evolved_species_id;
    }

    public int getEvolution_trigger_id() {
        return evolution_trigger_id;
    }

    public void setEvolution_trigger_id(int evolution_trigger_id) {
        this.evolution_trigger_id = evolution_trigger_id;
    }

    public Integer getTrigger_item_id() {
        return trigger_item_id;
    }

    public void setTrigger_item_id(Integer trigger_item_id) {
        this.trigger_item_id = trigger_item_id;
    }

    public Integer getMinimum_level() {
        return minimum_level;
    }

    public void setMinimum_level(Integer minimum_level) {
        this.minimum_level = minimum_level;
    }

    public Integer getGender_id() {
        return gender_id;
    }

    public void setGender_id(Integer gender_id) {
        this.gender_id = gender_id;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public Integer getHeld_item_id() {
        return held_item_id;
    }

    public void setHeld_item_id(Integer held_item_id) {
        this.held_item_id = held_item_id;
    }

    public String getTime_of_day() {
        return time_of_day;
    }

    public void setTime_of_day(String time_of_day) {
        this.time_of_day = time_of_day;
    }

    public Integer getKnown_move_id() {
        return known_move_id;
    }

    public void setKnown_move_id(Integer known_move_id) {
        this.known_move_id = known_move_id;
    }

    public Integer getKnown_move_type_id() {
        return known_move_type_id;
    }

    public void setKnown_move_type_id(Integer known_move_type_id) {
        this.known_move_type_id = known_move_type_id;
    }

    public Integer getMinimum_happiness() {
        return minimum_happiness;
    }

    public void setMinimum_happiness(Integer minimum_happiness) {
        this.minimum_happiness = minimum_happiness;
    }

    public Integer getMinimum_beauty() {
        return minimum_beauty;
    }

    public void setMinimum_beauty(Integer minimum_beauty) {
        this.minimum_beauty = minimum_beauty;
    }

    public Integer getMinimum_affection() {
        return minimum_affection;
    }

    public void setMinimum_affection(Integer minimum_affection) {
        this.minimum_affection = minimum_affection;
    }

    public Integer getRelative_physical_stats() {
        return relative_physical_stats;
    }

    public void setRelative_physical_stats(Integer relative_physical_stats) {
        this.relative_physical_stats = relative_physical_stats;
    }

    public Integer getParty_species_id() {
        return party_species_id;
    }

    public void setParty_species_id(Integer party_species_id) {
        this.party_species_id = party_species_id;
    }

    public Integer getParty_type_id() {
        return party_type_id;
    }

    public void setParty_type_id(Integer party_type_id) {
        this.party_type_id = party_type_id;
    }

    public Integer getTrade_species_id() {
        return trade_species_id;
    }

    public void setTrade_species_id(Integer trade_species_id) {
        this.trade_species_id = trade_species_id;
    }

    public boolean isNeeds_overworld_rain() {
        return needs_overworld_rain;
    }

    public void setNeeds_overworld_rain(boolean needs_overworld_rain) {
        this.needs_overworld_rain = needs_overworld_rain;
    }

    public boolean isTurn_upside_down() {
        return turn_upside_down;
    }

    public void setTurn_upside_down(boolean turn_upside_down) {
        this.turn_upside_down = turn_upside_down;
    }

    @Override
    public String toString() {
        return "PokemonEvolutionCSV{" +
            "id=" + id +
            ", evolved_species_id=" + evolved_species_id +
            ", evolution_trigger_id=" + evolution_trigger_id +
            ", trigger_item_id=" + trigger_item_id +
            ", minimum_level=" + minimum_level +
            ", gender_id=" + gender_id +
            ", location_id=" + location_id +
            ", held_item_id=" + held_item_id +
            ", time_of_day='" + time_of_day + '\'' +
            ", known_move_id=" + known_move_id +
            ", known_move_type_id=" + known_move_type_id +
            ", minimum_happiness=" + minimum_happiness +
            ", minimum_beauty=" + minimum_beauty +
            ", minimum_affection=" + minimum_affection +
            ", relative_physical_stats=" + relative_physical_stats +
            ", party_species_id=" + party_species_id +
            ", party_type_id=" + party_type_id +
            ", trade_species_id=" + trade_species_id +
            ", needs_overworld_rain=" + needs_overworld_rain +
            ", turn_upside_down=" + turn_upside_down +
            '}';
    }
}
