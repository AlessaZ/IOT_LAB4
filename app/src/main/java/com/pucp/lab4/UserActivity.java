package com.pucp.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Iterator;
import java.util.Objects;


public class UserActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Button btnAlineaciones;
    Button btnHitos;
    TextView tvMsg;
    String equipo1;
    String equipo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        progressBar = findViewById(R.id.progressBarUser);
        btnAlineaciones = findViewById(R.id.button_alineaciones);
        btnHitos = findViewById(R.id.button_hito);
        tvMsg = findViewById(R.id.tvUser);
        cargando();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("equipo").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount()<2){
                    progressBar.setVisibility(View.GONE);
                    tvMsg.setText("El administrador aun no ha agregado información del partido");
                    tvMsg.setVisibility(View.VISIBLE);
                }else{
                    terminarCargando();
                    Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                    equipo1 = Objects.requireNonNull(iterator.next().getValue()).toString();
                    equipo2 = Objects.requireNonNull(iterator.next().getValue()).toString();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                terminarCargando();
                Toast.makeText(UserActivity.this, "No se ha podido establecer conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void listHito(View view){
        Intent intent = new Intent(UserActivity.this,HitosActivity.class);
        startActivity(intent);
    }

    public void listAlineaciones(View view){
        Intent intent = new Intent(UserActivity.this,AlineacionEquipoActivity.class);
        intent.putExtra("equipo1",equipo1);
        intent.putExtra("equipo2",equipo2);
        startActivity(intent);
    }

    public void cargando(){
        progressBar.setVisibility(View.VISIBLE);
        btnHitos.setVisibility(View.GONE);
        btnAlineaciones.setVisibility(View.GONE);
        tvMsg.setVisibility(View.GONE);
    }

    public void terminarCargando(){
        progressBar.setVisibility(View.GONE);
        btnHitos.setVisibility(View.VISIBLE);
        btnAlineaciones.setVisibility(View.VISIBLE);
    }

}