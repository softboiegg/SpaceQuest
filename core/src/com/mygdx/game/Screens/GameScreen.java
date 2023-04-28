package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Entities.*;
import com.mygdx.game.Entities.Generator.EnemyShipGenerator;
import com.mygdx.game.Entities.Generator.MovingStarGenerator;
import com.mygdx.game.SpaceQuest;
import com.mygdx.game.UI.LabelWidget;
import com.mygdx.game.utils.EntityManager;
import com.mygdx.game.utils.GameData;
import com.mygdx.game.utils.InputManager;
import com.mygdx.game.utils.ResourceManager;

import javax.swing.text.html.parser.Entity;

public class GameScreen extends Screens{
    private Stage stage;
    private Table table;
    OrthographicCamera camera;
    private int screenWidth;
    private int screenHeight;
    FitViewport viewport;
    Skin mySkin;
    HUD hud;
    LabelWidget mainLabel;
    private SpriteBatch spriteBatch;
    Explosion explosion;
    PlayerShip playerShip;
    EnemyShip enemyShip;
    EnemyShipGenerator enemyShipGenerator;
    MovingStarGenerator movingStarGenerator;
    InputManager inputManager;
    GameScreen gameScreen;


    public GameScreen() {
        super();
        this.gameScreen = this;
//        playerShip = EntityManager.getInstance().getPlayerShipPool().obtain(0,0);
        playerShip = new PlayerShip(0,0);
        EntityManager.getInstance().setPlayerShip(playerShip);
        inputManager = new InputManager(playerShip, Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D);

        movingStarGenerator = new MovingStarGenerator(50);
//        explosion = new Explosion(new Vector2(100,100));
//        playerShip = new PlayerShip(0,0, inputManager);
        enemyShipGenerator = new EnemyShipGenerator(2, 3);
    }

    @Override
    public void buildStage() {
        spriteBatch = new SpriteBatch();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        hud = new HUD(180, spriteBatch);
        camera = new OrthographicCamera();

        viewport = new FitViewport(screenWidth, screenHeight, camera);
        mySkin = ResourceManager.getInstance().getSkin("flat-earth");
        camera.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);


        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
//        table.setDebug(false);
        table.add(mainLabel).center();

    }

    public void quit(){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            ScreenManager.getInstance().displayScreen(GameStateScreens.TITLE);
        }
    }

    public void testQuiz(){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            Gdx.graphics.setContinuousRendering(false);
            Gdx.graphics.requestRendering();
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        hud.getStage().getViewport().update(width, height);

    }

    public void gameOver(){
        if (hud.getTimer() == 0){
            ScreenManager.getInstance().displayScreen(GameStateScreens.GAME_OVER, true);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        hud.updateTimer(delta);
        stage.act(Gdx.graphics.getDeltaTime());
        spriteBatch.setProjectionMatrix(camera.combined); //set the spriteBatch to draw what our playerViewport sees
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(hud.getStage().getCamera().combined);
        EntityManager.getInstance().draw(spriteBatch);

        if(hud.getTimer() % 30 == 0 && hud.getTimer() != 180 && hud.getTimer() != 0){
            ScreenManager.getInstance().displayScreen(GameStateScreens.QUIZ);
            GameData.getInstance().setTime(hud.getTimer() - 1 );
        }

        if (playerShip.isDestroyed()) {
            ScreenManager.getInstance().displayScreen(GameStateScreens.GAME_OVER, false);
        }


        //        explosion.draw(batch, 1.0f);
//        explosion.draw(spriteBatch, 1.0f);


        spriteBatch.end();
        hud.update();
        hud.getStage().act(delta);
        hud.getStage().draw();
        gameOver();
        stage.draw();
        quit();
    }

    public void update(float delta) {
        camera.update();
        inputManager.update();

        EntityManager.getInstance().update(delta);

        enemyShipGenerator.update(delta);

    }


    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        hud.dispose();
        spriteBatch.dispose();
    }
}
