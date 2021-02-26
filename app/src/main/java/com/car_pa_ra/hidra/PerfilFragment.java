package com.car_pa_ra.hidra;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.car_pa_ra.hidra.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilFragment  extends Fragment {

    private OnControlerFragmentListener listener;
    
    ImageView imgUsuario;
    TextView tvUsername;
    TextView tvNsocial;
    TextView tvNayuda;
    TextView tvNvaloracion;
    TextView tvDescripcion;
    TextView tvCorreoUsuario;
    TextView tvLocalizacion;

    LinearLayout llLocation;
    DatabaseReference dbRef;
    ValueEventListener vel;
    Usuario user = new Usuario();
    FirebaseUser userFb;
    FirebaseAuth fba;

    public PerfilFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        imgUsuario = view.findViewById(R.id.imgUsuario);
        Glide.with(this).load(R.drawable.perfil).into(imgUsuario);

        tvUsername = view.findViewById(R.id.tvUsername);
        tvNsocial = view.findViewById(R.id.tvNsocial);
        tvNayuda = view.findViewById(R.id.tvNayuda);
        tvNvaloracion = view.findViewById(R.id.tvNvaloracion);
        tvDescripcion = view.findViewById(R.id.tvDescripcion);
        tvCorreoUsuario = view.findViewById(R.id.tvCorreoUsuario);
        tvLocalizacion = view.findViewById(R.id.tvLocalizacion);
        llLocation = view.findViewById(R.id.llLocation);
        fba = FirebaseAuth.getInstance();
        userFb = fba.getCurrentUser();

        llLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MapaActivity.class);
                i.putExtra( "LOCATION", user.getDir() );
                startActivity(i);
            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference("datos/usuario");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        addListener();
    }

    private void addListener() {
        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        if (userFb.getUid().equals(dss.getValue(Usuario.class).getuId())){
                            user = dss.getValue(Usuario.class);
                        }
                    }

                    cargarUser();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(),
                            R.string.error_lectura, Toast.LENGTH_LONG).show();
                }
            };
            dbRef.addValueEventListener(vel);
        }
    }

    private void cargarUser() {
        Glide.with(imgUsuario.getContext())
                .load(user.getImg())
                .circleCrop()
                .into(imgUsuario);

        tvUsername.setText(user.getNombre());
        tvDescripcion.setText(user.getDesc());
        tvCorreoUsuario.setText(user.getEmail());
        tvLocalizacion.setText(user.getDir());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnControlerFragmentListener) {
            listener = (OnControlerFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        removeListener();

    }

    private void removeListener() {
        if (vel != null) {
            dbRef.removeEventListener(vel);
            vel = null;
        }
    }
}
