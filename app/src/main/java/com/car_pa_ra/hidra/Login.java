package com.car_pa_ra.hidra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Login extends AppCompatActivity {

        ImageView imgLogin;
        TextInputLayout etNomUsuario;
        TextInputLayout etPw;
        Button btnRegistrarse;
        private FirebaseAuth fba;
        private FirebaseUser user;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            imgLogin = findViewById(R.id.imgLogin);

            etNomUsuario = findViewById(R.id.etNomUsuario);
            etPw = findViewById(R.id.etPw);
            btnRegistrarse = findViewById(R.id.btnRegistrarse);
            fba = FirebaseAuth.getInstance();
            user = fba.getCurrentUser();

            Glide.with(this).load(R.drawable.ic_icon).into(imgLogin);

            if (user != null) {
                etNomUsuario.getEditText().setText(user.getEmail());
            }
        }


    public void btnEntrar(View view) {
        String email = etNomUsuario.getEditText().getText().toString().trim();
        String password = etPw.getEditText().getText().toString().trim();
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, getString(R.string.no_data),
                    Toast.LENGTH_LONG).show();
        } else {
            fba.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = fba.getCurrentUser();
                                accederApp();
                                finish();
                            } else {
                                Toast.makeText(Login.this,
                                        getString(R.string.msj_no_accede)
                                                + "\n" + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                Log.i("Sing in", "error: "+ task.getException().getMessage());
                            }
                        }
                    });
        }
    }

    private void accederApp() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void btnRegistro(View view) {
            Intent iRegis = new Intent(this, Registro.class);
            startActivity(iRegis);
    }
}