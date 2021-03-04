package com.car_pa_ra.hidra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Raúl
 * @author Pablo
 * @author Carlos
 *
 * Fragmento que contiene información de contacto sobre la app, asi como tutoriales sobre
 * la app y terminos legales.
 *
 * @see InfoGrupoFragment
 * @see PerfilFragment
 *
 */


public class HelpFragment extends Fragment {


    public HelpFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        return view;
    }
}