package com.pucp.lab4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new FirebaseAuthUIActivityResultContract(), result -> {
        onSignInOnResult(result);
    });

    private FirebaseDatabase firebaseDatabase;

    private void onSignInOnResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse idpResponse = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {

            firebaseDatabase.getReference("equipo").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {
                    Intent intentAdmin;
                    if (dataSnapshot.getChildrenCount()<2){
                        intentAdmin = new Intent(MainActivity.this, EquiposActivity.class);
                    }else{
                        intentAdmin = new Intent(MainActivity.this, AdminActivity.class);
                    }
                    startActivity(intentAdmin);
                    finish();
                }
            });
        } else {
            Log.d("msg-fb", "error al loguearse");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();

        Button btnLogin = findViewById(R.id.button_admin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fbIntent = AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.GoogleBuilder().build()
                        ))
                        .setTosAndPrivacyPolicyUrls(
                                "https://example.com/terms.html",
                                "https://example.com/privacy.html")
                        .setLogo(R.drawable.icons8_football_kick_64)
                        .build();
                activityResultLauncher.launch(fbIntent);
            }
        });
    }

    public void User(View view){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

}