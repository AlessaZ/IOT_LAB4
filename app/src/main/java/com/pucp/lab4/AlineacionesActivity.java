package com.pucp.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pucp.lab4.Adapters.AlineacionAdapter;
import com.pucp.lab4.Adapters.HitoAdapter;

import java.util.ArrayList;
import java.util.List;

public class AlineacionesActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Hitos");
        setContentView(R.layout.activity_alineaciones);
        ArrayList<Hito> listaHito = new ArrayList<>();
        ArrayList<String> listaJugadores = new ArrayList<>();
        Intent intent = getIntent();
        String comparar =(String) intent.getStringExtra("equipo");
        RecyclerView recyclerView =findViewById(R.id.recycleView_alineaciones);
        AlineacionAdapter alineacionAdapter = new AlineacionAdapter(listaJugadores);
        recyclerView.setAdapter(alineacionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AlineacionesActivity.this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference().child("hitos");

        ref.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()){
                    listaHito.add(d.getValue(Hito.class));
                }
                for(Hito hito : listaHito){
                    if(hito.getEquipo().equals(comparar)){
                        String jugador = hito.getNombreJugador()+" "+hito.getApellidoJugador();
                        if(!listaJugadores.contains(jugador)){
                            listaJugadores.add(jugador);
                        }
                    }
                }
                alineacionAdapter.notifyDataSetChanged();
            }
        });







    }
}