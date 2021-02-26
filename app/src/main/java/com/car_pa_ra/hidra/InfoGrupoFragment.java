package com.car_pa_ra.hidra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public
class InfoGrupoFragment extends Fragment {


    public
    InfoGrupoFragment() {
        // Required empty public constructor
    }


    public static
    InfoGrupoFragment newInstance(String param1, String param2) {
        InfoGrupoFragment fragment = new InfoGrupoFragment();

        return fragment;
    }

    @Override
    public
    void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public
    View onCreateView(LayoutInflater inflater, ViewGroup container,
                      Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_info_grupo, container, false );
    }
}