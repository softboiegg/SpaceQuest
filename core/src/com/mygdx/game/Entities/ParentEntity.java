package com.mygdx.game.Entities;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.Interfaces.iBoundary;

public abstract class ParentEntity extends Sprite implements iBoundary, Pool.Poolable {

    public ParentEntity(){};

    public ParentEntity(Texture texture) {
        super(texture);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(getTexture(),this.getX(),this.getY());

    }


    public abstract void update(float delta);

    public void setSize(float newHeight) {
        float aspectRatio = (float) this.getTexture().getWidth() / (float) this.getTexture().getHeight();
        float newWidth = newHeight * aspectRatio; // Calculate the new width based on the aspect ratio
        setSize(newWidth, newHeight);
        setBounds(getX(), getY(), newWidth, newHeight);
    }
}

