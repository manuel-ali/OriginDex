package com.origindex.testgame.game.battle;

import com.origindex.testgame.view.battle.PokemonData;

public class BattleStep {
    public enum Target{
        PLAYER,
        ENEMY
    }

    public enum Type{
        MESSAGE,
        ANIMATION,
        UPDATE
    }

    public enum AnimationType{
        STATUS,
        HIT,
        FAINT
    }

    private Type type;
    private String message;
    private float duration;
    private Target target;
    private AnimationType animationType;
    private PokemonData pokemonData;

    public static BattleStep message(String message){
        BattleStep step = new BattleStep();
        step.type = Type.MESSAGE;
        step.message = message;
        step.duration = 1.5f;

        return step;
    }

    public static BattleStep animation(AnimationType animationType, Target target) {
        BattleStep step = new BattleStep();
        step.type = Type.ANIMATION;
        step.animationType = animationType;
        step.duration = 1.5f;
        step.target = target;

        return step;
    }

    public static BattleStep update(Target target, PokemonData pokemonData) {
        BattleStep step = new BattleStep();
        step.type = Type.UPDATE;
        step.target = target;
        step.pokemonData = pokemonData;

        return step;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public AnimationType getAnimationType() {
        return animationType;
    }

    public void setAnimationType(AnimationType animationType) {
        this.animationType = animationType;
    }

    public PokemonData getPokemonData() {
        return pokemonData;
    }

    public void setPokemonData(PokemonData pokemonData) {
        this.pokemonData = pokemonData;
    }
}
