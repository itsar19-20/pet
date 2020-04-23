package com.ifts.applicazioneufficialetmpet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ifts.applicazioneufficialetmpet.interfaces.MyApiEndPointInterface;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ifts.applicazioneufficialetmpet.Activity_login.SHARED_PREFERENCE;

public class MainActivity extends AppCompatActivity {
    private Button btnStart;
    private ProgressBar cerchio;

    private static final String SHARED_PREF_USERNAME = "shared_pref_username";
    private static final String USERNAME = "username";
    private static final String TIPOUTENTE="tipoUtente";
    private String tipoUtente;
    private String loginUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cerchio = findViewById(R.id.progressBar_circle);
        btnStart = findViewById(R.id.button_start);

        btnStart.setVisibility(View.INVISIBLE);
        btnStart.postDelayed(new Runnable() {
            public void run() {
                btnStart.setVisibility(View.VISIBLE);
                cerchio.setVisibility(View.INVISIBLE);
            }
        }, 2000);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tipoUtente.contentEquals("proprietario")) {
                    openActivity_proprietario();
                }else {
                    openActivityPetSitter();
                }
            }
        });
    }

    protected void onStart() {
        super.onStart();
            verifyUser();
    }

    private void verifyUser(){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        String username = sharedPref.getString(USERNAME, null);
        String tipoUtente = sharedPref.getString(TIPOUTENTE, null);
        if(username != null) {
            ApplicationWebService applicationWebService = (ApplicationWebService) getApplication();
            MyApiEndPointInterface apiInterface =  applicationWebService.getRetrofit().create(MyApiEndPointInterface.class);
            apiInterface.getControlloBloccato(username).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    int statusCode = response.code();
                    if (statusCode == 200) {
                        String controllo = response.body();
                        if(controllo.contentEquals("ok")){
                            Toast.makeText(MainActivity.this, "welcome" + username, Toast.LENGTH_LONG).show();
                        } else if (controllo.contentEquals("bloccato")) {
                            Toast.makeText(MainActivity.this, "Il tuo account è stato bloccato, controlla la tua email per le info di sblocco", Toast.LENGTH_LONG);
                            sharedPref.edit().clear().commit();
                            sendUserToLogin();
                        } else if (controllo.contentEquals("disattivato")){
                            Toast.makeText(MainActivity.this, "Il tuo account è stato bloccato dai nostri admin. Scrivici per avere più informazioni: takemypetapp@gmail.com", Toast.LENGTH_LONG);
                            sharedPref.edit().clear().commit();
                            sendUserToLogin();
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Si è verificato un errore: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            sendUserToLogin();
            }

    }

    private void sendUserToLogin() {
        Intent loginIntent = new Intent(MainActivity.this, Activity_login.class);
       // loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
    private void SendUserToProfile(){
        Intent profiloIntent = new Intent(MainActivity.this, Activity_profilo.class);
        profiloIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(profiloIntent);
        finish();
    }
    public void openActivityPetSitter(){
        Intent intent = new Intent(MainActivity.this, Activity_petsitter.class);
        startActivity(intent);
        finish();
    }

    public void openActivity_proprietario(){
        Intent intent = new Intent(MainActivity.this, Activity_proprietario.class);
        startActivity(intent);
        finish();
    }
}


