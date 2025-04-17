package com.origindex.testgame.csvimport.csvmodels.pokemon;

//Clase de importacion de pokemon.csv para la base de datos
public class PokemonCSV {
    private int id;
    private String identifier; //Nombre del pokemon
    private int species_id; //ID de la especie del pokemon
    private Integer height,weight,base_experience,order;
    private Boolean is_default; //Indica si es la forma por defecto, (sin megas, sin formas alternativas, etc)

    public PokemonCSV(int id, String identifier, int species_id, Integer height, Integer weight, Integer base_experience, Integer order, Boolean is_default) {
        this.id = id;
        this.identifier = identifier;
        this.species_id = species_id;
        this.height = height;
        this.weight = weight;
        this.base_experience = base_experience;
        this.order = order;
        this.is_default = is_default;
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

    public int getSpecies_id() {
        return species_id;
    }

    public void setSpecies_id(int species_id) {
        this.species_id = species_id;
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

    public Integer getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(Integer base_experience) {
        this.base_experience = base_experience;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean isDefault() {
        return is_default;
    }

    public void setIs_default(Boolean is_default) {
        this.is_default = is_default;
    }

    @Override
    public String toString() {
        return "PokemonCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", species_id=" + species_id +
            ", height=" + height +
            ", weight=" + weight +
            ", base_experience=" + base_experience +
            ", order=" + order +
            ", is_default=" + is_default +
            '}';
    }
}
