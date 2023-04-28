package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferenceManager {
    private Preferences preferences;
    private static PreferenceManager instance;
    private String stringValue;
    private int intValue;

    PreferenceManager(){}
    public static PreferenceManager getInstance(){
        if (instance ==null) {
            instance = new PreferenceManager();
        }
        return instance;
    }

    public void initialize(String title){
        this.preferences = Gdx.app.getPreferences(title);
    }

    public void saveString(String key, String value){
        preferences.putString(key, value);
        preferences.flush();
    }

    public String getString(String key, String value){
        this.stringValue = preferences.getString(key, value);
        return stringValue;
    }

    public void saveInt(String key, int value){
        preferences.putInteger(key, value);
        preferences.flush();
    }

    public int getInt(String key){
        this.intValue = preferences.getInteger(key);
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public int getIntValue() {
        return intValue;
    }
}
