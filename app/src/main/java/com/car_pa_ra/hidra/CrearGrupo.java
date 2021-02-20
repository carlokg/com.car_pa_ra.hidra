package com.car_pa_ra.hidra;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.car_pa_ra.hidra.model.Grupos;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrearGrupo extends AppCompatActivity {

    ImageView imgGrupo;
    TextInputLayout etNomG;
    TextInputLayout etDescG;
    TextInputLayout etTipoG;
    ToggleButton swtAS;

    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);

        imgGrupo = findViewById(R.id.imgGrupo);
        etNomG = findViewById(R.id.etNomGrupo);
        etDescG = findViewById(R.id.etDescGrupo);
        etTipoG = findViewById(R.id.etTipoGrupo);
        swtAS = findViewById(R.id.tglbtnAS);

         database = FirebaseDatabase.getInstance();
         myRef = database.getReference();
    }


    public void crearGrupo(View v){
        String nomG;
        String descG = "";
        String tipoG = "";
        int imgG = 0;
        String tglCheck;

        if(etNomG.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this, "Debe de introducir un nombre",
                    Toast.LENGTH_LONG).show();
        } else{
            nomG =etNomG.getEditText().getText().toString().trim();
            descG = etDescG.getEditText().getText().toString().trim();
            tipoG = etTipoG.getEditText().getText().toString().trim();
            //imgG = imgGrupo.getDrawable().hashCode();
            if(swtAS.isChecked()) tglCheck = "Ayuda";
            else  tglCheck = "Social";

            //Grupos grupo = new Grupos(tglCheck,imgG,nomG,descG,tipoG);
            Grupos grupo = new Grupos();

            myRef.child("grupos").setValue(grupo);

            Toast.makeText(getApplicationContext(), "Grupo Creado", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}