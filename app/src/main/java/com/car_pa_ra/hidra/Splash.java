package com.car_pa_ra.hidra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Splash extends AppCompatActivity {

    ImageView imgApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgApp = findViewById(R.id.imgApp);
        Glide.with(this).load(R.drawable.ic_icon_claro).into(imgApp);


        Animation elegance = AnimationUtils.loadAnimation(this, R.anim.fadeln);
        imgApp.startAnimation(elegance);
        openApp(true);
    }

    private void openApp(boolean locationPermission) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash
                        .this, Login.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}