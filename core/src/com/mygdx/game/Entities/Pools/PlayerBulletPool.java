package com.mygdx.game.Entities.Pools;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.Entities.PlayerBullet;

public class PlayerBulletPool extends Pool<PlayerBullet> {

    private Array<PlayerBullet> activeObjects;

    public PlayerBulletPool() {
        activeObjects = new Array<>();
    }

    @Override
    protected PlayerBullet newObject() {
        return new PlayerBullet(0, 0,0);
    }

    public PlayerBullet obtain(int x, int y, int damage) {
        PlayerBullet playerBullet = super.obtain();
        playerBullet.setPosition((x - playerBullet.getWidth()/2), y);
        playerBullet.setDamage(damage);
        activeObjects.add(playerBullet);
//        System.out.println(activeObjects.size);
        return playerBullet;
    }

    @Override
    public void free(PlayerBullet playerBullet) {
        super.free(playerBullet);
        activeObjects.removeValue(playerBullet, true);
    }

    public Array<PlayerBullet> getActiveObjects() {
        return activeObjects;
    }
}
