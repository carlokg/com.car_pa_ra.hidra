package com.car_pa_ra.hidra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public
class InfoGrupoFragment extends Fragment {

    TextView tvNombreGrupo;
    TextView tvUbicacion;
    TextView tvTipoGrupo;
    TextView tvDescripcionGrupo;
    ImageView ivImagenGrupo;

    public InfoGrupoFragment() {
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

        View view = inflater.inflate(R.layout.fragment_info_grupo, container, false);

        ivImagenGrupo = view.findViewById(R.id.ivImagenGrupo);

        tvNombreGrupo = view.findViewById(R.id.tvNombreGrupo);
        tvUbicacion = view.findViewById(R.id.tvUbicacion);
        tvTipoGrupo = view.findViewById(R.id.tvTipoGrupo);
        tvDescripcionGrupo = view.findViewById(R.id.ivImagenGrupo);

        return view;
    }
}