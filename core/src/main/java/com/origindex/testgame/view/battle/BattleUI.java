package com.origindex.testgame.view.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;
import com.origindex.testgame.game.logic.battle.BattleManager;
import com.origindex.testgame.game.logic.battle.MoveResolver;
import com.origindex.testgame.game.logic.battle.PriorityResolver;
import com.origindex.testgame.view.battle.sprites.PokemonSpriteManager;

import java.util.ArrayList;
import java.util.List;

public class BattleUI {
    private final Stage stage;
    private final Skin skin;
    private final BattleManager battleManager;

    // Tablas para organizar la UI
    private Table rootTable;
    private Table topTable, centerTable, bottomTable;
    private Table enemyHudTable, playerHudTable, actionTable, messageTable;

    // Etiquetas para mostrar información del jugador y enemigo
    private static Label playerLevelLabel, playerHpValueLabel;
    private static Label enemyLevelLabel, enemyHpValueLabel;
    private static Label infoLabel;

    // Barras de progreso para la vida del jugador y enemigo
    private ProgressBar playerHpBar, enemyHpBar;
    private ProgressBar playerExpBar;

    // Referencias a los Pokémon del jugador y enemigo
    private ActivePokemon playerPokemon;
    private ActivePokemon enemyPokemon;

    // Sprites de los Pokémon
    private Image playerSprite;
    private Image enemySprite;

    private boolean showingMessage;

    // Estilos de las barras de progreso y botones
    ProgressBar.ProgressBarStyle styleEnemy;
    ProgressBar.ProgressBarStyle stylePlayer;
    ProgressBar.ProgressBarStyle stylePlayerExperience;
    TextButton.TextButtonStyle buttonStyle;

    public BattleUI(Stage stage, Skin skin, BattleManager battleManager) {
        this.stage = stage;
        this.skin = skin;
        this.battleManager = battleManager;
        this.playerPokemon = battleManager.getBattle().getPokemonPlayer();
        this.enemyPokemon = battleManager.getBattle().getPokemonEnemy();
        this.showingMessage = true;
        createUI();
    }

