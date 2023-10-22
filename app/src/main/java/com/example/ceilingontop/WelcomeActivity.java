package com.example.ceilingontop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {
    ImageView appIcon, welcomeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        appIcon = (ImageView) findViewById(R.id.appLogo);
        welcomeText = (ImageView) findViewById(R.id.welcomeText);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.welcome_animation);
        appIcon.startAnimation(animation1);


        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.text_animation);
        welcomeText.startAnimation(animation2);

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                welcomeText.animate().translationY(-100).setDuration(800).setStartDelay(300);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}