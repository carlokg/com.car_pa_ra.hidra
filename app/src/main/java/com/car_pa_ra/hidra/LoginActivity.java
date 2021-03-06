package com.car_pa_ra.hidra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author Raúl
 * @author Pablo
 * @author Carlos
 *
 * Ventana principal para logearse, desde aquí se puede iniciar sesión o pasar
 * a la pantalla de registro me diante dos botones. Recordará el último usuario logeado.
 *
 * @see MainActivity
 * @see RegistroActivity
 *
 */

public class LoginActivity extends AppCompatActivity {

        ImageView imgLogin;
        TextInputLayout etNomUsuario;
        TextInputLayout etPw;
        private FirebaseAuth fba;
        private FirebaseUser user;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            imgLogin = findViewById(R.id.imgLogin);

            etNomUsuario = findViewById(R.id.etNomUsuario);
            etPw = findViewById(R.id.etPw);
            fba = FirebaseAuth.getInstance();
            user = fba.getCurrentUser();

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
                                Toast.makeText(LoginActivity.this,
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
            Intent iRegis = new Intent(this, RegistroActivity.class);
            startActivity(iRegis);
    }
}