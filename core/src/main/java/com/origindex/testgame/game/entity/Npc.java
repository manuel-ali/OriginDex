package com.origindex.testgame.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.origindex.testgame.game.logic.PokemonFactory;
import com.origindex.testgame.view.MapScreen;

public class Npc extends Trainer{
    private int tileX, tileY;
    private float x, y;
    private boolean interacting;
    private String actionType;
    private Player.Direction direction;
    private TextureRegion currentFrame, defaultSprite ,standDown, standUp, standLeft, standRight;

    public Npc(int tileX, int tileY, int tileSize, String actionType, String spriteName, String defaultDirection) {

        this.tileX = tileX;
        this.tileY = tileY;
        this.x = tileX * tileSize;
        this.y = tileY * tileSize;
        this.actionType = actionType;
        if (actionType.equals("battle")){
            this.activePokemon = PokemonFactory.getRandomPokemon();
        }
        parseDirection(defaultDirection);
        loadAnimations(spriteName);
    }

    private void loadAnimations(String spriteName){
        currentFrame = new TextureRegion();
        standDown = MapScreen.npcAtlas.findRegion(spriteName + "-0");
        standUp = MapScreen.npcAtlas.findRegion(spriteName + "-1");
        standRight = MapScreen.npcAtlas.findRegion(spriteName + "-2");

        standLeft = new TextureRegion(standRight);
        standLeft.flip(true, false);

        defaultSprite = standDown;
    }

    private void parseDirection(String defaultDirection) {
        switch (defaultDirection) {
            case "up":
                direction = Player.Direction.UP;
                break;
            case "down":
                direction = Player.Direction.DOWN;
                break;
            case "left":
                direction = Player.Direction.LEFT;
                break;
            case "right":
                direction = Player.Direction.RIGHT;
                break;
        }
    }

    public void update(Player.Direction playerDirection) {
        if (interacting){
            switch (playerDirection) {
                case UP:
                    direction = Player.Direction.DOWN;
                    break;
                case DOWN:
                    direction = Player.Direction.UP;
                    break;
                case LEFT:
                    direction = Player.Direction.RIGHT;
                    break;
                case RIGHT:
                    direction = Player.Direction.LEFT;
                    break;
                default:
                    direction = Player.Direction.DOWN;
                    break;
            }
        }else {
            direction = Player.Direction.DOWN;
        }

    }

    public void render(SpriteBatch batch) {
        switch (direction) {
            case UP:
                currentFrame = standUp;
                break;
            case DOWN:
                currentFrame = standDown;
                break;
            case LEFT:
                currentFrame = standLeft;
                break;
            case RIGHT:
                currentFrame = standRight;
                break;
            default:
                currentFrame = standDown;
                break;
        }

        batch.draw(currentFrame, x, y);
    }

    public void dispose() {
        MapScreen.npcAtlas.dispose();
    }

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isInteracting() {
        return interacting;
    }

    public void setInteracting(boolean interacting) {
        this.interacting = interacting;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}


