package com.mygdx.game.Entities.Pools;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.Entities.EnemyBullet;
import com.mygdx.game.Entities.PlayerBullet;

public class EnemyBulletPool extends Pool<EnemyBullet> {

    private Array<EnemyBullet> activeObjects;

    public EnemyBulletPool() {
        activeObjects = new Array<>();
    }

    @Override
    protected EnemyBullet newObject() {
        return new EnemyBullet(0, 0,0);
    }

    public EnemyBullet obtain(int x, int y, int damage) {
        EnemyBullet enemyBullet = super.obtain();
        enemyBullet.setPosition((x - enemyBullet.getWidth()/2), y);
        enemyBullet.setDamage(damage);
        activeObjects.add(enemyBullet);
//        System.out.println(activeObjects.size);
        return enemyBullet;
    }

    @Override
    public void free(EnemyBullet enemyBullet) {
        super.free(enemyBullet);
        activeObjects.removeValue(enemyBullet, true);
    }

    public Array<EnemyBullet> getActiveObjects() {
        return activeObjects;
    }
}
