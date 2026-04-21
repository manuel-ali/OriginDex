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
import com.origindex.testgame.game.battle.BattleController;
import com.origindex.testgame.game.battle.BattleStep;
import com.origindex.testgame.view.MapScreen;
import com.origindex.testgame.view.battle.sprites.MoveData;

import java.util.List;
import java.util.Queue;

public class BattleViewScreen implements Screen {
    private final BattleController battleController;
    private final BattleHud battleHud;
    private Main game;
    private MapScreen mapScreen;
    private Stage stage;
    private Skin skin;


    public BattleViewScreen(Main game, MapScreen mapScreen, BattleController battleController) {
        this.game = game;
        this.mapScreen = mapScreen;
        this.battleController = battleController;
        this.stage = new Stage(new FitViewport(960, 640));
        this.skin = new Skin(Gdx.files.internal("view/uiskin.json"));
        this.battleHud = new BattleHud(stage, skin, battleController);
    }

    @Override
    public void show() {
        startFadeIn(1f);
        PokemonData player = battleController.getPlayerData();
        PokemonData enemy = battleController.getEnemyData();

        battleHud.setPlayerData(player);
        battleHud.setEnemyData(enemy);
        battleHud.setMessage("What should " + player.nickname() + " do?");
        battleHud.showBattleScreen();

        battleController.startBattle();

        battleController.setListener(new BattleListener() {
            @Override
            public void onTurnFinished() {

            }

            @Override
            public void executeSteps(Queue<BattleStep> steps) {
                battleHud.processBattleSteps(steps);
            }

            @Override
            public void onShowMoves(List<MoveData> moves) {
                battleHud.showMoves(moves);
            }

            @Override
            public void onShowMessage(String message) {
                battleHud.setMessage(message);
            }

            @Override
            public void onBattleEnded() {
                fadeOutAndSwitchScreen(game, mapScreen, 1.0f);
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
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
