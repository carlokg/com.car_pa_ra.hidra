package com.car_pa_ra.hidra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class AjustesFragment extends Fragment {

    ImageView imgAjustes;

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
        Glide.with(this).load(R.drawable.ic_appbar_config).circleCrop().into(imgAjustes);

        return view;


    }
}