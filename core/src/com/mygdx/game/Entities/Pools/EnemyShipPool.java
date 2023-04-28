package com.mygdx.game.Entities.Pools;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.Entities.EnemyShip;
import com.mygdx.game.Entities.PlayerBullet;

public class EnemyShipPool extends Pool<EnemyShip> {

    private Array<EnemyShip> activeObjects;

    public EnemyShipPool() {
        activeObjects = new Array<EnemyShip>();
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip();
    }

    public EnemyShip obtain(int x, int y, int damage) {
        EnemyShip enemyShip = super.obtain();
        enemyShip.setPosition(x, y);
        enemyShip.setAttackDmg(damage);
        activeObjects.add(enemyShip);
        return enemyShip;
    }

    @Override
    public void free(EnemyShip enemyShip) {
        super.free(enemyShip);
        activeObjects.removeValue(enemyShip, true);
    }

    public Array<EnemyShip> getActiveObjects() {
        return activeObjects;
    }
}
