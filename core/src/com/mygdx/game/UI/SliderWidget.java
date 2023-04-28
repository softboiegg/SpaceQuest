package com.mygdx.game.UI;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;


public class SliderWidget extends Slider {

    public SliderWidget(float min, float max, float stepSize, boolean vertical, Skin skin) {
        super(min, max, stepSize, vertical, skin);
    }
    public void setVol(float dvalue){
        this.setValue(dvalue);
    }
}

