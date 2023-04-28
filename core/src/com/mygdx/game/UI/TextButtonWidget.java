package com.mygdx.game.UI;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class TextButtonWidget extends TextButton {
    public TextButtonWidget(String text, Skin skin) {
        super(text, skin);
    }
    public void setProperties( float width, float height, float x, float y){
        this.setSize(width, height);
        this.setPosition(x,y);
    };

}


