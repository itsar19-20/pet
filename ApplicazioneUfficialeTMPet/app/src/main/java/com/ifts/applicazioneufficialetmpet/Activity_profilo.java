package com.ifts.applicazioneufficialetmpet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class Activity_profilo extends AppCompatActivity {

    private CircleImageView imgProfilo;
    private EditText username;
    private EditText status;
    private Button update;
    private TextView usernameScelto;

    private String userID;
    private FirebaseAuth auth;
    private DatabaseReference rootReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        auth = FirebaseAuth.getInstance();
        userID = auth.getCurrentUser().getUid();
        rootReference = FirebaseDatabase.getInstance().getReference();

        initialize();
       // username.setVisibility(View.INVISIBLE);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfilo();
            }
        });
        saveUserInof();
    }

    public void initialize(){
        imgProfilo = (CircleImageView) findViewById(R.id.image_profilo);
        username = (EditText) findViewById(R.id.editText_username_profilo);
        status = (EditText) findViewById(R.id.editText_status_profilo);
        update = (Button) findViewById(R.id.button_update_profilo);
        usernameScelto  = (TextView) findViewById(R.id.textView_username_scelto_profilo);
    }

    public void updateProfilo(){
        String setUsername = username.getText().toString();
        String setStatus = status.getText().toString();

        if(TextUtils.isEmpty(setUsername)){
            Toast.makeText(Activity_profilo.this, "Inserire uno Username perfavore", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(setUsername)){
            Toast.makeText(Activity_profilo.this, "Inserire uno Stato perfavore", Toast.LENGTH_SHORT).show();
        }else{
            HashMap<String, String> profileMap = new HashMap<>();
            profileMap.put("uid", userID);
            profileMap.put("name", setUsername);
            profileMap.put("status", setStatus);

            rootReference.child("Users").child(userID).setValue(profileMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                SendUserToMain();
                                Toast.makeText(Activity_profilo.this, "Profilo aggiornato con successo", Toast.LENGTH_SHORT);
                            }else{
                                String messageError = task.getException().toString();
                                Toast.makeText(Activity_profilo.this, "ATTENZIONE: Qualcosa è andato storto" + messageError, Toast.LENGTH_LONG);
                            }
                        }
                    });
        }
    }
    private void SendUserToMain(){
        Intent mainIntent = new Intent(Activity_profilo.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void saveUserInof(){
        rootReference.child("Users").child(userID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("name") && (dataSnapshot.hasChild("image")))){
                            String usernameSalvato = dataSnapshot.child("name").getValue().toString();
                            String statusSalvato = dataSnapshot.child("status").getValue().toString();
                            String imgProfiloSalvato = dataSnapshot.child("image").getValue().toString();

                            usernameScelto.setText(usernameSalvato);
                            status.setText(statusSalvato);
                        }else if((dataSnapshot.exists()) && (dataSnapshot.hasChild("name"))){
                            String usernameSalvato = dataSnapshot.child("name").getValue().toString();
                            String statusSalvato = dataSnapshot.child("status").getValue().toString();

                            //usernameScelto.setText(usernameSalvato);
                            username.setText(usernameSalvato);
                            username.setEnabled(false);
                            status.setText(statusSalvato);
                        }
                        else{
                            //username.setVisibility(View.VISIBLE);
                            Toast.makeText(Activity_profilo.this, "Aggiorna le tue informazioni perfavore", Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
