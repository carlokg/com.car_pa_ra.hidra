package com.car_pa_ra.hidra;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.car_pa_ra.hidra.model.Grupos;
import com.car_pa_ra.hidra.recyclerUtil.Adapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SocialFragment extends Fragment {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager llm;

    DatabaseReference dbRef;
    ValueEventListener vel;

    private ArrayList<Grupos> lGrupos;

    public SocialFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social, container, false);

        dbRef = FirebaseDatabase.getInstance()
                .getReference("datos/grupo");



        recycler = view.findViewById(R.id.rvExplora);
        recycler.setHasFixedSize(true);

        FloatingActionButton fabCrearGrupo = (FloatingActionButton) view.findViewById(R.id.fabCrearGrupo);
        fabCrearGrupo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), CrearGrupoActivity.class);
                startActivity(i);

            }
        });

        lGrupos = new ArrayList<Grupos>();


        return view;
    }

    private void cargarListaGrupos() {
        llm = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(llm);

        adapter = new Adapter(lGrupos);
        recycler.setAdapter(adapter);
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
                    Grupos g;
                    lGrupos.clear();
                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        if(dss.getValue(Grupos.class).getAyuSoc().equals("Social")){
                            g = dss.getValue(Grupos.class);
                            lGrupos.add(g);
                        }
                    }
                    cargarListaGrupos();
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

    public void crear(View view){
        Intent i = new Intent(getContext(), CrearGrupoActivity.class);
        startActivity(i);
    }
}