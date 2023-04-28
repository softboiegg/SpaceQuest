package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Interfaces.iCollide;
import com.mygdx.game.Screens.ScreenManager;
import com.mygdx.game.utils.ResourceManager;

import com.mygdx.game.utils.EntityManager;
import com.mygdx.game.utils.GameData;

public class PlayerShip extends Ships {
    private float fireRate;
    private float fireTimer;

    public PlayerShip(int x, int y) {
        super(ResourceManager.getInstance().getTexture("player"), 100, 10,5);
        this.setX(x);
        this.setY(y);
        fireRate = GameData.getInstance().getPlayerFireRate();
        fireTimer = 0;
    }

    @Override
    public boolean checkCollision(iCollide ce) {
        if (ce instanceof EnemyBullet) {
            return this.getBoundingRectangle().overlaps(((EnemyBullet) ce).getBoundingRectangle());
        }
        else {
            return false;
        }
    }

    @Override
    public void handleCollision(iCollide ce) {
        if (ce instanceof EnemyBullet) {
            System.out.println("player bullet hit enemy!");
            takeDamage(((EnemyBullet) ce).getDamage());
            System.out.println(getHealth());
            GameData.getInstance().setHealth(this.getHealth());
        }
    }

    @Override
    public boolean checkScreenBounds() {
        return (this.getX() < 0) || (this.getX() + this.getWidth() > Gdx.graphics.getWidth());
    }

    @Override
    public void handleOutOfScreen() {

        if (this.getX() < 0) {
            this.setX(0);
        } else if (this.getX() + this.getWidth() > Gdx.graphics.getWidth()) {
            this.setX(Gdx.graphics.getWidth() - this.getWidth());
        }
    }

    @Override
    public void update(float delta) {
        if (checkScreenBounds()) {
            handleOutOfScreen();
        };
        fireTimer += delta;
            shoot();
        if (this.getHealth() <= 0) {
            EntityManager.getInstance().getExplosionPool().obtain((int)(getX() - getWidth()/2), (int)(getY() - 80));
            this.destroy();
        }
    }

    public void shoot() {
        if (fireTimer >= 1 / fireRate) {
            float bulletX = this.getX() + (this.getWidth() / 2);
            float bulletY = this.getY() + this.getHeight();
            EntityManager.getInstance().getPlayerBulletPool().obtain((int)bulletX, (int)bulletY, this.getAttackDmg());
            fireTimer = 0;
        }
    }


    public void moveLeft() {
        this.setX(this.getX() - this.getSpeed());
    }

    public void moveRight() {
        this.setX(this.getX() + this.getSpeed());
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(this.getTexture(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public void reset() {

    }
}
