package com.car_pa_ra.hidra;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class AjustesFragment extends Fragment {

    private OnControlerFragmentListener listener;
    ImageView imgAjustes;
    Button btnAboutus;
    Button btnlogOut;


    public AjustesFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);
        imgAjustes = view.findViewById(R.id.imgAjustes);
        Glide.with(this).load(R.drawable.ic_appbar_config).into(imgAjustes);

        btnAboutus = (Button) view.findViewById(R.id.btnAbout);
        btnlogOut = (Button) view.findViewById(R.id.btnLogout);

        btnAboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = "about";

                listener.selectFrgment(texto);
            }
        });

        btnlogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Login.class);
                startActivity(i);
            }
        });

        return view;

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

}