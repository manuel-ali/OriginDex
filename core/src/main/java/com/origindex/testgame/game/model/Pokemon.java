package com.origindex.testgame.game.model;

import java.util.List;

public class Pokemon {
    private int id;
    private String identifier;
    //private PokemonSpecies species;
    private int height, weight, baseExperience;
    private Integer orderIndex;
    private Boolean isDefault; //Indica si es la forma por defecto, (sin megas, sin formas alternativas, etc)
    private List<PokemonStat> stats;
    private List<PokemonMove> moves;
    private List<PokemonType> types;


    public Pokemon(int id, String identifier, int height, int weight, int baseExperience, Integer orderIndex, Boolean isDefault, List<PokemonStat> stats, List<PokemonMove> moves, List<PokemonType> types) {
        this.id = id;
        this.identifier = identifier;
        this.height = height;
        this.weight = weight;
        this.baseExperience = baseExperience;
        this.orderIndex = orderIndex;
        this.isDefault = isDefault;
        this.stats = stats;
        this.moves = moves;
        this.types = types;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public List<PokemonStat> getStats() {
        return stats;
    }

    public void setStats(List<PokemonStat> stats) {
        this.stats = stats;
    }

    public List<PokemonMove> getMoves() {
        return moves;
    }

    public void setMoves(List<PokemonMove> moves) {
        this.moves = moves;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", height=" + height +
            ", weight=" + weight +
            ", baseExperience=" + baseExperience +
            ", orderIndex=" + orderIndex +
            ", isDefault=" + isDefault +
            ", stats=" + stats +
            ", moves=" + moves +
            ", types=" + types +
            '}';
    }
}
