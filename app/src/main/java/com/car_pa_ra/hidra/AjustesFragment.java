package com.car_pa_ra.hidra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class AjustesFragment extends Fragment implements View.OnClickListener{

    ImageView imgAjustes;
    Button btnAboutus;

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

        btnAboutus = (Button) view.findViewById(R.id.btnAbout);
        btnAboutus.setOnClickListener(this);

        return view;


    }

    @Override
    public void onClick(View v) {
        //TODO TERMINAR ONCLICK
       /* Fragment selectedFragment = null;
        switch (v.getId()) {
            case R.id.btnAbout:

                AboutFragment abf = new AboutFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, abf)
                        .commit();
                //selectedFragment = new AboutFragment();

                break;
            case R.id.btnHelp:
                break;

            case R.id.btnLogout:
                break;


        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit();*/

    }
}