package com.example.techmemoryjog;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class ScoreActivity extends AppCompatActivity {

    public final static String PERCENTAGE_SCORE = "com.example.techmemoryjog_PERCENTAGE_SCORE";
    public static final String TOPICS_LIST = "com.example.techmemoryjog_TOPICS_LIST";
    public float mPercentage = 0;
    private ArrayList<String> mTopics;
    private StringBuilder mTopicsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        //GET EXTRAS
        Intent intent = getIntent();
        mPercentage =  intent.getFloatExtra(PERCENTAGE_SCORE, mPercentage);
        setPercentage();
        //Display topcs and percentage
        TextView textView = findViewById(R.id.text_topics);
        mTopics = intent.getStringArrayListExtra(TOPICS_LIST);
        if(mTopics.size() == 0){
            textView.setText("You've got no topic to review. You are a master for now!!");
        }
        int i = 0;
        mTopicsText = new StringBuilder();
        while(i< mTopics.size()){
//            topicsText = topicsText+"\n"+mTopics.get(i);
            mTopicsText.append(mTopics.get(i)).append("\n");
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
//                textView.setText(Html.fromHtml("<li><ol>"+ mTopics.get(i)+"<ol></li>", Html.FROM_HTML_MODE_COMPACT));
//            }
//            else{
//                textView.setText(Html.fromHtml("<li><ol>"+ mTopics.get(i)+"<ol></li>"));
//            }
            textView.setText(mTopicsText);

            i++;
        }

    }

    private void setPercentage() {
        TextView textView = findViewById(R.id.text_perctage);
        String percentage = Float.toString(mPercentage)+"%";
        textView.setText(percentage);
    }


    public void sendMail(View view) {
        //Send the percentage
        //Topics to review
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc2822");
        intent.putExtra(Intent.EXTRA_SUBJECT, "TMJ Assessment");
        intent.putExtra(Intent.EXTRA_TEXT, mPercentage);
        String emailText = "Your score is "+mPercentage+"%"+"\n"+"Please follow up on the following topics"+"\n"+mTopicsText;
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        startActivity(intent);
    }
}
