package com.ifts.applicazioneufficialetmpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ifts.applicazioneufficialetmpet.interfaces.MyApiEndPointInterface;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_creaEvento extends AppCompatActivity {
    VideoView vvVideoBackrgound;
    EditText etOrganizzatore;
    EditText etNomeEvento;
    EditText etDescrizione;
    FloatingActionButton btnSave;
    Bitmap imageProfile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etOrganizzatore = findViewById(R.id.edit_author);
        etNomeEvento = findViewById(R.id.edit_title);
        etDescrizione =  findViewById(R.id.edit_text);
        btnSave = findViewById(R.id.fab_save);

        //DA GESTIRE L'EDIT
        // Intent i = getIntent();
        // if( i.getStringExtra("edit").equals("true")){}

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationWebService webService = (ApplicationWebService) getApplication();
                MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);
                apiService.setNewEvent(etNomeEvento.getText().toString(), etDescrizione.getText().toString(),etOrganizzatore.getText().toString()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(Activity_creaEvento.this, "Evento salvato con successo", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(Activity_creaEvento.this, "Errore salvataggio evento: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
//==============================SET BACKGROUND VIDEO===========================================================
        vvVideoBackrgound = findViewById(R.id.vvBackground);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cane_app);
        vvVideoBackrgound.setVideoURI(uri);
        vvVideoBackrgound.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        vvVideoBackrgound.start();
    }
}

