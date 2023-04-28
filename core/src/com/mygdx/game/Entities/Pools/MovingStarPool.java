package com.mygdx.game.Entities.Pools;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.Entities.MovingStar;
import com.mygdx.game.Entities.PlayerBullet;

public class MovingStarPool extends Pool<MovingStar> {

    private Array<MovingStar> activeObjects;

    public MovingStarPool() {
        activeObjects = new Array<>();
    }

    @Override
    protected MovingStar newObject() {
        return new MovingStar();
    }

    public MovingStar obtain() {
        MovingStar movingStar = super.obtain();
        activeObjects.add(movingStar);
        return movingStar;
    }

    @Override
    public void free(MovingStar movingStar) {
        super.free(movingStar);
        activeObjects.removeValue(movingStar, true);
    }

    public Array<MovingStar> getActiveObjects() {
        return activeObjects;
    }
}
