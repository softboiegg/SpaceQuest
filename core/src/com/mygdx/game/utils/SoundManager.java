package com.mygdx.game.utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundManager {

    private static SoundManager instance;
    Music bgmusic;

    public SoundManager() {
        bgmusic = ResourceManager.getInstance().getMusic("bg");
    }

    public void playMusic(){
        bgmusic.play();
    }
    public void pauseMusic(){
        bgmusic.pause();
    }
    public void setVolume(float value){
        bgmusic.setVolume(value);
    }
    public float getVolume() {return bgmusic.getVolume();}
    public boolean isPlaying(){
        return bgmusic.isPlaying();
    }
    public void setLooping(boolean loop){
        bgmusic.setLooping(loop);
    }

    public static SoundManager getInstance() {
        if (instance == null){
            instance = new SoundManager();
        }
        return instance;
}
}
