package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.Entities.PlayerShip;

public class InputManager extends InputAdapter {

    private PlayerShip playerShip;
    private int keyLeft;
    private int keyRight;
    private int keyUp;
    private int keyDown;

    public InputManager(PlayerShip playerShip, int keyUp, int keyDown, int keyLeft, int keyRight)  {

        this.playerShip = playerShip;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
    }

    public int getKeyLeft() {
        return keyLeft;
    }

    public void setKeyLeft(int keyLeft) {
        this.keyLeft = keyLeft;
    }

    public int getKeyRight() {
        return keyRight;
    }

    public void setKeyRight(int keyRight) {
        this.keyRight = keyRight;
    }

    public int getKeyUp() {
        return keyUp;
    }

    public void setKeyUp(int keyUp) {
        this.keyUp = keyUp;
    }

    public int getKeyDown() {
        return keyDown;
    }

    public void setKeyDown(int keyDown) {
        this.keyDown = keyDown;
    }

    public void update() {
        if (Gdx.input.isKeyPressed(keyLeft)) {
            playerShip.moveLeft();
        }
        if (Gdx.input.isKeyPressed(keyRight)) {
            playerShip.moveRight();        }
    }
}

