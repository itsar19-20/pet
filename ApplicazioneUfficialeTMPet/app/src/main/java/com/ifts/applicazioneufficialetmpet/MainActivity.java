package com.ifts.applicazioneufficialetmpet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private ProgressBar cerchio;

    private FirebaseUser user;
    private FirebaseAuth auth;
    private DatabaseReference rootReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        rootReference = FirebaseDatabase.getInstance().getReference();

        cerchio = (ProgressBar) findViewById(R.id.progressBar_circle);
        start = (Button) findViewById(R.id.button_start);

        start.setVisibility(View.INVISIBLE);
        start.postDelayed(new Runnable() {
            public void run() {
                start.setVisibility(View.VISIBLE);
                cerchio.setVisibility(View.INVISIBLE);
            }
        }, 2000);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToSceltaStart();
                finish();
            }
        });
    }

    protected void onStart() {

        super.onStart();

        if (user == null){
            SendUserToLogin();
        }else{
            VerifyUserExistence();
        }
    }

    private void VerifyUserExistence(){
        String userId = auth.getCurrentUser().getUid();
        rootReference.child("Users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ((dataSnapshot.child("name").exists())){
                    Toast.makeText(MainActivity.this, "welcome", Toast.LENGTH_LONG).show();
                }else{
                    SendUserToProfile();
                   // Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void SendUserToLogin() {
        Intent loginIntent = new Intent(MainActivity.this, Activity_login.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
    private void SendUserToSceltaStart() {
        Intent sceltaStartIntent = new Intent(MainActivity.this, Activity_scelta_start.class);
        startActivity(sceltaStartIntent);
    }
    private void SendUserToProfile(){
        Intent profiloIntent = new Intent(MainActivity.this, Activity_profilo.class);
        profiloIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(profiloIntent);
        finish();
    }
}


