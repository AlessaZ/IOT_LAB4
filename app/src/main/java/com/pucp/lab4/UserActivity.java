package com.pucp.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    public void listHito(View view){
        Intent intent = new Intent(UserActivity.this,HitosActivity.class);
        startActivity(intent);
    }

    public void listAlineaciones(View view){
        Intent intent = new Intent(UserActivity.this,AlineacionEquipoActivity.class);
        startActivity(intent);
    }


}