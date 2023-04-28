package com.mygdx.game.Entities.Pools;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.Entities.Explosion;
import com.mygdx.game.Entities.PlayerShip;

public class ExplosionPool extends Pool<Explosion> {

    private Array<Explosion> activeObjects;

    public ExplosionPool() {
        activeObjects = new Array<Explosion>();
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(-100, -100);
    }

    public Explosion obtain(int x, int y) {
        Explosion explosion = super.obtain();
        explosion.setPosition(x, y);
        TextureRegion firstFrame = explosion.getAnimation().getKeyFrame(0);
        explosion.setRegion(firstFrame);
        activeObjects.add(explosion);
        return explosion;
    }

    @Override
    public void free(Explosion explosion) {
        super.free(explosion);
        activeObjects.removeValue(explosion, true);
    }

    public Array<Explosion> getActiveObjects() {
        return activeObjects;
    }
}
