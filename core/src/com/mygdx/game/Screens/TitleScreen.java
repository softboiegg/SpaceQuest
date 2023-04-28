package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.utils.ResourceManager;
import com.mygdx.game.utils.SoundManager;
import com.mygdx.game.UI.LabelWidget;
import com.mygdx.game.UI.TextButtonWidget;
import com.mygdx.game.utils.GameData;

public class TitleScreen extends Screens{

    private Stage stage;
    Table table;

    private int screenWidth;
    private int screenHeight;
    OrthographicCamera camera;
    Skin mySkin;
    Texture background;
    StretchViewport viewport;
    LabelWidget mainLabel;
    TextButtonWidget playButton;
    TextButtonWidget settingsButton;
    GameData gameData;

    public TitleScreen(){
        super();
    }

    @Override
    public void buildStage() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        gameData = new GameData();
//        gameData.setScore(0);
//        gameData.setHealth(100);
//        gameData.setTime(160);

        camera = new OrthographicCamera();

        viewport = new StretchViewport(screenWidth, screenHeight, camera);
        mySkin = ResourceManager.getInstance().getSkin("flat-earth");
        camera.setToOrtho(false, screenWidth, screenHeight);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        mainLabel = new LabelWidget("Space Quest!", mySkin,"title");
        settingsButton = new TextButtonWidget("SETTINGS", mySkin);
        playButton = new TextButtonWidget("PLAY", mySkin);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameData.getInstance().setHealth(100);
                GameData.getInstance().setScore(0);
                GameData.getInstance().setTime(180);
                ScreenManager.getInstance().displayScreen(GameStateScreens.GAME);

            }
        });

        settingsButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                ScreenManager.getInstance().displayScreen(GameStateScreens.SETTINGS);
            }
        });

        table = new Table(mySkin);
        table.setFillParent(true);
        stage.addActor(table);
        table.top();
        table.setDebug(false);
        table.add(mainLabel).center().expandX().padTop(100);
        table.row();
        table.add(playButton).center().padTop(300);
        table.row();
        table.add(settingsButton).center().padTop(30);
        SoundManager.getInstance().playMusic();
        SoundManager.getInstance().setLooping(true);
    }

    @Override
    public void resize(int screenWidth, int screenHeight) {
        stage.getViewport().update(screenWidth, screenHeight);
        camera.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }
}
