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

import com.ifts.applicazioneufficialetmpet.interfaces.MyApiEndPointInterface;
import com.ifts.applicazioneufficialetmpet.model.UserModel;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_login extends Activity {


    private EditText etUsername;
    private EditText etPassword;

    private Button btnLogin;

    private TextView tvPasswordDimenticata;
    private TextView tvRegistrati;

    private ProgressDialog loadingBar;

    public static final String SHARED_PREFERENCE = "shared_preference";
    private static final String USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeField();

        tvRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToRegistrazione();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowUserToLogin();
            }
        });

    }//l'ho aggiunta ma potrei toglierla nel caso tornassi al versione precedente

    private void initializeField(){
        etUsername = (EditText)findViewById(R.id.editText_user_name_login);
        etPassword = (EditText)findViewById(R.id.editText_password_login);

        btnLogin = (Button)findViewById(R.id.button_login_login);

        tvPasswordDimenticata = (TextView)findViewById(R.id.textView_password_dimenticata);
        tvRegistrati = (TextView)findViewById(R.id.textView_registrazione);
        loadingBar = new ProgressDialog(this);
    }


    private void allowUserToLogin(){

            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();


            if(etUsername.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()){
                Toast.makeText(Activity_login.this, "Manca lo Username", Toast.LENGTH_LONG).show();
            }else
            if(etPassword.getText().toString().isEmpty() && !etUsername.getText().toString().isEmpty()){
                Toast.makeText(Activity_login.this, "Manca la Password", Toast.LENGTH_LONG).show();
            }else
            if(etPassword.getText().toString().isEmpty() && etUsername.getText().toString().isEmpty()){
                Toast.makeText(Activity_login.this, "Manca lo Username e la Password", Toast.LENGTH_LONG).show();
            }else {
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
                            UserModel userModel = response.body();
                            if (userModel == null) {
                                Toast.makeText(Activity_login.this, "Username o Password sbagliati", Toast.LENGTH_LONG).show();
                                refresh();
                            }
                            else{
                            SharedPreferences sharedpref = getSharedPreferences(SHARED_PREFERENCE,MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpref.edit();
                            editor.putString(USERNAME, etUsername.getText().toString());
                            editor.commit();
                            sendUserToMain();
                            loadingBar.dismiss();
                        //Una volta aggiunto SQLite posso prendermi il model dalla Call<> e salvarlo
                        }
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

    private void sendUserToMain() {
        Intent mainIntent = new Intent(Activity_login.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    private void sendUserToRegistrazione() {
        Intent registrazioneIntent = new Intent(Activity_login.this, Activity_signup.class);
        startActivity(registrazioneIntent);
        finish();
    }

    private void refresh(){
        Intent refresh = new Intent(Activity_login.this, Activity_login.class);
        startActivity(refresh);
        finish();
    }
}

