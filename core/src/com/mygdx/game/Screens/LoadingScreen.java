package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.mygdx.game.utils.QuestionMap;
import com.mygdx.game.utils.Questions;
import com.mygdx.game.utils.ResourceManager;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadingScreen extends Screens {

    private Stage stage;
    private Table table;
    private int width;
    private int height;
    OrthographicCamera camera;
    Skin mySkin;
    Texture background;
    StretchViewport viewport;
    LabelWidget mainLabel;
    LabelWidget hintLabel;
    GameData player;
    QuestionMap questionMap;
    boolean answer;
    HashMap<Integer, Questions> questionsHashMap;
    ArrayList<Questions> questionsArrayList;

    public LoadingScreen(){
        super();
    }

    @Override
    public void buildStage() {
        player = new GameData();
        player.setScore(0);

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        questionsArrayList = new ArrayList<>();
        questionsHashMap = new HashMap<>();
        Questions q1 = new Questions("Mars is the fourth planet from the sun", true);
        Questions q2 = new Questions("Mars has water on its surface", true);
        Questions q3 = new Questions("Mars does not have a similar temperature to Earth", false);
        Questions q4 = new Questions("Mars has seasons like Earth", true);
        Questions q5 = new Questions("Mars does not have the same number of moons as Earth", false);
        Questions q6 = new Questions("Mars has a nickname called the Red Planet", true);
        Questions q7 = new Questions("Mars is the only planet in our solar system that humans have sent rovers to explore", true);
        Questions q8 = new Questions("Mars has the largest volcano in the solar system", true);
        Questions q9 = new Questions("The atmosphere on Mars is not similar to that on Earth", false);
        Questions q10 = new Questions("Humans cannot breathe the air on Mars without special equipment", false);
        Questions q11 = new Questions("The tallest mountain in the solar system is on Mars", true);
        Questions q12 = new Questions("Mars has the longest canyon in the solar system", true);
        Questions q13 = new Questions("Mars is smaller than Earth", true);
        Questions q14 = new Questions("It is not easy for humans to travel to Mars", false);
        Questions q15 = new Questions("Mars is not a gas giant planet like Jupiter and Saturn", false);


        questionsArrayList.add(q1);
        questionsArrayList.add(q2);
        questionsArrayList.add(q3);
        questionsArrayList.add(q4);
        questionsArrayList.add(q5);
        questionsArrayList.add(q6);
        questionsArrayList.add(q7);
        questionsArrayList.add(q8);
        questionsArrayList.add(q9);
        questionsArrayList.add(q10);
        questionsArrayList.add(q11);
        questionsArrayList.add(q12);
        questionsArrayList.add(q13);
        questionsArrayList.add(q14);
        questionsArrayList.add(q15);



        questionMap = new QuestionMap(questionsHashMap);
        questionMap.setQuestions(questionsArrayList);


        camera = new OrthographicCamera();
        viewport = new StretchViewport(width, height, camera);
        mySkin = ResourceManager.getInstance().getSkin("flat-earth");
        camera.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        mainLabel = new LabelWidget("Loading...", mySkin,"title");
        hintLabel = new LabelWidget("Loading!", mySkin,"title");

        setQnA();


        table = new Table(mySkin);
        table.setFillParent(true);
        stage.addActor(table);
        mainLabel.setWrap(true);
        mainLabel.setAlignment(Align.bottom);
        table.setDebug(false);

        table.add(mainLabel).center().width(500);
        table.row();

        Table buttonsTable = new Table(); // create a new table to hold the buttons
        buttonsTable.add(hintLabel).center();
        table.add(buttonsTable).center().padTop(30);

    }

    // NEEDS  TO BE REPLACED BY A TIMER/////
    public void gotoGame(){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            System.out.println("!!!");
            ScreenManager.getInstance().displayScreen(GameStateScreens.GAME, player);
        }
    }

    public void setQnA(){
        Questions question = questionMap.getRandomQuestion();
        answer = question.getAnswer();
        hintLabel.setText(question.getQuestion());
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        gotoGame();
        stage.draw();

    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }
}

