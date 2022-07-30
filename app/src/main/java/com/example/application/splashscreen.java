package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.content.Repeater;

public class splashscreen extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getSupportActionBar().hide();
        textView = findViewById(R.id.Welcome);
        LottieAnimationView lottieAnimationView;

        lottieAnimationView = findViewById(R.id.logo);
        lottieAnimationView.animate().setDuration(1000).setStartDelay(8000);
        lottieAnimationView.setRepeatCount(Animation.INFINITE);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity(new Intent(splashscreen.this,MainActivity.class));
            }

        },4000);
    }
}