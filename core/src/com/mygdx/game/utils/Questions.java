package com.mygdx.game.utils;

import java.util.HashMap;

public class Questions {

    private String question;
    private Boolean answer;

    public Questions(String question, Boolean answer){
        this.question = question;
        this.answer = answer;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    //    private HashMap<String, Boolean> questionMap;
//
//    public Questions(){
//
//    }
//    public Questions(HashMap<String, Boolean> questionMap) {
//        this.questionMap = questionMap;
//    }
//
//    public void createQuestionMap(Object ...params){
//        for (int i = 0; i < params.length ; i+=2) {
//            this.questionMap.put((String) params[i], (Boolean) params[i+1]);
//        }
//    }
//
//    public HashMap<String, Boolean> getQuestionMap() {
//        return questionMap;
//    }
//
//    public HashMap<String, Boolean> getOneQuestion(){
//        return questionMap;
//    }
}
