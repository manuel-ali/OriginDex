package com.origindex.testgame.view.battle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.origindex.testgame.Main;
import com.origindex.testgame.game.logic.battle.BattleManager;
import com.origindex.testgame.view.MapScreen;

public class BattleScreen implements Screen {
    private final BattleManager battleManager;
    private Main game;
    private MapScreen mapScreen;
    private Stage stage;
    private Skin skin;
    private BattleUI battleUI;

    public BattleScreen(BattleManager battleManager, Main game, MapScreen mapScreen) {
        this.battleManager = battleManager;
        this.game = game;
        this.mapScreen = mapScreen;
        this.stage = new Stage(new FitViewport(960, 640));
        this.skin = new Skin(Gdx.files.internal("view/uiskin.json"));
        this.battleUI = new BattleUI(stage, skin, battleManager);
    }

    @Override
    public void show() {
        startFadeIn(1f);
        battleUI.showBattleScreen();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        if (battleManager.getBattle().isFinished() && !battleUI.isShowingMessage()) {
            fadeOutAndSwitchScreen(game, mapScreen, 1f);
            battleManager.getBattle().getPokemonEnemy().setCurrentHP(battleManager.getBattle().getPokemonEnemy().getMaxHP());
            mapScreen.restorePlayerPosition(mapScreen.getLastPlayerPosition());
        }
    }

    private void startFadeIn(float duration) {
        Image fade = new Image(new Texture("view/black.png"));
        fade.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        fade.getColor().a = 1f;
        stage.addActor(fade);

        fade.addAction(Actions.sequence(
            Actions.fadeOut(duration),
            Actions.run(fade::remove)
        ));
    }

    private void fadeOutAndSwitchScreen(final Game game, final Screen newScreen, float duration) {
        Image fade = new Image(new Texture("view/black.png"));
        fade.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        fade.getColor().a = 0f;
        stage.addActor(fade);

        fade.addAction(Actions.sequence(
            Actions.fadeIn(duration),
            Actions.run(() -> game.setScreen(newScreen))
        ));
    }

    @Override public void resize(int width, int height) { stage.getViewport().update(width, height, true); }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {dispose();}
    @Override public void dispose() { stage.dispose(); skin.dispose();}
}
