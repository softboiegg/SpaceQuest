package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;

public abstract class Ships extends CollidableEntity{

    private int health;
    private int speed;
    private int attackDmg;
    private boolean destroyed = false;

    public Ships(Texture texture, int health, int speed, int attackDmg) {
        super(texture);
        this.health = health;
        this.speed = speed;
        this.attackDmg = attackDmg;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttackDmg(int attackDmg) {
        this.attackDmg = attackDmg;
    }

    public void destroy() {
        this.destroyed = true;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void takeDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
    }
}
