package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public abstract class Bullet extends CollidableEntity {

    private int velocityY;
    private int damage;
    private boolean destroyed = false;

    public Bullet(Texture texture, float x, float y, int velocityY, int damage) {
        super(texture);
        this.setX(x - (this.getWidth()/2));
        this.setY(y + this.getHeight());
        this.velocityY = velocityY;
        this.damage = damage;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void destroy() {
        this.destroyed = true;
    }
}
