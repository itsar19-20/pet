package com.ifts.applicazioneufficialetmpet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

    //private FirebaseUser user;
    private FirebaseAuth auth;

    private EditText etUsername;
    private EditText etPassword;

    private Button btnLogin;

    private TextView passwordDimenticata;
    private TextView registrati;

    private ProgressDialog loadingBar;

    private static final String SHARED_PREF_USERNAME = "shared_pref_username";
    private static final String USERNAME = "username";

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

    }//l'ho aggiunta ma potrei toglierla nel caso tornassi al versione precedente

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
            String password = etPassword.getText().toString();


            if(etUsername.toString().isEmpty() && !etPassword.toString().isEmpty()){
                Toast.makeText(Activity_login.this, "Manca lo Username", Toast.LENGTH_LONG).show();
            }else
            if(etPassword.toString().isEmpty() && !etUsername.toString().isEmpty()){
                Toast.makeText(Activity_login.this, "Manca la Password", Toast.LENGTH_LONG).show();
            }else
            if(etPassword.toString().isEmpty() && etUsername.toString().isEmpty()){
                Toast.makeText(Activity_login.this, "Manca lo Username e la Password", Toast.LENGTH_LONG).show();
            }else
            {
                loadingBar.setTitle("Logging");
                loadingBar.setMessage("Please Wait...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                ApplicationWebService applicationWebService = (ApplicationWebService) getApplication();
                MyApiEndPointInterface apiInterface = applicationWebService.getRetrofit().create(MyApiEndPointInterface.class);
                apiInterface.getUser(etUsername.getText().toString(), etPassword.getText().toString()).enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        int statusCode = response.code();
                        if (statusCode == 200){

                            loadingBar.dismiss();
                            Toast.makeText(Activity_login.this, "Ti sei loggato con Successo!", Toast.LENGTH_LONG).show();
                            SharedPreferences sharedpref = getSharedPreferences(SHARED_PREF_USERNAME,MODE_PRIVATE);
                            sharedpref.edit().putString(USERNAME, etUsername.toString());
                            sharedpref.edit().commit();
                            sendUserToMain();
                        //Una volta aggiunto SQLite posso prendermi il model dalla Call<> e salvarlo
                        }
                        UserModel userModel = response.body();
                        if(userModel == null) {
                            Toast.makeText(Activity_login.this, "Username o Password sbagliati", Toast.LENGTH_LONG).show();
                            refresh();

                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        loadingBar.dismiss();
                        Toast.makeText(Activity_login.this, "Si è verificato un errore: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

/*
                auth.signInWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    SendUserToMain();
                                    loadingBar.dismiss();
                                    Toast.makeText(Activity_login.this, "Ti sei loggato con Successo!", Toast.LENGTH_LONG).show();
                                }else{
                                    String message = task.getException().toString();
                                    loadingBar.dismiss();
                                    Toast.makeText(Activity_login.this, "si è verificato un errore:" + message, Toast.LENGTH_LONG).show();

                                }
                            }
                        });
                // _return = true;
                */
            }
            // return _return;


        }

        private void sendUserToMain() {
        Intent mainIntent = new Intent(Activity_login.this, MainActivity.class);
        //mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void SendUserToRegistrazione() {
        Intent registrazioneIntent = new Intent(Activity_login.this, Activity_registrazione.class);
        startActivity(registrazioneIntent);
    }

    private void refresh(){
        Intent refresh = new Intent(Activity_login.this, Activity_login.class);
        startActivity(refresh);
        finish();
    }
}

