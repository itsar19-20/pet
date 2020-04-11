package com.ifts.applicazioneufficialetmpet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifts.applicazioneufficialetmpet.database.NotaAdapter;
import com.ifts.applicazioneufficialetmpet.list.NotaCursorAdapter;
import com.ifts.applicazioneufficialetmpet.list.NotaDatabaseHelper;

import java.util.Date;
import java.util.HashMap;

public class CreateActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCE = "shared_preference";
    public static int count = 0;
    private int contatore;
    private NotaAdapter dbHelper;
    private Cursor cursor;

    private NotaCursorAdapter customAdapter;
    private NotaDatabaseHelper databaseOpenHelper;

    EditText autore;
    EditText titolo;
    EditText testo;

    private DatabaseReference rootReference;
    private FirebaseAuth auth;
    private String userID;
    private String eventoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        autore = (EditText) findViewById(R.id.edit_author);
        titolo = (EditText) findViewById(R.id.edit_title);
        testo = (EditText) findViewById(R.id.edit_text);

        dbHelper = new NotaAdapter(this);
        dbHelper.open();

        databaseOpenHelper = new NotaDatabaseHelper(this);
        customAdapter = new NotaCursorAdapter( CreateActivity.this, databaseOpenHelper.getAllData());

        auth = FirebaseAuth.getInstance();
        rootReference = FirebaseDatabase.getInstance().getReference();
        final String userID = auth.getCurrentUser().getUid();
        final String eventoID = auth.getCurrentUser().getUid();


        Intent i = getIntent();
        if( i.getStringExtra("edit").equals("true")){

            int position = i.getIntExtra("position", -1);

            if( position > -1 ){
                //recupero i valori precedenti...
                Cursor cursor = (Cursor) customAdapter.getItem(position);

                String date = cursor.getString(1);
                String author = cursor.getString(2);
                String title = cursor.getString(3);
                String text = cursor.getString(4);

                autore.setText(author);
                titolo.setText(title);
                testo.setText(text);
            }
        }

        FloatingActionButton save = findViewById(R.id.fab_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date today = new Date();

                String date = today.toString();
                String author = autore.getText().toString();
                String title = titolo.getText().toString();
                String text = testo.getText().toString();

                long resultId = dbHelper.createNota( date, author, title, text );

                if( resultId < 0 ){

                    Toast.makeText(CreateActivity.this, "Errore!", Toast.LENGTH_LONG).show();

                }else{
                    //change activity
                    Intent i = new Intent( CreateActivity.this, MainActivity.class);
                    startActivity(i);
                    updateEvento();
                    saveData();
                    Toast.makeText(CreateActivity.this, "Salvato!", Toast.LENGTH_LONG).show();
                }

            }
        });
        loadData();
        updateData();
}
//==================================AGGIORNO I DATI DELL'EVENTO NEL FIREBASE===============================
    public void updateEvento(){
        count = count+1;
        String setAutore = autore.getText().toString();
        String setTitolo = titolo.getText().toString();
        String setTesto = testo.getText().toString();


        /*
         if(TextUtils.isEmpty(setImg)){
            Toast.makeText(Activity_profilo.this, "Inserire uno Username perfavore", Toast.LENGTH_SHORT).show();
        }else
        if(TextUtils.isEmpty(setUsername)){
            Toast.makeText(Activity_profilo.this, "Inserire uno Username perfavore", Toast.LENGTH_SHORT).show();
        }else
        if(TextUtils.isEmpty(setUsername)){
            Toast.makeText(Activity_profilo.this, "Inserire uno Stato perfavore", Toast.LENGTH_SHORT).show();
        }else{
        */
        auth = FirebaseAuth.getInstance();
        userID = auth.getCurrentUser().getUid();
            // setImg= userID +".jpg";
            HashMap<String, String> profileMap = new HashMap<>();
            profileMap.put("autore", setAutore);
            profileMap.put("titolo", setTitolo);
            profileMap.put("testo", setTesto);
            // profileMap.put("image", setImg);


            rootReference.child("Users").child(userID).child("Eventi").child(userID +"EVENTO"+ count).setValue(profileMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //SendUserToMain();
                            if (task.isSuccessful()) {
                                Toast.makeText(CreateActivity.this, "Profilo aggiornato con successo", Toast.LENGTH_SHORT).show();
                            } else{
                                String messageError = task.getException().toString();
                                Toast.makeText(CreateActivity.this, "ATTENZIONE: Qualcosa è andato storto" + messageError, Toast.LENGTH_LONG);
                            }
                        }
                    });
        }
//========================================SALVO IL CONTATORE DEGLI EVENTI=========================================
//sharedPreference
        public void saveData(){
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(String.valueOf(count), count);
            editor.apply();
            }
//========================================CARICO IL CONTATORE DEGLI EVENTI=========================================
public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        contatore = sharedPreferences.getInt(String.valueOf(count),count);
}
//========================================AGGIORNO IL CONTATORE DEGLI EVENTI=========================================
public void updateData(){
        count = contatore;
}
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();

    }
}
