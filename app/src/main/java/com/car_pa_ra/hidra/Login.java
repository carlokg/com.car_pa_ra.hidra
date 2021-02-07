package com.car_pa_ra.hidra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Login extends AppCompatActivity {

        ImageView imgLogin;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            imgLogin = findViewById(R.id.imgLogin);

            Glide.with(this).load(R.drawable.ic_icon).into(imgLogin);
        }


    public void btnEntrar(View view) {
            Intent iExplora = new Intent(this, MainActivity.class);
            startActivity(iExplora);//a
    }

    public void btnRegistro(View view) {
            Intent iRegis = new Intent(this, Registro.class);
            startActivity(iRegis);
    }
}