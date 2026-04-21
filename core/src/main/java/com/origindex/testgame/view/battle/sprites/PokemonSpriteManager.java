package com.origindex.testgame.view.battle.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.origindex.testgame.game.entity.ActivePokemon;

public class PokemonSpriteManager {
    public static Texture getFrontSprite(int id) {
        String path = "sprites/generation-iii/firered-leafgreen/" + id + ".png";
        return new Texture(Gdx.files.internal(path));
    }

    public static Texture getBackSprite(int id) {
        String path = "sprites/generation-iii/firered-leafgreen/back/" + id + ".png";
        return new Texture(Gdx.files.internal(path));
    }
}
