package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Interfaces.iCollide;
import com.mygdx.game.utils.ResourceManager;
import com.mygdx.game.utils.EntityManager;

public class PlayerBullet extends Bullet {

    public PlayerBullet(float x, float y, int damage) {
        super(ResourceManager.getInstance().getTexture("playerBullet"), x, y, 10, damage);
        setX(getX() - (this.getWidth() / 2));
    }

    @Override
    public void update(float delta) {
        setY(getY() + getVelocityY());
    }

    @Override
    public boolean checkScreenBounds() {
        return (getY() >= Gdx.graphics.getHeight());
    }

    @Override
    public void handleOutOfScreen() {
        destroy();
    }

    @Override
    public boolean checkCollision(iCollide ce) {
        if (ce instanceof EnemyShip) {
            return this.getBoundingRectangle().overlaps(((EnemyShip) ce).getBoundingRectangle());
        }
        else {
        return false;}
    }

    @Override
    public void handleCollision(iCollide ce) {
        destroy();
        EntityManager.getInstance().getPlayerBulletPool().free(this);
    }

    @Override
    public void reset() {
        setDestroyed(false);
        setVelocityY(10);
    }
}
