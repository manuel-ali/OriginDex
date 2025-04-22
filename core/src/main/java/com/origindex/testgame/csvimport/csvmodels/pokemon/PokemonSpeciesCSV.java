package com.origindex.testgame.csvimport.csvmodels.pokemon;

public class PokemonSpeciesCSV {
    private final int id;
    private final String identifier; //Nombre del pokemon
    private final int generationId; //Id de la primera generación en la que aparece
    private final Integer evolvesFromSpeciesId; //Id del pokemon del que evoluciona
    private final int evolutionChainId, //Id de la cadena evolutiva
        colorId, //Id del color del pokemon
        shapeId; //Id de la forma del pokemon (cuadrado, redondo, etc)
    private final Integer habitatId; //Id del habitat del pokemon (bosque, mar, etc)
    private final int genderRate, //Probabilidad de tasa de género del pokemon, valores 0-4
        captureRate, //Probabilidad de captura del pokemon
        baseHappiness; //Felicidad base del pokemon
    private final boolean isBaby; //Si el pokemon es un bebe o no (como Pichu, Magby, etc)
    private final int hatchCounter; //Número de pasos necesarios para que el pokemon eclosione de un huevo
    private final boolean hasGenderDifferences; //Si el pokemon tiene diferencias de género (como el caso de los pokemon macho y hembra)
    private final int growthRateId; //Id de la tasa de crecimiento del pokemon (lento, medio, rápido)
    private final boolean formsSwitchable, //Si el pokemon tiene formas intercambiables (como Rotom o megaevoluciones)
        isLegendary, //Si el pokemon es legendario o no
        isMythical; //Si el pokemon es mítico o no
    private final int order; //Orden del pokemon en la Pokedex
    private final Integer conquestOrder; //Orden de obtención del pokemon en el juego Pokemon Conquest

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

    public String getIdentifier() {
        return identifier;
    }

    public int getGenerationId() {
        return generationId;
    }

    public Integer getEvolvesFromSpeciesId() {
        return evolvesFromSpeciesId;
    }

    public int getEvolutionChainId() {
        return evolutionChainId;
    }

    public int getColorId() {
        return colorId;
    }

    public int getShapeId() {
        return shapeId;
    }

    public Integer getHabitatId() {
        return habitatId;
    }

    public int getGenderRate() {
        return genderRate;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public int getBaseHappiness() {
        return baseHappiness;
    }

    public boolean isBaby() {
        return isBaby;
    }

    public int getHatchCounter() {
        return hatchCounter;
    }

    public boolean isHasGenderDifferences() {
        return hasGenderDifferences;
    }

    public int getGrowthRateId() {
        return growthRateId;
    }

    public boolean isFormsSwitchable() {
        return formsSwitchable;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public boolean isMythical() {
        return isMythical;
    }

    public int getOrder() {
        return order;
    }

    public Integer getConquestOrder() {
        return conquestOrder;
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
