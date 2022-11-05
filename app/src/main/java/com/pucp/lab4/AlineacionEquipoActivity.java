package com.pucp.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alineacion_equipo);

        Intent intent = getIntent();
        Button btn_equipo1 = findViewById(R.id.button_equipo1);
        Button btn_equipo2 = findViewById(R.id.button_equipo2);
        btn_equipo1.setText(intent.getStringExtra("equipo1"));
        btn_equipo2.setText(intent.getStringExtra("equipo2"));
    }

    public void Buttons(View view){
        intent = new Intent(this, AlineacionesActivity.class);
        intent.putExtra("equipo", ((Button) view).getText());
        startActivity(intent);
    }

}