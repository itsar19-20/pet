package com.ifts.applicazioneufficialetmpet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_scelta_start extends AppCompatActivity {
    private Button btnPetsitter;
    private Button proprietario;
    private Button btnSpiegaPetSitter;
    private Button spiegaProprietario;
    private TextView spiegazione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scelta_start);

        btnPetsitter = findViewById(R.id.button_petsitter);
        proprietario = findViewById(R.id.button_proprietario);
        btnSpiegaPetSitter = findViewById(R.id.button_spiega_petsitter);
        spiegaProprietario = findViewById(R.id.button_spiega_proprietario);
        spiegazione = findViewById(R.id.textView_spiegazione);

        btnPetsitter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivityPetSitter();
                finish();
            }
        });

        proprietario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivity_proprietario();
                finish();
            }
        });

        btnSpiegaPetSitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spiegaPetSitter();
                //finish();
            }
        });
        spiegaProprietario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spiega_proprietario();
                //finish();
            }
        });

    }
    public void openActivityPetSitter(){
        Intent intent = new Intent(Activity_scelta_start.this, Activity_petsitter.class);
        startActivity(intent);
    }

    public void openActivity_proprietario(){
        Intent intent = new Intent(Activity_scelta_start.this, Activity_proprietario.class);
        startActivity(intent);
    }
    public void spiegaPetSitter(){
        //Snackbar
        Toast.makeText(Activity_scelta_start.this, "IL PET-SITTER SI PRENDE CURA DEGLI ANIMALI DEI PROPRIETARI, CREA E PARTECIPA AD EVNTI. PER MAGGIORI INFORMAZIONI CONSULTARE IL SITO 'TakeMyPetWEB'", Toast.LENGTH_LONG).show();

        // spiegazione.setText("IL PET-SITTER SI PRENDE CURA DEGLI ANIMALI DEI PROPRIETARI, CREA E PARTECIPA AD EVNTI. PER MAGGIORI INFORMAZIONI CONSULTARE IL SITO 'TakeMyPetWEB'");
    }

    public void spiega_proprietario(){
        Toast.makeText(Activity_scelta_start.this, "IL PROPRIETARIO METTE ANNUNCI PER IL MANTEIMENTO MOMENTANEO DEL SUO ANIMALE(QUALSIASI TIPO), CREA E PARTECIPA AD EVNTI. PER MAGGIORI INFORMAZIONI CONSULTARE IL SITO 'TakeMyPetWEB'", Toast.LENGTH_LONG).show();

        // spiegazione.setText("IL PET-SITTER SI PRENDE CURA DEGLI ANIMALI DEI PROPRIETARI, CREA E PARTECIPA AD EVNTI. PER MAGGIORI INFORMAZIONI CONSULTARE IL SITO 'TakeMyPetWEB'");
    }
}
