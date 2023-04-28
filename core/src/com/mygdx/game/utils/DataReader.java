package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class DataReader {
    private FileHandle file;
    JsonReader jsonReader;
    JsonValue jsonValue;


    public DataReader(){
    }

    public FileHandle getFile() {
        return file;
    }

    public void setFile(FileHandle file) {
        this.file = file;
    }

    public boolean fileChecker(String file){
        return Gdx.files.external(file).exists();
    }

    public void read(FileHandle file){
        jsonValue = new JsonReader().parse(file);
    }
}
