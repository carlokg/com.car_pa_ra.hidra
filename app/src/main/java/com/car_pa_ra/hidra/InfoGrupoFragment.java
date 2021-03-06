package com.car_pa_ra.hidra;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.car_pa_ra.hidra.model.Grupos;
import com.car_pa_ra.hidra.recyclerUtil.MyViewModel;


/**
 * @author Raúl
 * @author Pablo
 * @author Carlos
 *
 * Fragmento que se carga sobre MainActivity al pulsar en una de las cards
 * del REcyclerView de una de: SocialFragment, AyudaFragment o ExploraFragment.
 * Contiene la informacion del grupo seleccionado en las vistas anteriormente mencionadas.
 * Si se pulsa sobre la ubicación, se abrirá MapaActivity.
 *
 * @see MapaActivity
 * @see SocialFragment
 * @see AyudaFragment
 * @see ExploraFragment
 *
 */
public
class InfoGrupoFragment extends Fragment {

    TextView tvNombreGrupo;
    TextView tvUbicacion;
    TextView tvTipoGrupo;
    TextView tvDescripcionGrupo;
    ImageView ivImagenGrupo;
    ImageView ivTipoGrupo;
    LinearLayout llUbic;

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
        llUbic = view.findViewById(R.id.llUbic);

        viewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(MyViewModel.class);
        final Grupos gAc = viewModel.getG();

        llUbic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MapaActivity.class);
                i.putExtra( "LOCATION", gAc.getUbicacion() );
                startActivity(i);
            }
        });




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