package com.example.techmemoryjog;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {
    private Button btn1,btn2, btn3,btn4, btn_next;
    private TextView timerTextView;
    private TextView textViewQuestionNo;
    private int original_size = -1;

    public final static String COURSE_ID = "com.example.techmemoryjog.COURSE_ID";
    public final static int COURSE_ID_INT = -1;
    public int mCourseId;

    public int questionNumber = 1;
    public int currentScore = 0;
    public int totalScore = 0;
    public int questionMarks;
    public float finalPercentage = 0;
    public HashMap<String, String> mHashMap;//Hashmap of choices
    private int mTime;//time taken to answer the question
    private List<Question> questionsList;//List of question
    private  ArrayList<String>topics_arr= new ArrayList<>();

    private Boolean mChoicesDisabled;
    private String topic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //get the extra
        Intent intent = getIntent();
        mCourseId = intent.getIntExtra(COURSE_ID, COURSE_ID_INT);
        loadQuestion();
        buttonHandler();//Changes the button color and text

    }

    private void loadQuestion() {
        questionsList = Manager.getInstance().getQuestions(mCourseId);
//        setProgressbar();

        TextView textView = findViewById(R.id.text_question);
        for(Question question: questionsList){
            mChoicesDisabled = false;
            textView.setText(question.getmQuestion());//get questions
            setChoices(question);//setChoices
            setTimer(question);//Set timer
            questionMarks = getMarks(question);//get marks
            topic = question.getTopic();//get topics

            break;
        }
    }

    private void setProgressbar() {
        if(original_size == -1){
            original_size = questionsList.size();
        }
        //update the progress bar
        int progressValue = (original_size - questionsList.size())/ original_size *100;
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(progressValue);
    }

    private int getMarks(Question question) {
        return question.getMarks();
    }

    private void setTimer(Question question) {
        mTime = question.getTime();
        timerTextView = findViewById(R.id.text_timer);
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String timer = Integer.toString(mTime);
                    timerTextView.setText(timer);
                    mTime--;
                    if(mTime > 0 && !mChoicesDisabled){
                        handler.postDelayed(this, 1000);
                    }
                    else{
                        handler.removeCallbacks(this);
                        mChoicesDisabled = true;
                        checkAnswer();
                    }

                }
            });
