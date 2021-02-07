package com.car_pa_ra.hidra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registro extends AppCompatActivity {

    ImageView imgLogin;
    FirebaseAuth fba;
    FirebaseUser user;
    TextInputLayout etEmail;
    TextInputLayout etPw;
    TextInputLayout etPw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        imgLogin = findViewById(R.id.imgLogin);

        etEmail = findViewById(R.id.etEmail);
        etPw = findViewById(R.id.etPw);
        etPw2 = findViewById(R.id.etPw2);
        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();

        Glide.with(this).load(R.drawable.perfil).circleCrop().into(imgLogin);
    }

    public void btnRegistrar(View view) {
        String email = etEmail.getEditText().getText().toString().trim();
        String pw = etPw.getEditText().getText().toString().trim();
        String pw2 = etPw2.getEditText().getText().toString().trim();
        if (email.isEmpty() || pw.isEmpty()||pw2.isEmpty()) {
            Toast.makeText(this, R.string.no_data,
                    Toast.LENGTH_SHORT).show();
        } else if(pw.equals(pw2)){
            fba.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = fba.getCurrentUser();
                                // ACCEDER A LA APLICACIÓN
                                accederApp();
                                Toast.makeText(Registro.this,
                                        R.string.msj_registrado,
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Registro.this,
                                        getString(R.string.msj_no_registrado)
                                                + "\n" + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } } });
        }else{

            Toast.makeText(Registro.this,
                    R.string.msj_pw_iguales,
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void accederApp() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}