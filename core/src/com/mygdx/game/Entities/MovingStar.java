package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MovingStar extends Star {
    private Vector2 velocity;

    public MovingStar() {
        super();

        // spawn the star at a random position on the top of the screen
        float x = MathUtils.random(0, Gdx.graphics.getWidth());
        float y = MathUtils.random(0, Gdx.graphics.getHeight());
//        if (MathUtils.randomBoolean(0.5f)) {
//            y /=  2;
//        }
        float randomValue = MathUtils.random(0, 1);

        if (randomValue < 0.33f) {
            y /= 3;
        } else if (randomValue >= 0.33f && randomValue < 0.66f) {
            y *= 2f / 3f;
        } else {

        }
        this.setPosition(x, y);

        // set the star velocity to move from top to bottom of the screen
        float vy = MathUtils.random(-100f, -30f);
        velocity = new Vector2(0, vy);
    }


    public void update(float delta) {
        // update the position of the star based on its velocity
        setPosition(getX() + velocity.x * delta, getY() + velocity.y * delta);

        // if the star goes off the bottom of the screen, respawn it at the top
        checkScreenBounds();
    }

    @Override
    public boolean checkScreenBounds() {
        return (getY() + getHeight() < 0);
    }

    @Override
    public void handleOutOfScreen() {
        reset();
    }

    @Override
    public void reset() {
        // set the star position to a random position on the top of the screen
        float x = MathUtils.random(0, Gdx.graphics.getWidth());
        float y = MathUtils.random(Gdx.graphics.getHeight() - getHeight() / 2, Gdx.graphics.getHeight() + getHeight() / 2);
        setPosition(x, y);

        // set the star velocity to move from top to bottom of the screen
        float vy = MathUtils.random(-100f, -30f);
        velocity.set(0, vy);
    }
}
