package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.ResourceManager;

public class Explosion extends Sprite {
    private static final int FRAME_COLS = 8, FRAME_ROWS = 8;
    private Animation<TextureRegion> animation;
    private Texture explosionSheet;
    private float stateTime;

    public Explosion(int x, int y) {
        explosionSheet = ResourceManager.getInstance().getTexture("explosion");
        TextureRegion[][] tmp = TextureRegion.split(explosionSheet,
                explosionSheet.getWidth() / FRAME_COLS,
                explosionSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] animationFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                animationFrames[index++] = tmp[i][j];
            }
        }

        animation = new Animation<TextureRegion>(0.025f, animationFrames);

        // Set the position of the sprite using the input Vector2
        setPosition(x, y);

        stateTime = 0f;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    @Override
    public void draw(Batch batch) {
        // Get the current frame of the animation based on the state time
        TextureRegion currentFrame = animation.getKeyFrame(stateTime);

        // Set the sprite's texture to the current frame of the animation
        if (stateTime >= 0.025f * 64) {
            currentFrame =  animation.getKeyFrame(0);
        }
        else {
            setRegion(currentFrame);
        }


        // Draw the sprite
        batch.draw(currentFrame, getX() - (75 /2), getY() + (75 /2));
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
        setRegion(animation.getKeyFrame(stateTime, true));
    }

    public void dispose() {
        explosionSheet.dispose();
    }

    public void reset() {

    }
}