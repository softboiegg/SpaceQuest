package com.mygdx.game.Entities.Generator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Entities.MovingStar;
import com.mygdx.game.Entities.Pools.MovingStarPool;
import com.mygdx.game.utils.EntityManager;

public class MovingStarGenerator {
    private MovingStarPool movingStarPool;
    private int maxMovingStars;

    public MovingStarGenerator(int maxMovingStars) {
        this.maxMovingStars = maxMovingStars;
        generate();
    }

    private void generate() {
        for (int i = 0; i < maxMovingStars; i++) {
            MovingStar movingStar = EntityManager.getInstance().getMovingStarPool().obtain();
        }
    }

}
