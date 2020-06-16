package com.ifts.applicazioneufficialetmpet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.ifts.applicazioneufficialetmpet.interfaces.MyApiEndPointInterface;
import com.ifts.applicazioneufficialetmpet.model.UserModel;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Activity_login extends Activity {
    //shared preference to save user
    public static final String SHARED_PREFERENCE = "shared_preference";
    private static final String USERNAME = "username";
    private static final String NOME = "nome";
    private static final String COGNOME = "cognome";
    private static final String DESCRIZIONE = "descrizione";
    private static final String TIPOUTENTE="tipoUtente";
    private static final String URLIMMAGINE="urlImmagine";

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    private TextView passwordDimenticata;
    private TextView registrati;

    private ProgressDialog loadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitializeField();

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToRegistrazione();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowUserToLogin();
            }
        });

    }

    private void InitializeField(){
        etUsername = (EditText)findViewById(R.id.editText_user_name_login);
        etPassword = (EditText)findViewById(R.id.editText_password_login);

        btnLogin = (Button)findViewById(R.id.button_login_login);

        passwordDimenticata = (TextView)findViewById(R.id.textView_password_dimenticata);
        registrati = (TextView)findViewById(R.id.textView_registrazione);
        loadingBar = new ProgressDialog(this);
    }


    private void AllowUserToLogin(){

            String username = etUsername.getText().toString();
            final String mail = etUsername.getText().toString();
            String password = etPassword.getText().toString();


        if(TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
            Toast.makeText(Activity_login.this, "Manca lo Username", Toast.LENGTH_LONG).show();
        }else
        if(TextUtils.isEmpty(password) && !TextUtils.isEmpty(mail) && !TextUtils.isEmpty(username)){
            Toast.makeText(Activity_login.this, "Manca la Password", Toast.LENGTH_LONG).show();
        }else
        if(TextUtils.isEmpty(password) && !TextUtils.isEmpty(mail) && TextUtils.isEmpty(username)){
            Toast.makeText(Activity_login.this, "Manca lo Username e la Password", Toast.LENGTH_LONG).show();
        }else
        if (mail.length() < 2 && password.length() < 2) {
            Toast.makeText(Activity_login.this, "la Mail e la Password sono troppo corti", Toast.LENGTH_LONG).show();
        }else
        if (mail.length() < 2 && password.length() > 2) {
            Toast.makeText(Activity_login.this, "la Mail è troppo corta", Toast.LENGTH_LONG).show();
        }else
        if (password.length() < 2 && mail.length() > 2) {
            Toast.makeText(Activity_login.this, "la password è troppo corta", Toast.LENGTH_LONG).show();
        }else
            {
                loadingBar.setTitle("Logging");
                loadingBar.setMessage("Please Wait...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                ApplicationWebService applicationWebService = (ApplicationWebService) getApplication();
                MyApiEndPointInterface myApiEndPointInterface = applicationWebService.getRetrofit().create(MyApiEndPointInterface.class);
                myApiEndPointInterface.getUser(etUsername.getText().toString(), etPassword.getText().toString()).enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        int statusCode = response.code();
                        if (statusCode == 200) {
                            UserModel userModel = response.body();

                            if (userModel == null) {
                                Toast.makeText(Activity_login.this, "Username o password sbagliati", Toast.LENGTH_LONG).show();
                                refresh();
                            } else {
                                saveUserOnSharedPreference(userModel.getUsername(),userModel.getNome(),userModel.getCognome(),userModel.getDescrizione(),
                                        userModel.getTipoUtente(),userModel.getImmagineProfilo().getUrlImmagine());
                                loadingBar.dismiss();
                                Toast.makeText(Activity_login.this, "Ti sei loggato con Successo!", Toast.LENGTH_LONG).show();
                                Toast.makeText(Activity_login.this,"UrlImmagine"+userModel.getImmagineProfilo().getUrlImmagine() ,
                                        Toast.LENGTH_LONG).show();
                                System.out.println(userModel.getImmagineProfilo().getUrlImmagine());
                                sendUserToMain();}
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        loadingBar.dismiss();
                        Toast.makeText(Activity_login.this, "Si è verificato un errore: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }


    private void saveUserOnSharedPreference(String username, String nome, String cognome, String descrizione,
                                            String tipoUtente, String urlImmagine){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME,username);
        editor.putString(NOME,nome);
        editor.putString(COGNOME,cognome);
        editor.putString(DESCRIZIONE,descrizione);
        editor.putString(TIPOUTENTE, tipoUtente);
        editor.putString(URLIMMAGINE,urlImmagine);
        editor.apply();
    }

    private void sendUserToMain() {
        Intent mainIntent = new Intent(Activity_login.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    private void refresh() {
        Intent loginIntent = new Intent(Activity_login.this, Activity_login.class);
        startActivity(loginIntent);
        finish();
    }

    private void SendUserToRegistrazione() {
        Intent registrazioneIntent = new Intent(Activity_login.this, Activity_signup.class);
        startActivity(registrazioneIntent);
    }
}
