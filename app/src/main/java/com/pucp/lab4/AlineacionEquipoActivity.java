package com.pucp.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class AlineacionEquipoActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alineacion_equipo);

        Button btn_equipo1 = findViewById(R.id.button_equipo1);
        Button btn_equipo2 = findViewById(R.id.button_equipo2);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().child("equipo").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                int i=0;
                for ( DataSnapshot d : dataSnapshot.getChildren()){
                    if(i==0){
                        btn_equipo1.setText(d.getValue().toString());
                    }else{
                        btn_equipo2.setText(d.getValue().toString());
                    }
                    i++;
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AlineacionEquipoActivity.this, "No se pudo establecer conexión", Toast.LENGTH_SHORT).show();
                finish();
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