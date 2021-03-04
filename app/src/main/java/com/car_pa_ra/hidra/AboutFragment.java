package com.car_pa_ra.hidra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * @author Raúl
 * @author Pablo
 * @author Carlos
 *
 * Si se pulsa sobre este elemento de la ventana emergente,
 * se cargará un fragmento con información de los desarrolladores.
 *
 * @see MapaActivity
 */


public class AboutFragment extends Fragment {

   ImageView imgp;
   ImageView imgc;
   ImageView imgr;




    public AboutFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about, container, false);

        imgr = view.findViewById(R.id.img3);
        imgc = view.findViewById(R.id.img2);
        imgp = view.findViewById(R.id.img1);



        Glide.with(this).load(R.drawable.fotor).circleCrop().into(imgr);
        Glide.with(this).load(R.drawable.fotoc).circleCrop().into(imgc);
        Glide.with(this).load(R.drawable.fotop).circleCrop().into(imgp);

        return view;
    }
}