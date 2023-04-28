package com.mygdx.game.utils;

public class GameData {
    private String playerName;
    private static GameData instance;
    private int health = 100;
    private int score;
    private int time;
    private float playerFireRate = 1;

    public GameData(){

    }

    public static GameData getInstance(){
        if(instance == null){
            instance = new GameData();
        }
        return instance;
    }

    public String getPlayerName(){
        return  this.playerName;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getTime(){
        return this.time;
    }

    public void setTime(int time){
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getPlayerFireRate() {
        return playerFireRate;
    }

    public void setPlayerFireRate(float playerFireRate) {
        this.playerFireRate = playerFireRate;
    }

    public void addScore(int addScore){
        System.out.println("Score: "+score);
        System.out.println("Add Score: "+score);
        this.score = score + addScore;
    }
    public void addHealth(int addHealth){
        this.health = health + addHealth;
    }

    public void addFireRate(float fireRate){
        this.playerFireRate = playerFireRate + fireRate;
    }

}
