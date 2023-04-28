package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.utils.ResourceManager;
import com.mygdx.game.utils.SoundManager;
import com.mygdx.game.UI.LabelWidget;
import com.mygdx.game.UI.SliderWidget;
import com.mygdx.game.UI.TextButtonWidget;

public class SettingScreen extends Screens{
    private Stage stage;
    OrthographicCamera camera;
    Skin mySkin;
    Table table;
    int width;
    int height;
    private int currentvol;
    boolean isMuted;
    StretchViewport viewport;
    SliderWidget volumeSlider;
    SoundManager soundManager;
    LabelWidget settingsLabel;
    TextButtonWidget muteButton;
    TextButtonWidget backButton;

    public SettingScreen() {
        super();
    }

    @Override
    public void buildStage() {
        isMuted = false;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        soundManager = new SoundManager();
        camera = new OrthographicCamera();

        mySkin = ResourceManager.getInstance().getSkin("flat-earth");
        viewport = new StretchViewport(width, height, camera);
        camera.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        muteButton = new TextButtonWidget("Mute", mySkin);
        settingsLabel = new LabelWidget("Settings",mySkin,"title");
        backButton = new TextButtonWidget("Back", mySkin);
        volumeSlider = new SliderWidget(0f,100f, 20f,false, mySkin);
        volumeSlider.setVol(100);
//        volumeSlider.setVol(100*soundManager.getVolume());

        table = new Table(mySkin);
        table.setDebug(false);
        stage.addActor(table);
        table.setFillParent(true);
        table.top();
        table.add(backButton).left().padLeft(30).padTop(50);
        table.row();
        table.add(settingsLabel).expandX();
        table.row();
        table.add(muteButton).size(100, 50).padTop(20);
        table.row();
        table.add(volumeSlider).padTop(20);

        muteButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                if (!isMuted){
                    isMuted = true;
                    muteButton.setText("Unmute");
                    SoundManager.getInstance().pauseMusic();
                    soundManager.pauseMusic();
                } else{
                    isMuted = false;
                    muteButton.setText("Mute");
                    SoundManager.getInstance().playMusic();
                    SoundManager.getInstance().setLooping(true);
                }
            }
        });
        volumeSlider.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundManager.getInstance().setVolume((volumeSlider.getValue() / 100));
            }
        });
        backButton.addListener(new ClickListener(){

            @Override
            public void clicked (InputEvent event, float x, float y) {
                ScreenManager.getInstance().displayScreen(GameStateScreens.TITLE);

            }
        });
    }

//    TODO: Add player name
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render(delta);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }
}
