package com.pucp.lab4.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucp.lab4.Hito;
import com.pucp.lab4.R;

import java.util.ArrayList;

public class HitoAdapter extends RecyclerView.Adapter<HitoAdapter.ViewHolder> {
    private ArrayList<Hito> listaHito;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textEquipo;
        private final TextView textJugador;
        private final TextView textHito;

        public ViewHolder(View view){
            super(view);
            textEquipo = (TextView) view.findViewById(R.id.textEquipo);
            textJugador = (TextView) view.findViewById(R.id.textJugador);
            textHito = (TextView) view.findViewById(R.id.textHito);
        }
        public TextView getTextView(){
            return getTextView();
        }

    }

    public HitoAdapter(ArrayList<Hito> dataSet){
        listaHito=dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_hitos,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hito hito = listaHito.get(position);
        String mostrarEquipo= "Equipo: "+ hito.getEquipo();
        String mostrarJugador= "Jugador: "+ hito.getNombreJugador() + "" + hito.getApellidoJugador();
        String mostrarHito= "Hito: "+ hito.getHito();

        TextView textShowEquipo = holder.itemView.findViewById(R.id.textEquipo);
        TextView textShowJugador = holder.itemView.findViewById(R.id.textJugador);
        TextView textShowHito = holder.itemView.findViewById(R.id.textHito);
        textShowEquipo.setText(mostrarEquipo);
        textShowJugador.setText(mostrarJugador);
        textShowHito.setText(mostrarHito);
    }

    @Override
    public int getItemCount() {
        return listaHito.size();
    }
}
