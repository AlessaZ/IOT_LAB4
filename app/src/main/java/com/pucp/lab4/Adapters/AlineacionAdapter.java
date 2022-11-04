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

public class AlineacionAdapter extends RecyclerView.Adapter<AlineacionAdapter.ViewHolder> {
    private ArrayList<String> listaJugadores;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;

        public ViewHolder(View view){
            super(view);
            textView = (TextView) view.findViewById(R.id.textViewJugador);
        }
        public TextView getTextView(){
            return getTextView();
        }

    }

    public AlineacionAdapter(ArrayList<String> dataSet){
        listaJugadores=dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_alineaciones,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        String mostrar = "Nombres de Jugadores: "+"\n-"+hito.getNombreJugador()+" "+hito.getApellidoJugador();
        TextView textShow = holder.itemView.findViewById(R.id.textViewJugador);
//        textShow.setText(mostrar);
    }

    @Override
    public int getItemCount() {
        return listaJugadores.size();
    }
}
