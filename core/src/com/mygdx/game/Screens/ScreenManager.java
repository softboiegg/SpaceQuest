package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.SpaceQuest;

public class ScreenManager {
    private static ScreenManager instance;
    private SpaceQuest LQ;

    public static ScreenManager getInstance() {
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }

    public void initialize(SpaceQuest LQ){
        this.LQ = LQ;
    }

    public void displayScreen(GameStateScreens GameState, Object... params){
        Screen currentScreen = LQ.getScreen();
        Screens switchScreen = GameState.getScreen(params);

        switchScreen.buildStage();
        LQ.setScreen(switchScreen);

        if(currentScreen != null){
            currentScreen.dispose();
        }

    }
}
