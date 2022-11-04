package com.pucp.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EquiposActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);


        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void AgregarEquipos(View view){

        HashMap<String,String> equipos = new HashMap<>();

        EditText equipo1 = findViewById(R.id.editText_equipo1);
        String equipo1Str = equipo1.getText().toString();

        EditText equipo2 = findViewById(R.id.editText_equipo2);
        String equipo2Str = equipo2.getText().toString();

        equipos.put("1",equipo1Str);
        equipos.put("2",equipo2Str);

        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("equipo").setValue(equipos).addOnSuccessListener(aVoid -> {
            Log.d("msg","Equipos guardado exitosamente");

        }).addOnFailureListener(e -> {
            Log.d("msg",e.getMessage());
        });
    }
}