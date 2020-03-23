package com.ifts.applicazioneufficialetmpet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_registrazione extends AppCompatActivity {

    //private EditText usernameRegistrazione;

    private DatabaseReference rootReference;


    private EditText passwordRegistrazione;
    private EditText mailRegistrazione;
    private Button registrati;

    private TextView tornaLogin;

    private FirebaseAuth auth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        auth = FirebaseAuth.getInstance();
        rootReference = FirebaseDatabase.getInstance().getReference();

        InitializeField();

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });

        tornaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToLogin();
            }
        });
    }


    private void InitializeField() {
       // usernameRegistrazione = (EditText)findViewById(R.id.editText_username_registrazione);
        passwordRegistrazione = (EditText)findViewById(R.id.editText_password_registrazione);
        mailRegistrazione = (EditText)findViewById(R.id.editText_mail_registrazione);
        registrati = (Button)findViewById(R.id.button_registrazione_registrazione);
        tornaLogin = (TextView)findViewById(R.id.textView_already_have_account);

        loadingBar = new ProgressDialog(Activity_registrazione.this);
    }





  private void CreateNewAccount(){
      //Boolean _return = false;
      //String username = usernameRegistrazione.getText().toString();
      String password = passwordRegistrazione.getText().toString();
      String mail = mailRegistrazione.getText().toString();


       if(TextUtils.isEmpty(mail) && !TextUtils.isEmpty(password)){
          Toast.makeText(Activity_registrazione.this, "Manca la Mail", Toast.LENGTH_LONG).show();
      }else
      if(TextUtils.isEmpty(password) && !TextUtils.isEmpty(mail)){
          Toast.makeText(Activity_registrazione.this, "Manca la Password", Toast.LENGTH_LONG).show();
      }else
      if (TextUtils.isEmpty(password) && TextUtils.isEmpty(mail)){
          Toast.makeText(Activity_registrazione.this, "Riempire tutti i campi", Toast.LENGTH_LONG).show();
      }   else
     /* if (username.length() > 10 ){
          Toast.makeText(Activity_registrazione.this, "lo Username è troppo lungo, al massimo 10 caratteri!", Toast.LENGTH_LONG).show();
      }else*/
      if (mail.length() < 2 && password.length() < 2) {
          Toast.makeText(Activity_registrazione.this, "la Mail e la Password sono troppo corti", Toast.LENGTH_LONG).show();
      }else
      if (mail.length() < 2 && password.length() > 2) {
          Toast.makeText(Activity_registrazione.this, "la Mail è troppo corta", Toast.LENGTH_LONG).show();
      }else
      if (password.length() < 2 && mail.length() > 2) {
          Toast.makeText(Activity_registrazione.this, "la password è troppo corta", Toast.LENGTH_LONG).show();
      }else
      {
          loadingBar.setTitle("Creating new Account");
          loadingBar.setMessage("Please Wait...");
          loadingBar.setCanceledOnTouchOutside(false);
          loadingBar.show();

            auth.createUserWithEmailAndPassword(mail, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String userId = auth.getCurrentUser().getUid();
                            rootReference.child("Users").child(userId).setValue("");
                            SendUserToMain();
                            loadingBar.dismiss();
                            Toast.makeText(Activity_registrazione.this, "Account creato con Successo!", Toast.LENGTH_LONG).show();
                        }else{
                            String message = task.getException().toString();
                            loadingBar.dismiss();
                            Toast.makeText(Activity_registrazione.this, "si è verificato un errore:" + message, Toast.LENGTH_LONG).show();

                        }
                    }
                });
      }
  }

  private void SendUserToLogin(){
        Intent loginIntent = new Intent(Activity_registrazione.this, Activity_login.class);
        startActivity(loginIntent);
    }
    private void SendUserToMain() {
        Intent mainIntent = new Intent(Activity_registrazione.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }
}