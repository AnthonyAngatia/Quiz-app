package com.example.techmemoryjog;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppBarConfiguration mAppBarConfiguration;
//    TextView textView, textView2;
    ConstraintLayout mConstraintLayout;
    Manager mng = new Manager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        mConstraintLayout = findViewById(R.id.constraint_layout);

        //Set Image for the cards
        setImageForTheCards();

    }

    private void setImageForTheCards() {
        TextView textView;
        textView = findViewById(R.id.card_textView1);
        Drawable drawable = getResources().getDrawable(R.drawable.angular);
        drawable.setBounds(0, 0, 165, 165);
        textView.setCompoundDrawables(null, drawable, null, null);
//        materialCardView = findViewById(R.id.card_1);
        textView = findViewById(R.id.card_textView2);
        drawable = getResources().getDrawable(R.drawable.android);
        drawable.setBounds(0, 0, 165, 165);
        textView.setCompoundDrawables(null, drawable, null, null);
        textView = findViewById(R.id.card_textView3);
        drawable = getResources().getDrawable(R.drawable.typescript);
        drawable.setBounds(0, 0, 165, 165);
        textView.setCompoundDrawables(null, drawable, null, null);
        textView = findViewById(R.id.card_textView4);
        drawable = getResources().getDrawable(R.drawable.java);
        drawable.setBounds(0, 0, 165, 165);
        textView.setCompoundDrawables(null, drawable, null, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void textViewClick(View view) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        switch (view.getId()) {
            case R.id.card_textView1:
                intent.putExtra(QuizActivity.COURSE_ID, 0);
                startActivity(intent);
                break;
            case R.id.card_textView2:
                intent.putExtra(QuizActivity.COURSE_ID, 1);
                startActivity(intent);
                break;
            case R.id.card_textView3:
                intent.putExtra(QuizActivity.COURSE_ID, 2);
                startActivity(intent);
                break;
            case R.id.card_textView4:
                intent.putExtra(QuizActivity.COURSE_ID, 3);
                startActivity(intent);
                break;
            default:

                break;

        }


    }

    @Override
    public void onClick(View view) {
        Log.d("tag", "Amcalled");


    }
    private void getWidgets() {
        List<Course> courses = mng.getInstance().getmCourses();
        courses.size();
        TextView textView;
        for(Course course:courses){
            //Make a card
            MaterialCardView mcv= getCardMaterialCard();
            //Create layout
//            ConstraintLayout constraintLayoutChild = findViewById(R.id.child_constraint);
            //Create Textview wih image
            TextView txtV = getTextView();
            //Getting the layout from activity main

            mcv.addView(txtV);
            mConstraintLayout.addView(mcv);
        }

    }

    private TextView getTextView() {
        TextView textViewx =new TextView(this);
        textViewx.setId(R.id.text_view_1);
        textViewx.setText("TypeScript");
        textViewx.setTextColor(Color.parseColor("#212121"));
        textViewx.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewx.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        int pix = (int)convertDptoPx(16);
        textViewx.setPadding(pix,pix,pix,pix);
//
        Drawable drawable = getResources().getDrawable(R.drawable.typescript);
        drawable.setBounds(0, 0, 220, 220);
        textViewx.setCompoundDrawables(null, drawable, null, null);
        pix = (int)convertDptoPx(10);
//        textViewx.setCompoundDrawablePadding((int)pix);
//        textViewx.setTag();
        return textViewx;
    }

    private MaterialCardView getCardMaterialCard() {
        MaterialCardView materialCardView = new MaterialCardView(this);
        float pix = convertDptoPx(152);
        //Setting the constraints
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        materialCardView.setLayoutParams(params);
        materialCardView.setId(R.id.mat_cad_1);
        //Add const
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintSet);

        constraintSet.connect(R.id.mat_cad_1,ConstraintSet.BOTTOM,mConstraintLayout.getId(), ConstraintSet.TOP,32);
        return materialCardView;
    }

    private float convertDptoPx(int dp){
        float pix;
        Resources resources = getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        pix = dp *(displayMetrics.densityDpi)/160.0F;
        return pix;
    }

}
