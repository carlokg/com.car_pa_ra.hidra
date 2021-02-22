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
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.car_pa_ra.hidra.model.Grupos;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class CrearGrupo extends AppCompatActivity {

    public static final int RC_PHOTO_ADJ = 100;

    ImageView imgGrupo;
    TextInputLayout etNomG;
    TextInputLayout etDescG;
    TextInputLayout etTipoG;
    ToggleButton swtAS;

    Uri selectedUri;
    StorageReference mFotoStorageRef;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);

        imgGrupo = findViewById(R.id.ivFoto);
        etNomG = findViewById(R.id.etNomGrupo);
        etDescG = findViewById(R.id.etDescGrupo);
        etTipoG = findViewById(R.id.etTipoGrupo);
        swtAS = findViewById(R.id.tglbtnAS);

         myRef = FirebaseDatabase.getInstance().getReference("datos").child("grupo");
         mFotoStorageRef = FirebaseStorage.getInstance().getReference()
                .child("Fotos");
    }


    public void crearGrupo(View v){
        final String nomG = etNomG.getEditText().getText().toString().trim();
        final String descG = etDescG.getEditText().getText().toString().trim();
        final String tipoG = etTipoG.getEditText().getText().toString().trim();

        final String tglCheck;

        if(swtAS.isChecked()) tglCheck = "Ayuda";
        else  tglCheck = "Social";


        if(etNomG.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this, "Debe de introducir un nombre, una descripcion y un tipo",
                    Toast.LENGTH_LONG).show();
        } else{
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
                        Grupos g = new Grupos(tglCheck,
                                downloadUri.toString(), nomG, descG, tipoG);
                        myRef.child(tglCheck+"_"+nomG)
                                .setValue(g);
                    }
                }
            });

        }
        finish();
    }

    public void adjuntarFoto(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent,
                "Complete la acción usando"), RC_PHOTO_ADJ);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_ADJ && resultCode == RESULT_OK) {
            selectedUri = data.getData();
            Glide.with(imgGrupo.getContext())
                    .load(selectedUri)
                    .into(imgGrupo);

        }
    }
}