package com.car_pa_ra.hidra;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

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
