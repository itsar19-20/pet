package com.ifts.applicazioneufficialetmpet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class Activity_login extends Activity {

    //private FirebaseUser user;
    private FirebaseAuth auth;

    private EditText loginEmail;
    private EditText loginPassword;

    private Button login;

    private TextView passwordDimenticata;
    private TextView registrati;

    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
      //  user = auth.getCurrentUser();

        InitializeField();

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToRegistrazione();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowUserToLogin();
            }
        });

    }//l'ho aggiunta ma potrei toglierla nel caso tornassi al versione precedente

    private void InitializeField(){
        loginEmail = (EditText)findViewById(R.id.editText_mail_login);
        loginPassword = (EditText)findViewById(R.id.editText_password_login);

        login = (Button)findViewById(R.id.button_login_login);

        passwordDimenticata = (TextView)findViewById(R.id.textView_password_dimenticata);
        registrati = (TextView)findViewById(R.id.textView_registrazione);
        loadingBar = new ProgressDialog(this);
    }


    private void AllowUserToLogin(){

            String username = loginEmail.getText().toString();
            String mail = loginEmail.getText().toString();
            String password = loginPassword.getText().toString();


            if(TextUtils.isEmpty(username) && !TextUtils.isEmpty(mail) && !TextUtils.isEmpty(password)){
                Toast.makeText(Activity_login.this, "Manca lo Username", Toast.LENGTH_LONG).show();
            }else
            if(TextUtils.isEmpty(mail) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
                Toast.makeText(Activity_login.this, "Manca la Mail", Toast.LENGTH_LONG).show();
            }else
            if(TextUtils.isEmpty(password) && !TextUtils.isEmpty(mail) && !TextUtils.isEmpty(username)){
                Toast.makeText(Activity_login.this, "Manca la Password", Toast.LENGTH_LONG).show();
            }else
            if(TextUtils.isEmpty(username) && TextUtils.isEmpty(mail) && !TextUtils.isEmpty(password)){
                Toast.makeText(Activity_login.this, "Mancano lo Username e la Mail", Toast.LENGTH_LONG).show();
            }else
            if(TextUtils.isEmpty(mail) && !TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
                Toast.makeText(Activity_login.this, "Mancano la Mail e la Password", Toast.LENGTH_LONG).show();
            }else
            if(TextUtils.isEmpty(password) && !TextUtils.isEmpty(mail) && TextUtils.isEmpty(username)){
                Toast.makeText(Activity_login.this, "Manca lo Username e la Password", Toast.LENGTH_LONG).show();
            }else
            if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password) && TextUtils.isEmpty(mail)){
                Toast.makeText(Activity_login.this, "Riempire tutti i campi", Toast.LENGTH_LONG).show();
            }   else
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
            }
            // return _return;
        }

        private void SendUserToMain() {
        Intent mainIntent = new Intent(Activity_login.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void SendUserToRegistrazione() {
        Intent registrazioneIntent = new Intent(Activity_login.this, Activity_registrazione.class);
        startActivity(registrazioneIntent);
    }
}

