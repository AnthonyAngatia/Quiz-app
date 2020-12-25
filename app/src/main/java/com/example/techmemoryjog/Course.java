package com.example.techmemoryjog;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String mCourseName;
    private int mId;
    private List<Question> mQuestions = new ArrayList<>();

    public Course(String name, int id, List<Question> mQuestions) {
        this.mCourseName = name;
        this.mId = id;
        this.mQuestions = mQuestions;
    }

    public List<Question> getmQuestions() {
        return mQuestions;
    }

    public void setmQuestions(List<Question> mQuestions) {
        this.mQuestions = mQuestions;
    }
}
