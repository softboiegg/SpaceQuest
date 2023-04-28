package com.mygdx.game.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LabelWidget extends Label {

    public LabelWidget(CharSequence text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }

    public void setProperties( int width, int height, float x, float y, int alignment){
        this.setSize(width,height);
        this.setPosition(x,y);
        this.setAlignment(alignment);

    }

}
