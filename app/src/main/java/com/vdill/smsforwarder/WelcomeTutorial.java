package com.vdill.smsforwarder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class WelcomeTutorial extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    Button buttonSkip, buttonNext;
    int [] layouts;
    SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_shared_preferences_key), Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!sharedPreferences.getBoolean(getString(R.string.first_time_user), false)){
            launchMainScreen();
        }
        setContentView(R.layout.activity_welcome__tutorial);
        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.layoutDots);
        buttonSkip = findViewById(R.id.button_skip);
        buttonNext = findViewById(R.id.button_next);
        layouts = new int[] {
                R.layout.tutorial_layout_one,
                R.layout.tutorial_layout_two,
                R.layout.tutorial_layout_three
        };
    }

    private void launchMainScreen(){
        editor.putBoolean(getString(R.string.first_time_user), false);
        editor.apply();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
