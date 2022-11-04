package com.pucp.lab4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pucp.lab4.Adapters.HitoAdapter;

import java.util.ArrayList;

public class HitosActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Hitos");
        setContentView(R.layout.activity_hitos);
        progressBar = findViewById(R.id.progressBarHitos);

        ArrayList<Hito> listaHito = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recycleView_Hitos);
        HitoAdapter hitoAdapter = new HitoAdapter(listaHito);
        recyclerView.setAdapter(hitoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HitosActivity.this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        cargando();
        DatabaseReference ref = firebaseDatabase.getReference().child("hitos");
        ref.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                terminarCargando();
                for (DataSnapshot d : dataSnapshot.getChildren()){
                    listaHito.add(d.getValue(Hito.class));
                }
                hitoAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                terminarCargando();
                Toast.makeText(HitosActivity.this, "No se pudieron cargar los hitos", Toast.LENGTH_SHORT).show();
            }
        });

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Hito hito = snapshot.getValue(Hito.class);
                if (listaHito.size()>0 && hito != listaHito.get(listaHito.size()-1)){
                    listaHito.add(hito);
                    hitoAdapter.notifyItemInserted(listaHito.size()-1);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void cargando(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void terminarCargando(){
        progressBar.setVisibility(View.GONE);
    }

}