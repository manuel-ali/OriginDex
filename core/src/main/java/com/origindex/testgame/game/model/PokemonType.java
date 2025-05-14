package com.origindex.testgame.game.model;

public class PokemonType {
    private Type type;
    private int slot;

    public PokemonType(Type type, int slot) {
        this.type = type;
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return "PokemonType{" +
            "type=" + type +
            ", slot=" + slot +
            '}';
    }
}
