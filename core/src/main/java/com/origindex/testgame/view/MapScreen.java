package com.origindex.testgame.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.origindex.testgame.Main;
import com.origindex.testgame.game.entity.Npc;
import com.origindex.testgame.game.entity.Player;
import com.origindex.testgame.game.logic.battle.Battle;
import com.origindex.testgame.game.logic.battle.BattleManager;
import com.origindex.testgame.view.battle.BattleScreen;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MapScreen implements Screen {
    private final Main game;

    private OrthographicCamera camera;
    private FitViewport viewport;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Player player;

    private Stage stage;
    private Label interactionLabel;
    private Table interactionTable;
    private boolean showingMessage;

    private Vector2 lastPlayerPosition;

    private Queue<String> messageQueue = new LinkedList<>();

    private final int VIRTUAL_WIDTH = 480;
    private final int VIRTUAL_HEIGHT = 320;
    private final int TILE_SIZE = 16;

    private int mapPixelWidth, mapPixelHeight;
    private SpriteBatch batch;

    private Runnable onMessagesFinished;

    private static final Array<Rectangle> collisionRectangles = new Array<>();
    private static final Array<Polygon> collisionPolygons = new Array<>();
    private final Array<Npc> npcs = new Array<>();
    public static final TextureAtlas npcAtlas = new TextureAtlas(Gdx.files.internal("view/maps/tileSet/npcs_atlas.txt"));

    public MapScreen(Main game) {
        this.game = game;
        initializeMap();
        initializeMapSize();
        this.player = new Player(getSpawnTileX(map), getSpawnTileY(map), TILE_SIZE);
        loadCollisionObjects(map);
        initializeAtlas();
        initializeNpcs(map);
    }

    public void initializeMap(){
        this.map = new TmxMapLoader().load("view/maps/overWorldMap.tmx");
        this.batch = new SpriteBatch();
        this.mapRenderer = new OrthogonalTiledMapRenderer(map, batch);
    }

    public void initializeMapSize(){
        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);
        this.mapPixelWidth = mapWidth * TILE_SIZE;
        this.mapPixelHeight = mapHeight * TILE_SIZE;
    }

    public void initializeInteractionUI(){
        stage = new Stage(new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT));
        Skin skin = new Skin(Gdx.files.internal("view/uiskin.json"));
        interactionLabel = new Label("", skin, "small");
        interactionLabel.setWrap(true);
        interactionTable = new Table();
        interactionTable.setVisible(false);
        interactionTable.setSize(VIRTUAL_WIDTH, 40);
        interactionTable.setPosition(0, 0);
        interactionTable.add(interactionLabel).width(VIRTUAL_WIDTH - 20).padLeft(20).left();
        Texture bgTexture = new Texture(Gdx.files.internal("view/ui/background.png"));
        Drawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(bgTexture));
        interactionTable.setBackground(backgroundDrawable);
        stage.addActor(interactionTable);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);

        initializeInteractionUI();
        startFadeIn(1f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        updateCamera();
        camera.update();

        mapRenderer.setView(camera);
        mapRenderer.getBatch().enableBlending();
        mapRenderer.render();

        stage.act(delta);
        stage.draw();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.render(batch);
        for (Npc npc : npcs) {
            npc.render(batch);
        }
        batch.end();

        if (!showingMessage) {
            player.update(delta);
        }

        checkInteractions();
        handleDialogueInput();
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

    private void initializeNpcs(TiledMap map) {
        MapLayer interactionLayer = map.getLayers().get("Interacciones");
        for (MapObject object : interactionLayer.getObjects()) {
            if (object instanceof RectangleMapObject) {
                RectangleMapObject rectObj = (RectangleMapObject) object;
                Rectangle rect = rectObj.getRectangle();

                MapProperties props = object.getProperties();
                String type = (String) props.get("type");
                if ("npc".equals(type)) {
                    String actionType = (String) props.get("interaction");
                    String spriteName = (String) props.get("sprite");
                    String direction = (String) props.get("facing");

                    int npcTileX = (int) (rect.x / TILE_SIZE);
                    int npcTileY = (int) (rect.y / TILE_SIZE);

                    Npc npc = new Npc(npcTileX, npcTileY, TILE_SIZE, actionType, spriteName, direction);
                    npcs.add(npc);
                }
            }
        }
    }

    private void initializeAtlas() {
        for (TextureAtlas.AtlasRegion region : npcAtlas.getRegions()) {
            region.getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }
    }

    public static Vector2 getSpawnPoint(TiledMap map) {
        MapLayer spawnLayer = map.getLayers().get("Spawn");
        if (spawnLayer == null) return new Vector2(0, 0);

        for (MapObject object : spawnLayer.getObjects()) {
            if (object instanceof RectangleMapObject && "spawn".equals(object.getName())) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                return new Vector2(rect.x, rect.y);
            }
        }

        return new Vector2(0, 0);
    }

    public static int getSpawnTileX(TiledMap map) {
        return (int) getSpawnPoint(map).x / 16;
    }

    public static int getSpawnTileY(TiledMap map) {
        return (int) getSpawnPoint(map).y / 16;
    }



    private void updateCamera() {
        camera.position.set(player.getX(), player.getY(), 0);

        float halfWidth = camera.viewportWidth / 2;
        float halfHeight = camera.viewportHeight / 2;

        camera.position.x = Math.max(halfWidth, camera.position.x);
        camera.position.y = Math.max(halfHeight, camera.position.y);
        camera.position.x = Math.min(mapPixelWidth - halfWidth, camera.position.x);
        camera.position.y = Math.min(mapPixelHeight - halfHeight, camera.position.y);
    }

    private void loadCollisionObjects(TiledMap map) {
        MapLayer collisionLayer = map.getLayers().get("Colisiones");

        for (MapObject object : collisionLayer.getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                collisionRectangles.add(rect);
            } else if (object instanceof PolygonMapObject) {
                Polygon polygon = ((PolygonMapObject) object).getPolygon();
                float x = object.getProperties().get("x", Float.class);
                float y = object.getProperties().get("y", Float.class);
                polygon.setPosition(x, y);
                collisionPolygons.add(polygon);
            }
        }
    }

    public static boolean isColliding(Rectangle playerRect) {
        for (Rectangle rect : collisionRectangles) {
            if (rect.overlaps(playerRect)) return true;
        }

        return false;
    }

    public void checkInteractions() {
        if (showingMessage) return;

        int playerX = player.getTileX();
        int playerY = player.getTileY();

        for (Npc npc : npcs) {
            int npcX = npc.getTileX();
            int npcY = npc.getTileY();

            boolean adjacent =
                (playerX == npcX && playerY == npcY + 1) || // arriba
                    (playerX == npcX && playerY == npcY - 1) || // abajo
                    (playerX == npcX - 1 && playerY == npcY) || // izquierda
                    (playerX == npcX + 1 && playerY == npcY);   // derecha

            if (adjacent && Gdx.input.isKeyPressed(Input.Keys.E)) {
                npc.setInteracting(true);
                npc.update(player.getDirection());
                handleActionType(npc.getActionType(), npc);
            }
        }
    }

    public void handleActionType(String actionType, Npc npc) {
        switch (actionType) {
            case "battle":
                showMessages(Arrays.asList(
                    "Let's battle!"
                ), () -> {
                    handleBattleInteraction(npc);
                    endInteraction(npc);
                });
                break;
            case "talk":
                showMessages(Arrays.asList(
                    "Hola, soy un NPC.",
                    "Esta es una conversación.",
                    "Adiós."
                ), () -> endInteraction(npc));
                break;
            case "heal":
                flashScreen(stage, () -> {
                    player.getActivePokemon().setCurrentHP(player.getActivePokemon().getMaxHP());
                    showMessages(Arrays.asList("Your Pokemon is now healed.")
                    , () -> endInteraction(npc));
                }, 3, 0.1f);
                break;
            default:
                System.out.println("Acción no reconocida: " + actionType);
        }
    }

    public void flashScreen(Stage stage, Runnable afterFlash, int flashes, float speed) {
        Image overlay = new Image(new Texture("view/white.png"));
        overlay.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        overlay.getColor().a = 0f;
        stage.addActor(overlay);

        overlay.addAction(Actions.sequence(
            Actions.repeat(flashes, Actions.sequence(
                Actions.fadeIn(speed),
                Actions.fadeOut(speed)
            )),
            Actions.run(() -> {
                afterFlash.run();
                overlay.remove();
            })
        ));
    }

    private void handleBattleInteraction(Npc npc) {
        lastPlayerPosition = new Vector2(player.getX(), player.getY());
        Battle battle = new Battle(player.getActivePokemon(), npc.getActivePokemon());
        BattleManager battleManager = new BattleManager(battle);
        fadeOutAndSwitchScreen(game, new BattleScreen(battleManager, game, this), 1f);
    }

    public void restorePlayerPosition(Vector2 position) {
        if (position != null) {
            player.setX(position.x);
            player.setY(position.y);
        }
    }

    private void handleDialogueInput() {
        if (showingMessage && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (!messageQueue.isEmpty()) {
                interactionLabel.setText(messageQueue.poll());
            } else {
                interactionTable.setVisible(false);
                showingMessage = false;

                if (onMessagesFinished != null) {
                    onMessagesFinished.run();
                    onMessagesFinished = null;
                }
            }
        }
    }

    public void showMessages(List<String> messages, Runnable onFinish) {
        messageQueue.clear();
        messageQueue.addAll(messages);
        onMessagesFinished = onFinish;
        showingMessage = true;
        interactionTable.setVisible(true);
        interactionLabel.setText(messageQueue.poll());
    }

    public void endInteraction(Npc npc) {
        interactionTable.setVisible(false);
        showingMessage = false;
        npc.setInteracting(false);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        player.dispose();

        for (Npc npc : npcs) {
            npc.dispose();
        }
    }

    public Vector2 getLastPlayerPosition() {
        return lastPlayerPosition;
    }

    public void setLastPlayerPosition(Vector2 lastPlayerPosition) {
        this.lastPlayerPosition = lastPlayerPosition;
    }
}
