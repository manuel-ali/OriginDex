package com.origindex.testgame;

import com.badlogic.gdx.Game;
import com.origindex.testgame.view.MapScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {
        setScreen(new MapScreen(this));
    }
}
