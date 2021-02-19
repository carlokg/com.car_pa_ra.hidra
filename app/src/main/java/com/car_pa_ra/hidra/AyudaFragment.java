package com.car_pa_ra.hidra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.car_pa_ra.hidra.model.Grupos;
import com.car_pa_ra.hidra.recyclerUtil.Adapter;

import java.util.ArrayList;
import java.util.List;

public class AyudaFragment extends Fragment {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager llm;

    public AyudaFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explora, container, false);

        List<Grupos> items = new ArrayList<>();

        items.add(new Grupos(R.drawable.deporte, "Titulo1", "Navig ation, A simple" +
                " solution is to declare an inte rface which is declaring the Depen dencies required "
                , "Deporte"));
        items.add(new Grupos(R.drawable.deporte, "Titulo2", "2", "Cocina"));
        items.add(new Grupos(R.drawable.deporte, "Titulo3", "3", "Deporte"));
        items.add(new Grupos(R.drawable.deporte, "Titulo4", "4", "Cocina"));
        items.add(new Grupos(R.drawable.deporte, "Titulo5", "5", "Deporte"));
        items.add(new Grupos(R.drawable.anim3, "Titulo6", "6", "Cocina"));


        recycler = view.findViewById(R.id.rvExplora);
        recycler.setHasFixedSize(true);

        llm = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(llm);

        adapter = new Adapter(items);
        recycler.setAdapter(adapter);

        return view;
    }
}