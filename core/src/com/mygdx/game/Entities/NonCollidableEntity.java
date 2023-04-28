package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;

public abstract class NonCollidableEntity extends ParentEntity{

    public NonCollidableEntity() {};

    public NonCollidableEntity(Texture texture) {
        super(texture);
    }
}