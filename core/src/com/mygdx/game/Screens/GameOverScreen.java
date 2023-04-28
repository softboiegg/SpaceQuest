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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.UI.LabelWidget;
import com.mygdx.game.UI.TextButtonWidget;
import com.mygdx.game.utils.GameData;
import com.mygdx.game.utils.PreferenceManager;
import com.mygdx.game.utils.ResourceManager;

public class GameOverScreen extends Screens{
    private boolean winCondition;
    private Stage stage;
    private int screenWidth;
    private int screenHeight;
    private Table table;
    private OrthographicCamera camera;
    Skin mySkin;
    StretchViewport viewport;
    LabelWidget mainLabel;
    LabelWidget winConditionLabel;
    LabelWidget highScoreAchievedLabel;
    LabelWidget scoreLabel;
    int highScore;

    TextButtonWidget backButton;

    public GameOverScreen(boolean winCondition){
        super();
        this.winCondition = winCondition;
    }
    @Override
    public void buildStage() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();

        viewport = new StretchViewport(screenWidth, screenHeight, camera);
        mySkin = ResourceManager.getInstance().getSkin("flat-earth");
        camera.setToOrtho(false, screenWidth, screenHeight);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        mainLabel = new LabelWidget("Level Lost", mySkin,"title");
        scoreLabel = new LabelWidget(String.format("Score: %03d", GameData.getInstance().getScore()), mySkin, "title");
        winConditionLabel = new LabelWidget("You Lost UwU", mySkin, "title");
        highScoreAchievedLabel = new LabelWidget("Ding Ding Ding Highscore achieved", mySkin, "title");

        highScoreAchievedLabel.setWrap(true);

        backButton = new TextButtonWidget("Back to Main Menu", mySkin);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                ScreenManager.getInstance().displayScreen(GameStateScreens.TITLE);
            }
        });

        highScoreAchievedLabel.setAlignment(Align.center);
        highScoreAchievedLabel.setVisible(false);

        table = new Table(mySkin);
        table.setFillParent(true);
        stage.addActor(table);
        table.top();
        table.setDebug(false);
        table.add(mainLabel).center().expandX().padTop(100);
        table.row();
        table.add(scoreLabel).center().expandX().padTop(100);
        table.row();
        table.add(winConditionLabel).center().expandX().padTop(20);
        table.row();
        table.add(highScoreAchievedLabel).center().width(500).padTop(30);

        table.row();
        table.add(backButton).center().padTop(100);

        setWinCondition(winCondition);
        saveHighscore();

    }
    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render(delta);


        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void saveHighscore(){
        highScore = PreferenceManager.getInstance().getInt("highScore");
        System.out.println("Highscore: " + highScore);
        if(GameData.getInstance().getScore() > highScore){
            PreferenceManager.getInstance().saveInt("highScore", GameData.getInstance().getScore());
            highScoreAchievedLabel.setVisible(true);
        }
    }

    public void setWinCondition(boolean winCondition){
        if(winCondition){
            mainLabel.setText("Level Completed!");
            winConditionLabel.setText("You Won");
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
