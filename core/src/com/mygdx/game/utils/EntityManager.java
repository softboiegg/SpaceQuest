package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.*;
import com.mygdx.game.Entities.Pools.*;
import com.mygdx.game.Interfaces.iCollide;
import com.badlogic.gdx.utils.Array;


public class EntityManager {

    private static EntityManager instance;
    private MovingStarPool movingStarPool;
    private PlayerShip playerShip;
    private PlayerBulletPool playerBulletPool;
    private EnemyShipPool enemyShipPool;
    private EnemyBulletPool enemyBulletPool;
    private ExplosionPool explosionPool;

    private EntityManager() {
        movingStarPool = new MovingStarPool();
        playerBulletPool = new PlayerBulletPool();
        enemyShipPool = new EnemyShipPool();
        enemyBulletPool = new EnemyBulletPool();
        explosionPool = new ExplosionPool();
    }

    public static EntityManager getInstance() {
        if (instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    public MovingStarPool getMovingStarPool() {
        return movingStarPool;
    }


    public PlayerBulletPool getPlayerBulletPool() {
        return playerBulletPool;
    }

    public EnemyShipPool getEnemyShipPool() {
        return enemyShipPool;
    }

    public EnemyBulletPool getEnemyBulletPool() {
        return enemyBulletPool;
    }

    public ExplosionPool getExplosionPool() {
        return explosionPool;
    }

    public Array<iCollide> getCollidableEntities() {
        Array<iCollide> collidables = new Array<>();

        collidables.add(playerShip);
        collidables.addAll(playerBulletPool.getActiveObjects());
        collidables.addAll(enemyShipPool.getActiveObjects());
        collidables.addAll(enemyBulletPool.getActiveObjects());

        return collidables;
    }

    public void checkCollisions() {
        Array<iCollide> collidables = getCollidableEntities();
        for (int i = 0; i < collidables.size; i++) {
            iCollide entity1 = collidables.get(i);
            for (int j = i + 1; j < collidables.size; j++) {
                iCollide entity2 = collidables.get(j);
                if (entity1.checkCollision(entity2)) {
                    entity1.handleCollision(entity2);
                    entity2.handleCollision(entity1);
                }
            }
        }
    }


    public void update(float delta) {
        checkCollisions();
        for (MovingStar star : movingStarPool.getActiveObjects()) {
            star.update(delta);
            if (star.checkScreenBounds()) {
                star.handleOutOfScreen();
            }
        }

            playerShip.update(delta);
            if (playerShip.checkScreenBounds()) {
                playerShip.handleOutOfScreen();
            }


        for (PlayerBullet playerBullet : playerBulletPool.getActiveObjects()) {
            playerBullet.update(delta);
            if (playerBullet.checkScreenBounds()) {
                playerBullet.handleOutOfScreen();
                playerBulletPool.free(playerBullet);
            }
        }

        for (EnemyShip enemyShip : enemyShipPool.getActiveObjects()) {
            enemyShip.update(delta);
            if (enemyShip.checkScreenBounds()) {
                enemyShipPool.free(enemyShip);
                enemyShip.handleOutOfScreen();
            }
        }

        for (EnemyBullet enemyBullet : enemyBulletPool.getActiveObjects()) {
            enemyBullet.update(delta);
            if (enemyBullet.checkScreenBounds()) {
                enemyBulletPool.free(enemyBullet);
                enemyBullet.handleOutOfScreen();
            }
        }

        for (Explosion explosion : explosionPool.getActiveObjects()) {
            explosion.update(delta);
//            if (explosion.checkScreenBounds()) {
//                explosionPool.free(explosion);
//                explosion.handleOutOfScreen();
//            }
        }

    }

    public void draw(SpriteBatch batch) {
        for (MovingStar star : movingStarPool.getActiveObjects()) {
            star.draw(batch);
        }


        playerShip.draw(batch);

        for (PlayerBullet playerBullet : playerBulletPool.getActiveObjects()) {
            playerBullet.draw(batch);
        }

        for (EnemyShip enemyShip : enemyShipPool.getActiveObjects()){
            enemyShip.draw(batch);
        }

        for (EnemyBullet enemyBullet : enemyBulletPool.getActiveObjects()){
            enemyBullet.draw(batch);
        }

        for (Explosion explosion : explosionPool.getActiveObjects()) {
            explosion.draw(batch);
        }
    }

    public void setPlayerShip(PlayerShip playerShip) {
        this.playerShip = playerShip;
    }
}