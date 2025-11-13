package com.origindex.testgame.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.origindex.testgame.game.logic.PokemonFactory;
import com.origindex.testgame.view.MapScreen;

public class Player extends Trainer{
    private final float WALKSPEED = 50f;
    private final float RUNSPEED = 100f;
    private int tileX, tileY;
    private float x, y;
    private float moveSpeed;
    private boolean moving = false;
    private float targetX, targetY;
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    private Direction direction;
    private Animation<TextureRegion> walkDown, walkUp, walkLeft, walkRight;
    private TextureRegion currentFrame, standDown, standUp, standLeft, standRight;
    private float stateTime;


    public Player(int tileX, int tileY, int tileSize) {
        this.activePokemon = PokemonFactory.getRandomPokemon();
        this.tileX = tileX;
        this.tileY = tileY;
        this.x = tileX * tileSize;
        this.y = tileY * tileSize;
        this.targetX = this.x;
        this.targetY = this.y;
        this.direction = Direction.DOWN;
        loadAnimations();
    }

    private void loadAnimations() {
        //Animaciones de caminar
        walkDown = new Animation<>(0.2f,
            MapScreen.npcAtlas.findRegion("npc_9-4"),
            MapScreen.npcAtlas.findRegion("npc_9-5"));
        walkUp = new Animation<>(0.2f,
            MapScreen.npcAtlas.findRegion("npc_9-6"),
            MapScreen.npcAtlas.findRegion("npc_9-7"));
        walkRight = new Animation<>(0.2f,
            MapScreen.npcAtlas.findRegion("npc_9-8"),
            MapScreen.npcAtlas.findRegion("npc_9-9"));
        //Giro la animacion de caminar a la derecha para que camine a la izquierda
        TextureRegion left1 = new TextureRegion(MapScreen.npcAtlas.findRegion("npc_9-8"));
        TextureRegion left2 = new TextureRegion(MapScreen.npcAtlas.findRegion("npc_9-9"));
        left1.flip(true, false);
        left2.flip(true, false);
        walkLeft = new Animation<>(0.2f, left1, left2);

        //Animaciones de pie
        currentFrame = new TextureRegion();
        standDown = MapScreen.npcAtlas.findRegion("npc_9-0");
        standUp = MapScreen.npcAtlas.findRegion("npc_9-1");
        standRight = MapScreen.npcAtlas.findRegion("npc_9-2");
        //Giro la animacion de pie a la derecha para que mire a la izquierda
        standLeft = new TextureRegion(standRight);
        standLeft.flip(true, false);
    }

    public void update(float delta) {
        moveSpeed = isRunning() ? RUNSPEED : WALKSPEED;

        if (!moving) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                moveTo(tileX, tileY + 1);
                direction = Direction.UP;
                stateTime = 0;
            } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                moveTo(tileX, tileY - 1);
                direction = Direction.DOWN;
                stateTime = 0;
            } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                moveTo(tileX - 1, tileY);
                direction = Direction.LEFT;
                stateTime = 0;
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                moveTo(tileX + 1, tileY);
                direction = Direction.RIGHT;
                stateTime = 0;
            }
        } else {
            float dx = targetX - x;
            float dy = targetY - y;
            float dist = moveSpeed * delta;

            if (Math.abs(dx) <= dist && Math.abs(dy) <= dist) {
                x = targetX;
                y = targetY;
                moving = false;
            } else {
                x += Math.signum(dx) * dist;
                y += Math.signum(dy) * dist;
            }
        }

        float animationSpeedFactor = moveSpeed / WALKSPEED;
        stateTime += delta * animationSpeedFactor;
    }

    private void moveTo(int newTileX, int newTileY) {
        Rectangle nextPosition = new Rectangle(newTileX * 16, newTileY * 16, 16, 16); //Calculo la nueva posición del jugador
        if (!MapScreen.isColliding(nextPosition)){ //Verifico si la nueva posición no colisiona con ningún objeto
            tileX = newTileX;
            tileY = newTileY;
            targetX = tileX * 16;
            targetY = tileY * 16;
            moving = true;
        }
    }

    public void render(SpriteBatch batch) {
        if (moving) {
            switch (direction) {
                case UP:
                    currentFrame = walkUp.getKeyFrame(stateTime, true);
                    break;
                case DOWN:
                    currentFrame = walkDown.getKeyFrame(stateTime, true);
                    break;
                case LEFT:
                    currentFrame = walkLeft.getKeyFrame(stateTime, true);
                    break;
                case RIGHT:
                    currentFrame = walkRight.getKeyFrame(stateTime, true);
                    break;
                default:
                    currentFrame = standDown;
                    break;
            }
        } else {
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
        }

        batch.draw(currentFrame, x, y);
    }

    public boolean isRunning() {
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            return true;
        }

        return false;
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

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
