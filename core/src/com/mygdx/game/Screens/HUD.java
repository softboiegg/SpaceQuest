package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.UI.LabelWidget;
import com.mygdx.game.utils.GameData;
import com.mygdx.game.utils.ResourceManager;


public class HUD {
    private Stage stage;
    Table topTable;
    Table middleTable;
    int width;
    int height;
    OrthographicCamera camera;
    Skin mySkin;
    FitViewport viewport;
    private int timer;
    private SpriteBatch spriteBatch;
    LabelWidget scoreLabel;
    LabelWidget timerLabel;
    LabelWidget userScore;
    LabelWidget userTime;
    LabelWidget healthLabel;
    LabelWidget userHealth;
    float worldTimer;


    public HUD(int timer,  SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
        this.timer = GameData.getInstance().getTime();
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();


        camera = new OrthographicCamera(width, height);

        mySkin = ResourceManager.getInstance().getSkin("flat-earth");

        viewport = new FitViewport(width, height);
        camera.setToOrtho(false, width, height);
        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);
        scoreLabel = new LabelWidget("Score", mySkin, "title");
        timerLabel = new LabelWidget("Time", mySkin, "title");
        healthLabel = new LabelWidget("Health",mySkin, "title");
        userScore = new LabelWidget(String.format("%03d", GameData.getInstance().getScore()), mySkin, "title");
        userScore = new LabelWidget(String.format("%03d", GameData.getInstance().getScore()), mySkin, "title");
        userHealth = new LabelWidget(String.format("%03d", GameData.getInstance().getHealth()),mySkin, "title");
        userTime = new LabelWidget(String.format("%03d", GameData.getInstance().getTime()), mySkin, "title");


        scoreLabel.setColor(1,1,1,0.7f);
        userScore.setColor(1,1,1,0.7f);
        timerLabel.setColor(1,1,1,0.7f);
        userTime.setColor(1,1,1,0.7f);
        userHealth.setColor(1,1,1,0.7f);
        healthLabel.setColor(1,1,1,0.7f);

        scoreLabel.setAlignment(Align.center);
        userScore.setAlignment(Align.center);
        timerLabel.setAlignment(Align.center);
        userTime.setAlignment(Align.center);
        userHealth.setAlignment(Align.center);
        healthLabel.setAlignment(Align.center);

        topTable = new Table(mySkin);
        topTable.top();
        topTable.setFillParent(true);

        topTable.setDebug(false);

        stage.addActor(topTable);
        topTable.add(scoreLabel).center().padTop(40).padRight(40).expandX();
        topTable.add(healthLabel).center().padTop(40).expandX();
        topTable.add(timerLabel).center().padTop(40).padLeft(40).expandX();
        topTable.row();
        topTable.add(userScore).padTop(10).padRight(40).expandX();
        topTable.add(userHealth).padTop(10).expandX();
        topTable.add(userTime).padTop(10).padLeft(40).expandX();

    }

    public void dispose() {
        stage.dispose();
    }

    public int getTimer() {
        return timer;

    }

    public void updateTimer(float delta) {
        worldTimer += delta;
        if (worldTimer >= 1 && timer != 0) {
            timer--;
            userTime.setText(String.format("%03d", timer));
            GameData.getInstance().addScore(100);
            GameData.getInstance().setTime(timer);
            worldTimer = 0;
        }
    }

    public void updateScore() {
        userScore.setText(String.format("%03d", GameData.getInstance().getScore()));
    }

    public void updateHealth() {
        System.out.println("Health: " + GameData.getInstance().getHealth());
        userHealth.setText(String.format("%03d", GameData.getInstance().getHealth()));
    }

    public void update(){
        updateScore();
        updateHealth();
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public Stage getStage() {
        return stage;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public void setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }
}
