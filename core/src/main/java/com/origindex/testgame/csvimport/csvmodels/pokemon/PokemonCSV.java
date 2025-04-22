package com.origindex.testgame.csvimport.csvmodels.pokemon;

//Clase de importación de pokemon.csv para la base de datos
public class PokemonCSV {
    private final int id;
    private final String identifier; //Nombre del pokemon
    private final int speciesId; //Id de la especie del pokemon
    private final Integer height,weight, baseExperience,order;
    private final Boolean isDefault; //Indica si es la forma por defecto, (sin megas, sin formas alternativas, etc)

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

    public String getIdentifier() {
        return identifier;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getBaseExperience() {
        return baseExperience;
    }

    public Integer getOrder() {
        return order;
    }

    public Boolean getDefault() {
        return isDefault;
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
