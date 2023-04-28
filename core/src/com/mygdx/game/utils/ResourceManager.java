package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


import java.util.ArrayList;
import java.util.HashMap;

public class ResourceManager {
    private static ResourceManager instance;
    private HashMap<String, Texture> textures;
    private HashMap<String, TiledMap> maps;
    private HashMap<String, Sound> sounds;
    private HashMap<String, BitmapFont> fonts;
    private HashMap<String, Music> music;
    private HashMap<String, Skin> skins;

    public ResourceManager() {
        textures = new HashMap<String, Texture>();
        maps = new HashMap<String, TiledMap>();
        sounds = new HashMap<String, Sound>();
        fonts = new HashMap<String, BitmapFont>();
        music = new HashMap<String, Music>();
        skins = new HashMap<String, Skin>();


        loadResources();
    }

    public static ResourceManager getInstance() {
        if (instance == null){
            instance = new ResourceManager();
        }
        return instance;
    }

    public void loadResources() {

        try {
            // Textures
            textures.put("star", new Texture("star_white02_resized.png"));
            textures.put("player", new Texture("ships/playerShip_resized.png"));
            textures.put("enemy1", new Texture("ships/enemy1_resized.png"));
            textures.put("playerBullet", new Texture("bullets/playerBullet_resized1.png"));
            textures.put("enemyBullet", new Texture("bullets/enemy1Bullet_resized1.png"));
            textures.put("explosion", new Texture("explosion.png"));

            // Music
            music.put("bg", Gdx.audio.newMusic(Gdx.files.internal("bgmusic.ogg")));

            // Skin
            skins.put("flat-earth", new Skin(Gdx.files.internal("skin/flat-earth-ui.json")));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }
    

    public Texture getTexture(String name) {
        return textures.get(name);
    }

    public TiledMap getMap(String name) {
        return maps.get(name);
    }

    public Sound getSound(String name) {
        return sounds.get(name);
    }

    public BitmapFont getFont(String name) {
        return fonts.get(name);
    }

    public Music getMusic(String name) {
        return music.get(name);
    }

    public Skin getSkin(String name) {
        return  skins.get(name);
    }

    public void dispose() {

        for (Texture texture : textures.values()) {
            texture.dispose();
        }

        for (TiledMap map : maps.values()) {
            map.dispose();
        }

        for (Sound sound : sounds.values()) {
            sound.dispose();
        }

        for (Music music : music.values()) {
            music.dispose();
        }

        for (BitmapFont font : fonts.values()) {
            font.dispose();
        }

        for (Skin skin : skins.values()) {
            skin.dispose();
        }
    }
}

