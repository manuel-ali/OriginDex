package com.origindex.testgame.game.logic;

public class ExperienceCalculator {
    /**
     * Calcula los puntos de experiencia necesarios para alcanzar un nivel específico
     *
     * @param growthRate El tipo de growthRate del pokemon (lento, medio, rapido, etc)
     * @param level      El nivel para el que se desea calcular la experiencia
     * @return La cantidad de experiencia necesaria para alcanzar el nivel especificado
     */
    public static int getExperience(String growthRate, int level) {
        switch (growthRate) {
            case "slow":
                return (int) (5 * Math.pow(level, 3) / 4);
            case "medium":
                return (int) Math.pow(level, 3);
            case "fast":
                return (int) (4 * Math.pow(level, 3) / 5);
            case "medium-slow":
                return (int) ((6.0 / 5.0) * Math.pow(level, 3) - 15 * Math.pow(level, 2) + 100 * level - 140);
            case "slow-then-very-fast":
                return calculateErratic(level);
            case "fast-then-very-slow":
                return calculateFluctuating(level);
            default:
                throw new IllegalArgumentException("Unknown growth rate: " + growthRate);
        }
    }

    // slow-then-very-fast (erratic)
    private static int calculateErratic(int level) {
        if (level <= 50) {
            return (int) ((Math.pow(level, 3) * (100 - level)) / 50);
        } else if (level <= 68) {
            return (int) ((Math.pow(level, 3) * (150 - level)) / 100);
        } else if (level <= 98) {
            return (int) ((Math.pow(level, 3) * (Math.floorMod(level, 3) * Math.floorMod(level, 3)
                - 9 * Math.floorMod(level, 3) + 1274 - 20 * Math.floor(level / 3.0))) / 1000);
        } else {
            return (int) ((Math.pow(level, 3) * (160 - level)) / 100);
        }
    }

    // fast-then-very-slow (fluctuating)
    private static int calculateFluctuating(int level) {
        if (level <= 15) {
            return (int) ((Math.pow(level, 3) * (24 + Math.floor((level + 1) / 3.0))) / 50);
        } else if (level <= 35) {
            return (int) ((Math.pow(level, 3) * (14 + level)) / 50);
        } else {
            return (int) ((Math.pow(level, 3) * (32 + Math.floor(level / 2.0))) / 50);
        }
    }
}
