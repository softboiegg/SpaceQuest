package com.mygdx.game.Screens;

import com.mygdx.game.utils.GameData;

public enum GameStateScreens {
    TITLE {
        public Screens getScreen(Object... params) {
            return new TitleScreen();
        }
    },
    SETTINGS {
        public Screens getScreen(Object... params) {
            return new SettingScreen();
        }
    },
    //    SCOREBOARD,
    GAME {
        public Screens getScreen(Object... params) {
            return new GameScreen();
        }
    },
    LOAD{
        public Screens getScreen(Object ... params) {
            return new LoadingScreen();
        }},
    QUIZ {
        public Screens getScreen(Object... params) {
            return new BonusScreen();
        }
    },
    GAME_OVER {
        public Screens getScreen(Object... params) {
            return new GameOverScreen((boolean) params[0]);
        }

        ;
    };
//    CREDITS;

    public abstract Screens getScreen(Object... params);
}
