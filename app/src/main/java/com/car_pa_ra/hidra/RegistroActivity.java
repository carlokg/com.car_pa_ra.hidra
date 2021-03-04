package com.car_pa_ra.hidra;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.car_pa_ra.hidra.model.Usuario;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * @author Raúl
 * @author Pablo
 * @author Carlos
 *
 * Ventana con campos editables para introducir los datos de la cuenta de usuario,
 * al aceptar se creará un usuario en la base de datos Firebase.
 * Y se accederá a la vista principal (MainActivity), dónde se cargarán las diferentes vistas.
 *
 * @see MainActivity
 * @see LoginActivity
 *
 */


public class RegistroActivity extends AppCompatActivity {

    private static final int RC_PHOTO_ADJ = 555;
    ImageView imgLogin;
    FirebaseAuth fba;
    FirebaseUser user;
    TextInputLayout etNomUsuario;
    TextInputLayout etDir;
    TextInputLayout etDesc;
    TextInputLayout etEmail;
    TextInputLayout etPw;
    TextInputLayout etPw2;

    Uri selectedUri;
    StorageReference mFotoStorageRef;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        imgLogin = findViewById(R.id.imgLogin);

        etEmail = findViewById(R.id.etEmail);
        etPw = findViewById(R.id.etPw);
        etPw2 = findViewById(R.id.etPw2);
        etNomUsuario = findViewById(R.id.etNomUsuario);
        etDir = findViewById(R.id.etDir);
        etDesc = findViewById(R.id.etDesc);
        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();

        myRef = FirebaseDatabase.getInstance().getReference("datos").child("usuario");
        mFotoStorageRef = FirebaseStorage.getInstance().getReference()
                .child("FotosUser");
    }

    public void btnRegistrar(View view) {
        final String nomU = etNomUsuario.getEditText().getText().toString().trim();
        final String dirU = etDir.getEditText().getText().toString().trim();
        final String descU = etDesc.getEditText().getText().toString().trim();
        final String email = etEmail.getEditText().getText().toString().trim();

        String pw = etPw.getEditText().getText().toString().trim();
        String pw2 = etPw2.getEditText().getText().toString().trim();
        if (email.isEmpty() || pw.isEmpty() || pw2.isEmpty() || nomU.isEmpty() || dirU.isEmpty() || descU.isEmpty() || selectedUri == null) {
            Toast.makeText(this, R.string.no_data,
                    Toast.LENGTH_SHORT).show();
        } else if(pw.equals(pw2)){
            fba.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                user = fba.getCurrentUser();
                                final StorageReference fotoRef = mFotoStorageRef
                                        .child(selectedUri.getEncodedPath());
                                UploadTask ut = fotoRef.putFile(selectedUri);

                                Task<Uri> urlTask = ut.continueWithTask(
                                        new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                            @Override
                                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task)
                                                    throws Exception {
                                                if (!task.isSuccessful()) {
                                                    throw task.getException();
                                                }
                                                return fotoRef.getDownloadUrl();
                                            }
                                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            Uri downloadUri = task.getResult();
                                            Usuario u = new Usuario(downloadUri.toString(), user.getUid(), nomU, email, dirU, descU );
                                            myRef.child(user.getUid()+"_"+nomU)
                                                    .setValue(u);
                                        }
                                    }
                                });
                                // ACCEDER A LA APLICACIÓN
                                accederApp();
                                Toast.makeText(RegistroActivity.this,
                                        R.string.msj_registrado,
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegistroActivity.this,
                                        getString(R.string.msj_no_registrado)
                                                + "\n" + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } } });
        }else{

            Toast.makeText(RegistroActivity.this,
                    R.string.msj_pw_iguales,
                    Toast.LENGTH_SHORT).show();
        }


    }
    public void adjuntarFotoUser(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.CATEGORY_APP_GALLERY, true);
        startActivityForResult(Intent.createChooser(intent,
                "abriendo galeria"), RC_PHOTO_ADJ);
    }

    private void accederApp() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_PHOTO_ADJ && resultCode == RESULT_OK) {
            selectedUri = data.getData();

            Glide.with(imgLogin.getContext())
                    .load(selectedUri)
                    .circleCrop()
                    .into(imgLogin);
        }

    }


}