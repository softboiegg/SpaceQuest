package com.mygdx.game.Entities.Generator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.EnemyShip;
import com.mygdx.game.Entities.Pools.EnemyShipPool;
import com.mygdx.game.utils.EntityManager;

public class EnemyShipGenerator {

    private EnemyShipPool enemyShipPool;
    private float timeSinceLastSpawn = 7;
    private float spawnInterval;
    private int maxEnemies;

    public EnemyShipGenerator(float spawnInterval, int maxEnemies) {
        this.spawnInterval = spawnInterval;
        this.maxEnemies = maxEnemies;
        enemyShipPool = EntityManager.getInstance().getEnemyShipPool();
    }

    public void update(float delta) {
        timeSinceLastSpawn += delta;

        if (timeSinceLastSpawn >= spawnInterval && EntityManager.getInstance().getEnemyShipPool().getActiveObjects().size < maxEnemies) {
            System.out.println(EntityManager.getInstance().getEnemyShipPool().getActiveObjects().size);
            System.out.println(maxEnemies);

            // Obtain a new enemy ship from the pool with random x and y positions
            int x = MathUtils.random(0, Gdx.graphics.getWidth() - 100);
            int y = Gdx.graphics.getHeight();
            int damage = 5;
            EnemyShip enemyShip = enemyShipPool.obtain(x, y, damage);

            // Set the enemy ship's movement direction and target height
            enemyShip.setMovingDown(true);
            enemyShip.setMovingRight(MathUtils.randomBoolean());
            enemyShip.setTargetHeight(MathUtils.random(Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 3), Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 5)));

            // Reset the timer
            timeSinceLastSpawn = 0;
        }
    }
}

