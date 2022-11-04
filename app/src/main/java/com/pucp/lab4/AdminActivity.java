package com.pucp.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    List <String> equiposList = new ArrayList<>();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AdminActivity.this, android.R.layout.simple_spinner_item, equiposList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().child("equipo").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for ( DataSnapshot d : dataSnapshot.getChildren()){
                    equiposList.add(d.getValue().toString());
                }
                adapter.notifyDataSetChanged();           }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AdminActivity.this, "No se pudo cargar la lista de euipos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveHito(View view){
        DatabaseReference ref = firebaseDatabase.getReference().child("hitos");

        EditText editNombre = findViewById(R.id.editText_nombre);
        EditText editApellido = findViewById(R.id.editText_apellido);
        EditText editHito = findViewById(R.id.editTextTextMultiLine);

        String nombreJugador = editNombre.getText().toString().trim();
        String apellidoJugador = editApellido.getText().toString().trim();
        String hitoJugador = editHito.getText().toString().trim();
        String equipo = spinner.getSelectedItem().toString();

        if(nombreJugador.isEmpty() || apellidoJugador.isEmpty() || hitoJugador.isEmpty() || equipo.isEmpty()){
            Toast.makeText(AdminActivity.this, "Debe llenar todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }
        DatabaseReference newRef = ref.push();
        Hito hito = new Hito(equipo,nombreJugador,apellidoJugador,hitoJugador);
        newRef.setValue(hito).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(AdminActivity.this, "Hito creado exitosamente!", Toast.LENGTH_SHORT).show();
                editNombre.setText("");
                editApellido.setText("");
                editHito.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AdminActivity.this, "Ha ocurrido un error :(", Toast.LENGTH_SHORT).show();
            }
        });


    }

}