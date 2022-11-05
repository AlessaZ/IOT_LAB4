package com.pucp.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Alineaciones");
        setContentView(R.layout.activity_alineaciones);
        RecyclerView recyclerView =findViewById(R.id.recycleView_alineaciones);
        progressBar = findViewById(R.id.progressBarAlineaciones);

        ArrayList<Hito> listaHito = new ArrayList<>();
        ArrayList<String> listaJugadores = new ArrayList<>();
        Intent intent = getIntent();
        String comparar =(String) intent.getStringExtra("equipo");
        TextView tv = findViewById(R.id.textView_nombreEquipo);
        tv.setText(comparar);
        AlineacionAdapter alineacionAdapter = new AlineacionAdapter(listaJugadores);
        recyclerView.setAdapter(alineacionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AlineacionesActivity.this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference().child("hitos");
        cargando();
        ref.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                terminarCargando();
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
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                terminarCargando();
                Toast.makeText(AlineacionesActivity.this, "No se pudo establecer conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cargando(){
        progressBar.setVisibility(View.VISIBLE);

    }

    public void terminarCargando(){
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}