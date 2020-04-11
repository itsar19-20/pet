package com.ifts.applicazioneufficialetmpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Activity_scelta_start extends AppCompatActivity {
    private Button petsitter;
    private Button proprietario;
    private Button spiegaPetsitter;
    private Button spiegaProprietario;
    private TextView spiegazione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scelta_start);

        petsitter = (Button)findViewById(R.id.button_petsitter);
        proprietario = (Button)findViewById(R.id.button_proprietario);
        spiegaPetsitter = (Button)findViewById(R.id.button_spiega_petsitter);
        spiegaProprietario = (Button)findViewById(R.id.button_spiega_proprietario);
        spiegazione = (TextView)findViewById(R.id.textView_spiegazione);

        petsitter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivity_petsitter();
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

        spiegaPetsitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spiega_petsitter();
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
    public void openActivity_petsitter(){
        Intent intent = new Intent(Activity_scelta_start.this, Activity_petsitter.class);
        startActivity(intent);
    }

    public void openActivity_proprietario(){
        Intent intent = new Intent(Activity_scelta_start.this, Activity_proprietario.class);
        startActivity(intent);
    }
    public void spiega_petsitter(){
        //Snackbar
        Toast.makeText(Activity_scelta_start.this, "IL PET-SITTER SI PRENDE CURA DEGLI ANIMALI DEI PROPRIETARI, CREA E PARTECIPA AD EVNTI. PER MAGGIORI INFORMAZIONI CONSULTARE IL SITO 'TakeMyPetWEB'", Toast.LENGTH_LONG).show();

        // spiegazione.setText("IL PET-SITTER SI PRENDE CURA DEGLI ANIMALI DEI PROPRIETARI, CREA E PARTECIPA AD EVNTI. PER MAGGIORI INFORMAZIONI CONSULTARE IL SITO 'TakeMyPetWEB'");
    }

    public void spiega_proprietario(){
        Toast.makeText(Activity_scelta_start.this, "IL PROPRIETARIO METTE ANNUNCI PER IL MANTEIMENTO MOMENTANEO DEL SUO ANIMALE(QUALSIASI TIPO), CREA E PARTECIPA AD EVNTI. PER MAGGIORI INFORMAZIONI CONSULTARE IL SITO 'TakeMyPetWEB'", Toast.LENGTH_LONG).show();

        // spiegazione.setText("IL PET-SITTER SI PRENDE CURA DEGLI ANIMALI DEI PROPRIETARI, CREA E PARTECIPA AD EVNTI. PER MAGGIORI INFORMAZIONI CONSULTARE IL SITO 'TakeMyPetWEB'");
    }
}
