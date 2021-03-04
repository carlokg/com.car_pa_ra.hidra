package com.car_pa_ra.hidra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
/**
 * @author Raúl
 * @author Pablo
 * @author Carlos
 *
 * Ventana de carga de la aplicación, cuenta con una animación de fondo de la galaxia
 * y tiene el nombre de la aplicación sobre la animación.
 *
 * @see InfoGrupoFragment
 * @see PerfilFragment
 *
 */


public class SplashActivity extends AppCompatActivity {

    ImageView imgApp;
    View fondo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        fondo = findViewById(R.id.fondo);
        fondo.setBackgroundResource(R.drawable.splash_anim);

        AnimationDrawable progressAnimation = (AnimationDrawable) fondo.getBackground();
        progressAnimation.start();

        openApp(true);
    }

    private void openApp(boolean locationPermission) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity
                        .this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}