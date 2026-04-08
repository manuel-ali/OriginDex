package com.origindex.testgame.view.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.origindex.testgame.game.battle.*;
import com.origindex.testgame.view.battle.sprites.MoveData;
import com.origindex.testgame.view.battle.sprites.PokemonSpriteManager;

import java.util.*;
import java.util.List;

public class BattleHud {
    private final Stage stage;
    private final Skin skin;
    private final BattleController battleController;

    private Table topTable, centerTable, bottomTable;
    private Table actionTable;
    private Table messageTable;

    // Etiquetas para mostrar información del jugador y enemigo
    private Label enemyNicknameLabel, playerNicknameLabel;
    private Label enemyHpValueLabel, playerHpValueLabel;
    private Label enemyLevelLabel, playerLevelLabel;
    private Label playerExpValueLabel;
    private Label infoLabel;

    // Barras de progreso para la vida del jugador y enemigo
    private Map<PokemonData, ProgressBar> hpBars = new HashMap<>();
    private ProgressBar playerHpBar, enemyHpBar;
    private ProgressBar playerExpBar;

    private Queue<BattleStep> battleSteps = new LinkedList<BattleStep>();

    // Sprites de los Pokémon
    private Image playerSprite;
    private Image enemySprite;

    private boolean showingMessage;

    // Estilos de las barras de progreso y botones
    ProgressBar.ProgressBarStyle styleEnemy;
    ProgressBar.ProgressBarStyle stylePlayer;
    ProgressBar.ProgressBarStyle stylePlayerExperience;
    TextButton.TextButtonStyle buttonStyle;

    public BattleHud(Stage stage, Skin skin, BattleController battleController) {
        this.stage = stage;
        this.skin = skin;
        this.battleController = battleController;
        createUI();
    }

    public void processBattleSteps(Queue<BattleStep> steps) {
        battleSteps.addAll(steps);
        executeNextStep();
    }

    private void executeNextStep(){
        if (battleSteps.isEmpty()){
            battleController.onTurnFinished();
            return;
        }

        BattleStep battleStep = battleSteps.poll();

        switch (battleStep.getType()){
            case MESSAGE -> showMessage(battleStep.getMessage(), battleStep.getDuration(), this::executeNextStep);
            case ANIMATION -> playAnimation(battleStep.getAnimationType(), battleStep.getTarget(), battleStep.getDuration(), this::executeNextStep);
            case UPDATE -> updateUI(battleStep.getTarget(), battleStep.getPokemonData(), this::executeNextStep);
        }
    }

    private void showMessage(String message, float duration, Runnable onFinish){
        setMessage(message, duration, onFinish);
    }

    private void playAnimation(BattleStep.AnimationType animationType, BattleStep.Target target, float duration, Runnable onFinish){
        Image spriteToAnimate = getSpriteToAnimate(target);

        switch (animationType){
            case HIT -> enableHitAnimation(spriteToAnimate, duration, onFinish);
            case STATUS -> enableStatChangeAnimation(spriteToAnimate, duration, onFinish);
            case FAINT -> enableFaintAnimation(spriteToAnimate, duration, onFinish);
        }
    }

    private Image getSpriteToAnimate(BattleStep.Target target){
        return target == BattleStep.Target.PLAYER ? playerSprite :  enemySprite;
    }

    private void createUI() {
        // Tablas para organizar la UI
        Table rootTable = new Table();
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
        //Sprite
        enemySprite = new Image();
        enemySprite.setScale(2f);

        //Hp bar
        enemyHpBar = new ProgressBar(0, 100, 1, false, styleEnemy);
        enemyHpBar.setAnimateDuration(0.3f);

        //Labels
        enemyNicknameLabel = new Label("", skin, "default");
        enemyLevelLabel = new Label("", skin, "default");
        enemyHpValueLabel = new Label("", skin);

        Label hpLabel = new Label("HP:", skin);

        //HUD table
        Table enemyHudTable = new Table();
        //Table background
        Texture bgTexture = new Texture(Gdx.files.internal("view/ui/InputField.png"));
        Drawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(bgTexture));
        enemyHudTable.pad(15).setBackground(backgroundDrawable);

