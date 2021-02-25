package com.car_pa_ra.hidra.recyclerUtil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.car_pa_ra.hidra.R;
import com.car_pa_ra.hidra.model.Grupos;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.GrupoViewHolder> {

    private List<Grupos> items;

    public static class GrupoViewHolder extends RecyclerView.ViewHolder{

        public ImageView imagen;
        public TextView titulo;
        public TextView descripcion;
        public TextView tipo;



        public GrupoViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            titulo = (TextView) v.findViewById(R.id.titulo);
            descripcion = (TextView) v.findViewById(R.id.descripcion);
            tipo = (TextView) v.findViewById(R.id.tipo);
        }
    }

    public Adapter(List<Grupos> items) {
        this.items = items;
    }

    @Override
    public GrupoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card, viewGroup, false);
        return new GrupoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.GrupoViewHolder holder, int position) {
        //holder.imagen.setImageResource(items.get(position).getImagen());
        holder.titulo.setText(items.get(position).getTitulo());
        holder.descripcion.setText(items.get(position).getDescripcion());
        holder.tipo.setText(items.get(position).getTipo());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
