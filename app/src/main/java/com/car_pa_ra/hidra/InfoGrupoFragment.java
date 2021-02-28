package com.car_pa_ra.hidra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.car_pa_ra.hidra.model.Grupos;
import com.car_pa_ra.hidra.recyclerUtil.MyViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public
class InfoGrupoFragment extends Fragment {

    TextView tvNombreGrupo;
    TextView tvUbicacion;
    TextView tvTipoGrupo;
    TextView tvDescripcionGrupo;
    ImageView ivImagenGrupo;
    ImageView ivTipoGrupo;

    private MyViewModel viewModel;



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
        ivTipoGrupo = view.findViewById(R.id.ivTipoGrupo);
        tvDescripcionGrupo = view.findViewById(R.id.tvDescripcionGrupo);

        viewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(MyViewModel.class);
        Grupos gAc = viewModel.getG();

        if(gAc.getAyuSoc().toLowerCase().equals("ayuda")){
            Glide.with(ivTipoGrupo.getContext())
                    .load(R.drawable.ic_appbar_ayuda)
                    .into(ivTipoGrupo);
        } else{
            Glide.with(ivTipoGrupo.getContext())
                    .load(R.drawable.ic_appbar_social)
                    .into(ivTipoGrupo);
        }


        Glide.with(ivImagenGrupo.getContext())
                .load(gAc.getImagen())
                .into(ivImagenGrupo);



        tvNombreGrupo.setText( gAc.getTitulo() );
        tvUbicacion.setText( gAc.getUbicacion() );
        tvTipoGrupo.setText( gAc.getTipo() );
        tvDescripcionGrupo.setText( gAc.getDescripcion() );

        return view;
    }
}