        enemyHudTable.add(enemyNicknameLabel).left().colspan(2);
        enemyHudTable.add(enemyLevelLabel).right().row();
        enemyHudTable.add(hpLabel).left().padRight(5);
        enemyHudTable.add(enemyHpBar).left().width(200);
        enemyHudTable.add(enemyHpValueLabel).right().padLeft(5);

        topTable.add(enemyHudTable).left();
        topTable.add().width(100);
        topTable.add(enemySprite).right().padTop(80);
        topTable.add().width(160);
    }

    public void setEnemyData(PokemonData pokemonData){
        //Sprite
        Texture enemyTexture = PokemonSpriteManager.getFrontSprite(pokemonData.id());
        enemySprite.setDrawable(new TextureRegionDrawable(new TextureRegion(enemyTexture)));

        //Labels
        enemyNicknameLabel.setText(pokemonData.nickname());
        enemyLevelLabel.setText("Lvl " + pokemonData.level());
        enemyHpValueLabel.setText(pokemonData.currentHp() + "/" + pokemonData.maxHp());

        //Bars
        enemyHpBar.setRange(0, pokemonData.maxHp());
        enemyHpBar.setValue(pokemonData.currentHp());
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
        playerSprite = new Image();
        playerSprite.setScale(2f);

        playerHpBar = new ProgressBar(0, 100, 1, false, stylePlayer);
        playerHpBar.setAnimateDuration(0.3f);

        playerNicknameLabel = new Label("", skin);
        playerLevelLabel = new Label("", skin);
        playerHpValueLabel = new Label("", skin);

        Label hpLabel = new Label("HP:", skin);
        Label xpLabel = new Label("EXP:", skin);
        playerExpBar = new ProgressBar(0, 100, 1, false, stylePlayerExperience);
        playerExpValueLabel = new Label("", skin);

        Table playerHudTable = new Table();

        Texture bgTexture = new Texture(Gdx.files.internal("view/ui/InputField.png"));
        Drawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(bgTexture));
        playerHudTable.pad(20).setBackground(backgroundDrawable);

        playerHudTable.add(playerNicknameLabel).left().colspan(2);
        playerHudTable.add(playerLevelLabel).right().row();
        playerHudTable.add(hpLabel).left();
        playerHudTable.add(playerHpBar).width(200).padRight(5).padLeft(5);
        playerHudTable.add(playerHpValueLabel).right().row();
        playerHudTable.add(xpLabel).left().padRight(5);
        playerHudTable.add(playerExpBar).width(200);
        playerHudTable.add(playerExpValueLabel).right().padLeft(5);

        centerTable.add().width(130);
        centerTable.add(playerSprite).bottom().left().padTop(60);
        centerTable.add().width(130);
        centerTable.add(playerHudTable).right();
    }

    public void setPlayerData(PokemonData pokemonData){
        //Sprite
        Texture player = PokemonSpriteManager.getBackSprite(pokemonData.id());
        playerSprite.setDrawable(new TextureRegionDrawable(new TextureRegion(player)));

        //Labels
        playerNicknameLabel.setText(pokemonData.nickname());
        playerLevelLabel.setText("Lvl " + pokemonData.level());
        playerHpValueLabel.setText(pokemonData.currentHp() + "/" + pokemonData.maxHp());

        //Bars
        playerHpBar.setRange(0, pokemonData.maxHp());
        playerHpBar.setValue(pokemonData.currentHp());

        playerExpBar.setRange(0, pokemonData.nextLevelXp());
        playerExpBar.setValue(pokemonData.actualXp());
        playerExpValueLabel.setText(pokemonData.actualXp() + "/" + pokemonData.nextLevelXp());
    }

    private void createBottomUI() {
        // Derecha: Menú de acciones
        createActionButtons();

        // Mensajes (centrado abajo)
        infoLabel = new Label("", skin);
        infoLabel.setWrap(true);
        infoLabel.setAlignment(Align.left);

        messageTable.add(infoLabel).pad(10).width(300);

        // Añadir subtablas al bottomTable
        bottomTable.defaults().pad(10);
        bottomTable.add(messageTable).top().left();
        bottomTable.add(actionTable).top().right();

        Drawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("view/ui/background.png"))));
        bottomTable.setBackground(backgroundDrawable);
    }

    private void createActionButtons() {
        actionTable.clear();
        actionTable.setVisible(true);

        addActionButton("Fight", battleController::onFightButtonSelected);
        addActionButton("Bag", battleController::onBagButtonSelected);
        addActionButton("Pokemon", battleController::onPokemonButtonSelected);
        addActionButton("Run", battleController::onRunButtonSelected);
    }

    public void showMoves(List<MoveData> moves) {
        actionTable.clear();
        actionTable.setVisible(true);

        for (MoveData move : moves) {
            TextButton moveButton = new TextButton(move.displayMove(), buttonStyle);
            moveButton.getLabel().setWrap(true);
            moveButton.getLabelCell().width(200);

            moveButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    actionTable.setVisible(false);
                    battleController.onPlayerMoveSelected(move);
                }
            });

            actionTable.add(moveButton).width(200).height(40).pad(5);
            int rows = actionTable.getChildren().size;
            if (rows % 2 == 0) actionTable.row();
        }

        actionTable.pack();
    }

    private void addActionButton(String s, Runnable action) {
        TextButton textButton = new TextButton(s, buttonStyle);

        textButton.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                action.run();
            }
        });

        actionTable.add(textButton).width(200).height(40).pad(5);

        if (actionTable.getChildren().size % 2 == 0){
            actionTable.row();
        }
    }

    public void updatePlayerUI(PokemonData pokemonData, Runnable onFinish){
        playerHpBar.addAction(
            Actions.sequence(
                Actions.run(() -> {
                    playerHpBar.setValue(pokemonData.currentHp());
                    playerHpValueLabel.setText(pokemonData.currentHp() + "/" + pokemonData.maxHp());
                    updateLifeBarColor(playerHpBar);
                }),
                Actions.delay(1.0f),
                Actions.run(onFinish)
            )
        );
    }

    public void updateEnemyUI(PokemonData pokemonData, Runnable onFinish){
        enemyHpBar.addAction(
            Actions.sequence(
                Actions.run(() -> {
                    enemyHpBar.setValue(pokemonData.currentHp());
                    enemyHpValueLabel.setText(pokemonData.currentHp() + "/" + pokemonData.maxHp());
                    updateLifeBarColor(enemyHpBar);
                }),
                Actions.delay(1.0f),
                Actions.run(onFinish)
            )
        );
    }

    public void updateUI(BattleStep.Target target, PokemonData pokemonData, Runnable onFinish){
        if (target == BattleStep.Target.PLAYER){
             updatePlayerUI(pokemonData, onFinish);
        } else {
             updateEnemyUI(pokemonData, onFinish);
        }
    }

    public void showBattleScreen() {
        Gdx.input.setInputProcessor(stage);
    }

    public void setMessage(String message) {
        infoLabel.setText(message);
    }

    public void setMessage(String message, float duration, Runnable onComplete) {
        infoLabel.setText(message);

        infoLabel.addAction(
            Actions.sequence(
                Actions.delay(duration),
                Actions.run(onComplete)
            )
        );
    }

    public void enableFaintAnimation(Image sprite, float duration, Runnable onComplete) {
        sprite.addAction(
            Actions.sequence(
                Actions.parallel(
                    Actions.moveBy(0, -40, 0.4f),
                    Actions.fadeOut(0.4f)
                ),
                Actions.run(() -> {
                    if (onComplete != null) {
                        onComplete.run();
                    }
                })
            )
        );
    }

    public void enableHitAnimation(Image sprite, float duration, Runnable onComplete) {
        sprite.addAction(
            Actions.sequence(
                Actions.repeat(4, Actions.sequence(
                    Actions.fadeOut(0.1f),
                    Actions.fadeIn(0.1f))
                ),
                Actions.delay(duration),
                Actions.run(onComplete)
            )
        );
    }

    public void enableStatChangeAnimation(Image sprite, float duration, Runnable onComplete) {
        sprite.addAction(
            Actions.sequence(
                Actions.repeat(2, Actions.sequence(
                    Actions.color(Color.RED, 0.1f),
                    Actions.color(Color.WHITE, 0.1f))
                ), Actions.delay(duration),
                Actions.run(onComplete)
            )
        );
    }

    public boolean isShowingMessage() {
        return showingMessage;
    }

    public void setShowingMessage(boolean showingMessage) {
        this.showingMessage = showingMessage;
    }
}
