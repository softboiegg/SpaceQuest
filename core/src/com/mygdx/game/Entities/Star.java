package com.mygdx.game.Entities;

import com.mygdx.game.utils.ResourceManager;

public abstract class Star extends NonCollidableEntity {

    public Star() {
        super(ResourceManager.getInstance().getTexture("star"));
    }

}
