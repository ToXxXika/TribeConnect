package com.example.tribeconnectv2;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ImageView imageView = findViewById(R.id.imageViewLogo);
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
        imageView.startAnimation(rotateAnimation);
        Objects.requireNonNull(getSupportActionBar()).hide();
       new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
               startActivity(intent);
               finish();
           }
       }, 2500);
    }
}