    private void createUI() {
        rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

        // Secciones principales
        topTable = new Table();
        centerTable = new Table();
        bottomTable = new Table();
        messageTable = new Table();
        actionTable = new Table();

        rootTable.add(topTable).expandY().center().width(798).row();
        rootTable.add(centerTable).center().width(798).row();
        rootTable.add(bottomTable).center().padBottom(30).width(798);
        rootTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("view/battle/battle_background.jpg")))));
        createProgressBarStyle();
        createButtonStyle();
        createTopUI();
        createPlayerUi();
        createBottomUI();
    }

    public void createButtonStyle(){
        BitmapFont font = new BitmapFont(Gdx.files.internal("view/fonts/pixel_font_12.fnt"));
        Texture buttonTexture = new Texture(Gdx.files.internal("view/ui/button_1.png"));
        buttonTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest); // si es pixel art

        Drawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        Drawable downDrawable = new TextureRegionDrawable(new TextureRegion(buttonTexture)).tint(Color.GRAY);

        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = buttonDrawable;
        buttonStyle.down = downDrawable;
        buttonStyle.over = buttonDrawable;
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;
    }

    private void createTopUI() {
        Texture enemyTexture = PokemonSpriteManager.getFrontSprite(enemyPokemon);
        enemySprite = new Image(enemyTexture);
        enemySprite.setScale(2f);

        enemyHudTable = new Table();

        ActivePokemon enemy = battleManager.getBattle().getPokemonEnemy();
        enemyHpBar = new ProgressBar(0, enemy.getMaxHP(), 1, false, styleEnemy);
        enemyHpBar.setValue(enemy.getCurrentHP());
        enemyHpBar.setAnimateDuration(0.3f);

        Label enemyNicknameLabel = new Label(enemy.getNickname(), skin, "default");
        enemyLevelLabel = new Label("Lvl " + enemy.getLevel(), skin);
        Label enemyHpLabel = new Label("HP:", skin);
        enemyHpValueLabel = new Label(enemy.getCurrentHP() + "/" + enemy.getMaxHP(), skin);

        Texture bgTexture = new Texture(Gdx.files.internal("view/ui/InputField.png"));
        Drawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(bgTexture));
        enemyHudTable.pad(15).setBackground(backgroundDrawable);

        enemyHudTable.add(enemyNicknameLabel).left().colspan(2);
        enemyHudTable.add(enemyLevelLabel).right().row();
        enemyHudTable.add(enemyHpLabel).left().padRight(5);
        enemyHudTable.add(enemyHpBar).left().width(200);
        enemyHudTable.add(enemyHpValueLabel).right().padLeft(5);

        topTable.add(enemyHudTable).left();
        topTable.add().width(100);
        topTable.add(enemySprite).right().padTop(80);
        topTable.add().width(160);
    }

    public void createProgressBarStyle() {
        TextureRegionDrawable bg = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("view/ui/background_horizontal.png"))));
        TextureRegionDrawable fill = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("view/ui/filled_horizontal.png"))));

        styleEnemy = new ProgressBar.ProgressBarStyle();
        styleEnemy.background = bg;
        styleEnemy.knob = null;
        styleEnemy.knobBefore = fill.tint(Color.GREEN);

        stylePlayer = new ProgressBar.ProgressBarStyle();
        stylePlayer.background = bg;
        stylePlayer.knob = null;
        stylePlayer.knobBefore = fill.tint(Color.GREEN);

        stylePlayerExperience = new ProgressBar.ProgressBarStyle();
        stylePlayerExperience.background = bg;
        stylePlayerExperience.knob = null;
        stylePlayerExperience.knobBefore = fill.tint(new Color(0,155,255, 1)); // Aqua color for experience bar
    }

    public void updateLifeBarColor(ProgressBar lifeBar) {
        float percent = lifeBar.getPercent();
        Color color;
        if (percent < 0.3f) {
            color = Color.RED;
        } else if (percent < 0.5f) {
            color = Color.ORANGE;
        } else {
            color = Color.GREEN;
        }

        TextureRegionDrawable fillBase = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("view/ui/filled_horizontal.png"))));
        lifeBar.getStyle().knobBefore = fillBase.tint(color);
    }

    public void createPlayerUi(){
        playerHudTable = new Table();
        ActivePokemon player = battleManager.getBattle().getPokemonPlayer();
        playerHpBar = new ProgressBar(0, player.getMaxHP(), 1, false, stylePlayer);
        playerHpBar.setValue(player.getCurrentHP());
        playerHpBar.setAnimateDuration(0.3f);

        Label playerNicknameLabel = new Label(player.getNickname(), skin);
        playerLevelLabel = new Label("LVL " + player.getLevel(), skin);
        Label playerHpLabel = new Label("HP:", skin);
        playerHpValueLabel = new Label(player.getCurrentHP() + "/" + player.getMaxHP(), skin);

        Label experienceLabel = new Label("EXP:", skin);
        playerExpBar = new ProgressBar(0, player.getNextLevelXP(), 1, false, stylePlayerExperience);
        playerExpBar.setValue(player.getActualXP());

        Texture playerTexture = PokemonSpriteManager.getBackSprite(playerPokemon);
        playerSprite = new Image(playerTexture);
        playerSprite.setScale(2f);

        playerHudTable.add(playerNicknameLabel).left().colspan(2);
        playerHudTable.add(playerLevelLabel).right().row();
        playerHudTable.add(playerHpLabel).left();
        playerHudTable.add(playerHpBar).width(200).padRight(5).padLeft(5);
        playerHudTable.add(playerHpValueLabel).right().row();
        playerHudTable.add(experienceLabel).left().padRight(5);
        playerHudTable.add(playerExpBar).width(200);

        Texture bgTexture = new Texture(Gdx.files.internal("view/ui/InputField.png"));
        Drawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(bgTexture));
        playerHudTable.pad(20).setBackground(backgroundDrawable);

        centerTable.add().width(130);
        centerTable.add(playerSprite).bottom().left().padTop(60);
        centerTable.add().width(130);
        centerTable.add(playerHudTable).right();
    }

    private void createBottomUI() {
        bottomTable.defaults().pad(10);

        // Derecha: Menú de acciones
        createActionButtons();

        // Mensajes (centrado abajo)
        infoLabel = new Label("What should " + playerPokemon.getNickname() +  " do?", skin);
        infoLabel.setWrap(true);
        infoLabel.setAlignment(Align.left);
        messageTable.add(infoLabel).pad(10).width(300);

        // Añadir subtablas al bottomTable
        bottomTable.add(messageTable).top().left();
        bottomTable.add(actionTable).top().right();

        Texture bgTexture = new Texture(Gdx.files.internal("view/ui/background.png"));
        Drawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(bgTexture));
        bottomTable.setBackground(backgroundDrawable);
    }

    private void showMoves() {
        actionTable.clear();

        List<ActiveMove> moves = battleManager.getBattle().getPokemonPlayer().getLearnedMoves();
        int i = 0;
        for (ActiveMove move : moves) {
            final ActiveMove moveCopy = move;
            String moveButtonText = moveCopy.getMove().getIdentifier() + " " + moveCopy.getCurrentPP() + "/" + moveCopy.getMove().getPp();
            TextButton moveButton = new TextButton(moveButtonText, buttonStyle);
            moveButton.getLabel().setWrap(true);
            moveButton.getLabelCell().width(200);
            moveButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    List<String> messages = new ArrayList<>();
                    actionTable.setVisible(false);
                    ActiveMove enemyMove = MoveResolver.chooseEnemyMove(enemyPokemon, messages);
                    boolean isPlayerAttacksFirst = PriorityResolver.playerAttacksFirst(moveCopy, enemyMove, playerPokemon.getSpeedStat(), enemyPokemon.getSpeedStat());
                    resolveAnimationTurn(isPlayerAttacksFirst, moveCopy, enemyMove, messages);
                }
            });

            actionTable.add(moveButton).width(200).height(40).pad(5);
            i++;
            if (i % 2 == 0) actionTable.row();
        }
    }

    private void createActionButtons() {
        actionTable.clear();
        actionTable.setVisible(true);

        TextButton fight = new TextButton("Fight", buttonStyle);
        TextButton bag = new TextButton("Bag", buttonStyle);
        TextButton pokemon = new TextButton("Pokemon", buttonStyle);
        TextButton run = new TextButton("Run", buttonStyle);

        fight.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                showMoves();
            }
        });

        run.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        actionTable.add(fight).width(200).height(40).pad(5);
        actionTable.add(bag).width(200).height(40).pad(5);
        actionTable.row();
        actionTable.add(pokemon).width(200).height(40).pad(5);
        actionTable.add(run).width(200).height(40).pad(5);
    }

    public void updateUI() {
        playerHpBar.setValue(playerPokemon.getCurrentHP());
        enemyHpBar.setValue(enemyPokemon.getCurrentHP());

        playerHpValueLabel.setText(playerPokemon.getCurrentHP() + "/" + playerPokemon.getMaxHP());
        enemyHpValueLabel.setText(enemyPokemon.getCurrentHP() + "/" + enemyPokemon.getMaxHP());

        updateLifeBarColor(playerHpBar);
        updateLifeBarColor(enemyHpBar);
    }

    public void showBattleScreen() {
        Gdx.input.setInputProcessor(stage);
    }

    public void resolveAnimationTurn(boolean isPlayerAttacksFirst, ActiveMove moveCopy, ActiveMove enemyMove, List<String> messages) {
        if (isPlayerAttacksFirst) {
            playPlayerTurn(moveCopy, enemyMove, messages, () -> {
                playEnemyTurn(enemyMove, moveCopy, new ArrayList<>(), () -> {
                    showDefaultMessage();
                    createActionButtons();
                });
            });
        } else {
            playEnemyTurn(enemyMove, moveCopy, messages, () -> {
                playPlayerTurn(moveCopy, enemyMove, new ArrayList<>(), () -> {
                    showDefaultMessage();
                    createActionButtons();
                });
            });
        }
    }

    private void playPlayerTurn(ActiveMove playerMove, ActiveMove enemyMove, List<String> messages, Runnable onFinish) {
        battleManager.handleTurn(playerPokemon, playerMove, enemyPokemon, enemyMove, messages);

        showFirstMessage(messages.get(0), () -> {
            handleMoveAnimation(enemySprite, playerMove, () -> {
                updateUI();
                showMessageSequence(new ArrayList<>(messages), () -> {
                    handleFaintedPokemonAnimation(onFinish);
                });
            });
        });
    }

    private void playEnemyTurn(ActiveMove enemyMove, ActiveMove playerMove, List<String> messages, Runnable onFinish) {
        battleManager.handleTurn(enemyPokemon, enemyMove, playerPokemon, playerMove, messages);

        showFirstMessage(messages.get(0), () -> {
            handleMoveAnimation(playerSprite, enemyMove, () -> {
                updateUI();
                showMessageSequence(new ArrayList<>(messages), () -> {
                    handleFaintedPokemonAnimation(onFinish);
                });
            });
        });
    }

    public void handleFaintedPokemonAnimation(Runnable onFinish) {
        if (playerPokemon.getCurrentHP() <= 0) {
            showMessage(playerPokemon.getNickname() + " fainted.", () -> battleManager.getBattle().setFinished(true));
        } else if (enemyPokemon.getCurrentHP() <= 0) {
            showMessage(enemyPokemon.getNickname() + " fainted.", () -> battleManager.getBattle().setFinished(true));
        }else {
            onFinish.run();
        }
    }

    public void handleMoveAnimation(Image sprite, ActiveMove move, Runnable onComplete) {
        if (move.getMove().isStatusMove()) {
            enableStatChangeAnimation(sprite, move.getMove().getTarget().getIdentifier(), onComplete);
        } else {
            enableHitAnimation(sprite, onComplete);
        }
    }

    public void enableHitAnimation(Image sprite, Runnable onComplete) {
        sprite.addAction(
            Actions.sequence(
                Actions.repeat(4, Actions.sequence(
                    Actions.fadeOut(0.1f),
                    Actions.fadeIn(0.1f))
                ), Actions.run(onComplete)
            )
        );
    }

    public void enableStatChangeAnimation(Image sprite, String moveTarget, Runnable onComplete) {
        if (moveTarget.equals("all-opponents")) {
            sprite.addAction(
                Actions.sequence(
                    Actions.repeat(2, Actions.sequence(
                        Actions.color(Color.RED, 0.1f),
                        Actions.color(Color.WHITE, 0.1f)
                    )),
                    Actions.run(onComplete)
                )
            );
        } else {
            onComplete.run();
        }
    }

    public void showFirstMessage(String message, Runnable onFinish) {
        infoLabel.setText(message);
        messageTable.addAction(Actions.sequence(
            Actions.delay(1.0f),
            Actions.run(onFinish)
        ));
    }

    public void showMessageSequence(List<String> messages, Runnable onFinish) {
        if (messages.isEmpty()) {
            onFinish.run();
            return;
        }

        String currentMessage = messages.remove(0);
        showMessage(currentMessage, () -> {
            showMessageSequence(messages, onFinish);
        });
    }

    public void showDefaultMessage() {
        infoLabel.setText("What should " + playerPokemon.getNickname() + " do?");
    }

    public void showMessage(String message, Runnable onComplete) {
        showingMessage = true;
        SequenceAction sequence = new SequenceAction();

        sequence.addAction(Actions.run(() -> infoLabel.setText(message)));
        sequence.addAction(Actions.delay(1.5f));

        if (onComplete != null) {
            sequence.addAction(Actions.run(onComplete));
        }

        sequence.addAction(Actions.run(() -> showingMessage = false));

        messageTable.addAction(sequence);
    }

    public boolean isShowingMessage() {
        return showingMessage;
    }

    public void setShowingMessage(boolean showingMessage) {
        this.showingMessage = showingMessage;
    }
}
