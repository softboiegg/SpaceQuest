package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Interfaces.iCollide;
import com.mygdx.game.utils.GameData;
import com.mygdx.game.utils.ResourceManager;
import com.mygdx.game.utils.EntityManager;

public class EnemyShip extends Ships {

    private float fireRate;
    private float fireTimer;
    private boolean movingDown;
    private boolean movingRight;
    private float targetHeight;

    public EnemyShip() {
        super(ResourceManager.getInstance().getTexture("enemy1"), 5, 2, 5);

        fireRate = 0.5f;
        fireTimer = 0;

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        float x = MathUtils.random(0, screenWidth - this.getWidth());
        float y = screenHeight;
        this.setPosition(x, y);
        movingDown = true;
        movingRight = MathUtils.randomBoolean();
        targetHeight = MathUtils.random(screenHeight - (screenHeight / 3), screenHeight - (screenHeight / 5));
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public float getTargetHeight() {
        return targetHeight;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public void setTargetHeight(float targetHeight) {
        this.targetHeight = targetHeight;
    }

    @Override
    public boolean checkCollision(iCollide ce) {
        if (ce instanceof PlayerBullet) {
            return this.getBoundingRectangle().overlaps(((PlayerBullet) ce).getBoundingRectangle());
        }
        else {
            return false;
        }
    }

    @Override
    public void handleCollision(iCollide ce) {
        if (ce instanceof PlayerBullet) {
            System.out.println("player bullet hit enemy!");
            takeDamage(((PlayerBullet) ce).getDamage());
        }
    }

    @Override
    public boolean checkScreenBounds() {
        return false;
    }

    @Override
    public void handleOutOfScreen() {

    }

    @Override
    public void update(float delta) {
        updateMovement();
        checkScreenBounds();
        fireTimer += delta;
        shoot();
        if (this.getHealth() <= 0) {
            EntityManager.getInstance().getExplosionPool().obtain((int)(getX() - getWidth()/2), (int)(getY() - 80));
            this.destroy();
            EntityManager.getInstance().getEnemyShipPool().free(this);
            GameData.getInstance().addScore(100);
        }
    }

    public void shoot() {
        if (fireTimer >= 1 / fireRate) {
            float bulletX = this.getX() + (this.getWidth() / 2);
            float bulletY = this.getY();
            EntityManager.getInstance().getEnemyBulletPool().obtain((int)bulletX, (int)bulletY, this.getAttackDmg());
            fireTimer = 0;

        }
    }

    public void updateMovement() {
        if (movingDown) {
            this.setY(this.getY() - this.getSpeed());
            if (this.getY() <= targetHeight) {
                movingDown = false;
            }
        }

        if (Gdx.graphics.getWidth() <= this.getX() + this.getWidth()) {
            movingRight = false;
        } else if (this.getX() <= 0) {
            movingRight = true;
        }

        if (movingRight) {
            this.setX(this.getX() + this.getSpeed());
        } else {
            this.setX(this.getX() - this.getSpeed());
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(this.getTexture(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public void reset() {
        setDestroyed(false);
        setHealth(5);
        setMovingDown(true);
        setMovingRight(MathUtils.randomBoolean());
        setTargetHeight(MathUtils.random(Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 3), Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 5)));
//        setPosition(MathUtils.random(0, Gdx.graphics.getWidth() - this.getWidth()), Gdx.graphics.getHeight());
        setPosition(-100, - 100);

//        float screenWidth = Gdx.graphics.getWidth();
//        float screenHeight = Gdx.graphics.getHeight();
//
//        float x = MathUtils.random(0, screenWidth - this.getWidth());
//        float y = screenHeight;
//        this.setPosition(x, y);
//        movingDown = true;
//        movingRight = MathUtils.randomBoolean();
//        targetHeight = MathUtils.random(screenHeight - (screenHeight / 3), screenHeight - (screenHeight / 5));
    }
}
