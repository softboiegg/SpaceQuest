package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ImageButtonWidget extends ImageButton {
    public ImageButtonWidget(Skin skin, String style) {
        super(skin, style);
    }
    public void setProperties( float width, float height, float x, float y, String unchecked, String checked){
        this.setSize(width, height);
        this.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(unchecked))));
        this.getStyle().imageChecked = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(checked))));
        this.setPosition(x,y);

    };

}
