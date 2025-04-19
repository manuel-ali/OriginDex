package com.origindex.testgame.csvimport.csvmodels.pokemon;

//Clase de importación de pokemon.csv para la base de datos
public class PokemonCSV {
    private int id;
    private String identifier; //Nombre del pokemon
    private int speciesId; //Id de la especie del pokemon
    private Integer height,weight, baseExperience,order;
    private Boolean isDefault; //Indica si es la forma por defecto, (sin megas, sin formas alternativas, etc)

    public PokemonCSV(int id, String identifier, int speciesId, Integer height, Integer weight, Integer baseExperience, Integer order, Boolean isDefault) {
        this.id = id;
        this.identifier = identifier;
        this.speciesId = speciesId;
        this.height = height;
        this.weight = weight;
        this.baseExperience = baseExperience;
        this.order = order;
        this.isDefault = isDefault;
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

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(Integer baseExperience) {
        this.baseExperience = baseExperience;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean isDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "PokemonCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", speciesId=" + speciesId +
            ", height=" + height +
            ", weight=" + weight +
            ", baseExperience=" + baseExperience +
            ", order=" + order +
            ", isDefault=" + isDefault +
            '}';
    }
}
