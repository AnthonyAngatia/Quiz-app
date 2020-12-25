package com.example.techmemoryjog;

import java.util.HashMap;

class Question {
    private String mQuestion;
    private HashMap<String, String> choices;
    private String topic;
    private int marks;
    private int time;

    public Question(String question, HashMap<String, String> choices, String topic, int marks, int time) {
        this.mQuestion = question;
        this.choices = choices;
        this.topic = topic;
        this.marks = marks;
        this.time = time;
    }

    public HashMap<String, String> getChoices() {
        return choices;
    }

    public void setChoices(HashMap<String, String> choices) {
        this.choices = choices;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
