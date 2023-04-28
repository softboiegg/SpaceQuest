package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.GameStateScreens;
import com.mygdx.game.Screens.ScreenManager;
import com.mygdx.game.utils.InputManager;
import com.mygdx.game.utils.PreferenceManager;

public class SpaceQuest extends Game {

	@Override
	public void create () {
		PreferenceManager.getInstance().initialize("Game");
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().displayScreen(GameStateScreens.TITLE);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}

}
