package com.car_pa_ra.hidra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Registro extends AppCompatActivity {

    ImageView imgLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        imgLogin = findViewById(R.id.imgLogin);

        Glide.with(this).load(R.drawable.perfil).circleCrop().into(imgLogin);
    }
}