package com.origindex.testgame.csvimport.csvmodels.pokemon;

public class PokemonSpeciesCSV {
    private  int id;
    private String identifier; //Nombre del pokemon
    private int generationId; //ID de la primera generacion en la que aparece
    private Integer evolvesFromSpeciesId; //ID del pokemon del que evoluciona
    private int evolutionChainId, //ID de la cadena evolutiva
        colorId, //ID del color del pokemon
        shapeId; //ID de la forma del pokemon (cuadrado, redondo, etc)
    private Integer habitatId; //ID del habitat del pokemon (bosque, mar, etc)
    private int genderRate, //Probabilidad de tasa de genero del pokemon, valores 0-4
        captureRate, //Probabilidad de captura del pokemon
        baseHappiness; //Felicidad base del pokemon
    private boolean isBaby; //Si el pokemon es un bebe o no (como pichu, magby, etc)
    private int hatchCounter; //Numero de pasos necesarios para que el pokemon eclosione de un huevo
    private boolean hasGenderDifferences; //Si el pokemon tiene diferencias de genero (como el caso de los pokemon macho y hembra)
    private int growthRateId; //ID de la tasa de crecimiento del pokemon (lento, medio, rapido)
    private boolean formsSwitchable, //Si el pokemon tiene formas intercambiables (como rotom o megaevoluciones)
        isLegendary, //Si el pokemon es legendario o no
        isMythical; //Si el pokemon es mitico o no
    private int order; //Orden del pokemon en la pokedex
    private Integer conquestOrder; //Orden de obtencion del pokemon en el juego Pokemon Conquest

    public PokemonSpeciesCSV(int id, String identifier, int generationId, Integer evolvesFromSpeciesId, int evolutionChainId,
                             int colorId, int shapeId, Integer habitatId, int genderRate, int captureRate, int baseHappiness,
                             boolean isBaby, int hatchCounter, boolean hasGenderDifferences, int growthRateId, boolean formsSwitchable,
                             boolean isLegendary, boolean isMythical, int order, Integer conquestOrder) {
        this.id = id;
        this.identifier = identifier;
        this.generationId = generationId;
        this.evolvesFromSpeciesId = evolvesFromSpeciesId;
        this.evolutionChainId = evolutionChainId;
        this.colorId = colorId;
        this.shapeId = shapeId;
        this.habitatId = habitatId;
        this.genderRate = genderRate;
        this.captureRate = captureRate;
        this.baseHappiness = baseHappiness;
        this.isBaby = isBaby;
        this.hatchCounter = hatchCounter;
        this.hasGenderDifferences = hasGenderDifferences;
        this.growthRateId = growthRateId;
        this.formsSwitchable = formsSwitchable;
        this.isLegendary = isLegendary;
        this.isMythical = isMythical;
        this.order = order;
        this.conquestOrder = conquestOrder;
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

    public Integer getEvolvesFromSpeciesId() {
        return evolvesFromSpeciesId;
    }

    public void setEvolvesFromSpeciesId(Integer evolvesFromSpeciesId) {
        this.evolvesFromSpeciesId = evolvesFromSpeciesId;
    }

    public int getEvolutionChainId() {
        return evolutionChainId;
    }

    public void setEvolutionChainId(int evolutionChainId) {
        this.evolutionChainId = evolutionChainId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getShapeId() {
        return shapeId;
    }

    public void setShapeId(int shapeId) {
        this.shapeId = shapeId;
    }

    public Integer getHabitatId() {
        return habitatId;
    }

    public void setHabitatId(Integer habitatId) {
        this.habitatId = habitatId;
    }

    public int getGenderRate() {
        return genderRate;
    }

    public void setGenderRate(int genderRate) {
        this.genderRate = genderRate;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    public int getBaseHappiness() {
        return baseHappiness;
    }

    public void setBaseHappiness(int baseHappiness) {
        this.baseHappiness = baseHappiness;
    }

    public boolean isBaby() {
        return isBaby;
    }

    public void setBaby(boolean baby) {
        isBaby = baby;
    }

    public int getHatchCounter() {
        return hatchCounter;
    }

    public void setHatchCounter(int hatchCounter) {
        this.hatchCounter = hatchCounter;
    }

    public boolean isHasGenderDifferences() {
        return hasGenderDifferences;
    }

    public void setHasGenderDifferences(boolean hasGenderDifferences) {
        this.hasGenderDifferences = hasGenderDifferences;
    }

    public int getGrowthRateId() {
        return growthRateId;
    }

    public void setGrowthRateId(int growthRateId) {
        this.growthRateId = growthRateId;
    }

    public boolean isFormsSwitchable() {
        return formsSwitchable;
    }

    public void setFormsSwitchable(boolean formsSwitchable) {
        this.formsSwitchable = formsSwitchable;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public void setLegendary(boolean legendary) {
        isLegendary = legendary;
    }

    public boolean isMythical() {
        return isMythical;
    }

    public void setMythical(boolean mythical) {
        isMythical = mythical;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Integer getConquestOrder() {
        return conquestOrder;
    }

    public void setConquestOrder(Integer conquestOrder) {
        this.conquestOrder = conquestOrder;
    }

    @Override
    public String toString() {
        return "PokemonSpeciesCSV{" +
            "id=" + id +
            ", identifier='" + identifier + '\'' +
            ", generationId=" + generationId +
            ", evolvesFromSpeciesId=" + evolvesFromSpeciesId +
            ", evolutionChainId=" + evolutionChainId +
            ", colorId=" + colorId +
            ", shapeId=" + shapeId +
            ", habitatId=" + habitatId +
            ", genderRate=" + genderRate +
            ", captureRate=" + captureRate +
            ", baseHappiness=" + baseHappiness +
            ", isBaby=" + isBaby +
            ", hatchCounter=" + hatchCounter +
            ", hasGenderDifferences=" + hasGenderDifferences +
            ", growthRateId=" + growthRateId +
            ", formsSwitchable=" + formsSwitchable +
            ", isLegendary=" + isLegendary +
            ", isMythical=" + isMythical +
            ", order=" + order +
            ", conquestOrder=" + conquestOrder +
            '}';
    }
}
