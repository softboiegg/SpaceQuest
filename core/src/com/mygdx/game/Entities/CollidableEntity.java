package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Interfaces.iCollide;

public abstract class CollidableEntity extends ParentEntity implements iCollide{

    public CollidableEntity() {};

    public CollidableEntity(Texture texture) {
        super(texture);
    }
}