//            //Show the correct answer
//        }



        
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                timerTextView.setText(mTime);
//                mTime--;
//            }
//        };
//        final Timer timer = new Timer();
//        if(mTime > 0 && !mDisable){
//            timer.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    timerTextView.setText(mTime);
//                    mTime--;
//                }
//            }, 0, 1000);
//        }
//        else if(mTime <= 0){
//            mDisable = true;
//            checkAnswer();
//            timer.cancel();
//            //Show the correct answer
//        }
    }

    //Setting the choice in the text view
    private void setChoices(Question question) {
        mHashMap = question.getChoices();
        String[] choices = new String[4];
        int i = 0;
        for(Map.Entry mapElement: mHashMap.entrySet()){
            String key = mapElement.getKey().toString();
            choices[i] = key;
            i++;
        }
        btn1 = findViewById(R.id.btn_choice1);
        btn1.setText(choices[0]);
        btn2 = findViewById(R.id.btn_choice2);
        btn2.setText(choices[1]);
        btn3 = findViewById(R.id.btn_choice3);
        btn3.setText(choices[2]);
        btn4 = findViewById(R.id.btn_choice4);
        btn4.setText(choices[3]);
    }
    //Click next or finish
    private void buttonHandler() {
        btn_next = findViewById(R.id.btn_next_finish);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setProgressbar();
                //Add questionMarks to totalScore reset questionMarks to zero
                totalScore = totalScore + questionMarks;
                questionMarks = 0;
                //Reset topics to null
                topic = null;
//                if(questionsList.size() == 0){
                if( btn_next.getText().toString().equalsIgnoreCase("Finish")){
                    Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
//                    intent.putExtra(ScoreActivity.PERCENTAGE_SCORE,finalPercentage);
                    intent.putExtra(ScoreActivity.PERCENTAGE_SCORE, calculatePercentage());
                    intent.putStringArrayListExtra(ScoreActivity.TOPICS_LIST, topics_arr);
                    startActivity(intent);
                }
                else{
//                    restore original color of button
                    btn1.setBackgroundColor(Color.parseColor("#c5cae9"));
                    btn2.setBackgroundColor(Color.parseColor("#c5cae9"));
                    btn3.setBackgroundColor(Color.parseColor("#c5cae9"));
                    btn4.setBackgroundColor(Color.parseColor("#c5cae9"));
                    questionsList.remove(0);
                    if((questionsList.size()-1) == 0){
                        btn_next.setText("Finish");
                    }
                    //Change question number
                    questionNumber++;
                    textViewQuestionNo = findViewById(R.id.text_question_number);
                    String questionNo = Integer.toString(questionNumber);
                    textViewQuestionNo.setText(questionNo);

                    loadQuestion();//Load next question

                }
            }
        });
    }

    private float calculatePercentage() {
        finalPercentage = (float)(currentScore * 100 / totalScore);
        return finalPercentage;
    }

    //When user has made a choice by clicking a button
    public void btnClick(View view) {

        if(!mChoicesDisabled){
            mChoicesDisabled = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                view.setOutlineAmbientShadowColor(Color.parseColor("#ff4081"));
            }
            //delay
            try{
                Thread.sleep(1200);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            switch (view.getId()){
                case R.id.btn_choice1:
                    if(checkAnswer(btn1.getText().toString())){
                        //color green

                        btn1.setBackgroundColor(Color.parseColor("#8BC34A"));
                    }
                    else{
                        //color red
                        btn1.setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    //Stop timer
//                getOtherAnswers();
                    break;
                case R.id.btn_choice2:
                    if(checkAnswer(btn2.getText().toString())){
                        btn2.setBackgroundColor(Color.parseColor("#8BC34A"));
                    }
                    else{
                        //color red
                        btn2.setBackgroundColor(Color.parseColor("#F44336"));
                    }
//                     mChoicesDisabled = true;
                    break;
                case R.id.btn_choice3:
                    if(checkAnswer(btn3.getText().toString())){
                        btn3.setBackgroundColor(Color.parseColor("#8BC34A"));
                    }
                    else{
                        //color red
                        btn3.setBackgroundColor(Color.parseColor("#F44336"));
                    }
//                     mChoicesDisabled = true;
                    break;
                case R.id.btn_choice4:
                    if(checkAnswer(btn4.getText().toString())){
                        btn4.setBackgroundColor(Color.parseColor("#8BC34A"));
                    }
                    else{
                        //color red
                        btn4.setBackgroundColor(Color.parseColor("#F44336"));
                    }
//                     mChoicesDisabled = true;
                    break;
                default:
                    break;

            }
        }
    }
    /* Check if answer is correct */
    private boolean checkAnswer(String question) {
        if(mHashMap.get(question).equalsIgnoreCase("true")) {
            currentScore = questionMarks + currentScore;
            return true;
        }
        topics_arr.add(topic);
//        Check for duplicates
//        Iterator iterator = topics_arr.iterator();
//        while(iterator.hasNext()){
//            if(!topic.equalsIgnoreCase(iterator.next().toString())){//If no such topic is in the list
//                topics_arr.add(topic);
//            }
//        }
        return false;
    }
    /*Find the correct answer*/
    private void checkAnswer(){
       for(Map.Entry mapElement: mHashMap.entrySet()){
           String key = (String) mapElement.getKey();
           String value = (String) mapElement.getValue();
           if(value.equalsIgnoreCase("true")){
               //Color the box
               if(btn1.getText().equals(key)){
                   btn1.setBackgroundColor(Color.parseColor("#8BC34A"));
               }
               else if(btn2.getText().equals(key)){
                   btn2.setBackgroundColor(Color.parseColor("#8BC34A"));
               }
               else if(btn3.getText().equals(key)){
                   btn3.setBackgroundColor(Color.parseColor("#8BC34A"));
               }
               else if(btn4.getText().equals(key)){
                   btn4.setBackgroundColor(Color.parseColor("#8BC34A"));
               }
           }
        }
    }

}
