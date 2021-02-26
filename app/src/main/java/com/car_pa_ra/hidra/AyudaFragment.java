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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AyudaFragment extends Fragment {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager llm;

    MaterialButton btnCrear;
    MaterialButton btnExplora;
    MaterialButton btnSocial;

    DatabaseReference dbRef;
    ValueEventListener vel;

    private ArrayList<Grupos> lGrupos;

    public AyudaFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ayuda, container, false);

        dbRef = FirebaseDatabase.getInstance()
                .getReference("datos/grupo");

        recycler = view.findViewById(R.id.rvExplora);
        recycler.setHasFixedSize(true);

        lGrupos = new ArrayList<Grupos>();

        btnCrear = view.findViewById(R.id.btnCrearA);
        btnExplora = view.findViewById(R.id.btnExploraA);
        btnSocial = view.findViewById(R.id.btnSocialA);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CrearGrupoActivity.class);
                startActivity(i);
            }
        });
        btnExplora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ExploraFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        btnSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new SocialFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

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
                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        if(dss.getValue(Grupos.class).getAyuSoc().equals("Ayuda")){
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


    public void goSocial(View view){

    }
}