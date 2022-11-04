package com.pucp.lab4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.View;
import com.pucp.lab4.Adapters.HitoAdapter;

import java.util.ArrayList;

public class HitosActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Hitos");
        setContentView(R.layout.activity_hitos);
        ArrayList<Hito> listaHito = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference().child("hitos");
        ref.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {
                    for (DataSnapshot d : dataSnapshot.getChildren()){
                        Log.d("msg", d.getValue(Hito.class).getHito());
                    }
                }
        });

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

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

        RecyclerView recyclerView = findViewById(R.id.recycleView_Hitos);
        HitoAdapter hitoAdapter = new HitoAdapter(listaHito);
        recyclerView.setAdapter(hitoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HitosActivity.this));
    }

}