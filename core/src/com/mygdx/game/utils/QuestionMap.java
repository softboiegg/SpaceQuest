package com.mygdx.game.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class QuestionMap {
    private HashMap<Integer, Questions> questionsHashMap;
    private ArrayList<Integer> questionsAsked;
    private ArrayList<Questions> questionsList;
    private final Random randomNumberGenerator;
    public QuestionMap(HashMap<Integer, Questions> questionsHashMap){
        this.questionsHashMap = questionsHashMap;
        randomNumberGenerator = new Random();
        this.questionsAsked = new ArrayList<>();
        this.questionsList = new ArrayList<>();
    }

    public void setQuestions(ArrayList<Questions> questionsArrayList){
        for (int i = 0; i < questionsArrayList.size() ; i++) {
            questionsHashMap.put(i, questionsArrayList.get(i));
        }
    }

    public void setQuestionsHashMap(HashMap<Integer, Questions> questionsHashMap) {
        this.questionsHashMap = questionsHashMap;
    }

    public HashMap<Integer, Questions> getQuestionsHashMap() {
        return questionsHashMap;
    }

    public Questions getRandomQuestion(){
        int questionNum = getRandomInt();
        return questionsHashMap.get(questionNum);
    }

    public Integer getRandomInt(){
        return randomNumberGenerator.nextInt(questionsHashMap.size());
    }
}
