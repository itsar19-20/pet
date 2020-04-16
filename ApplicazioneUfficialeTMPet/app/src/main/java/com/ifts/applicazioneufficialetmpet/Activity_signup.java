package com.ifts.applicazioneufficialetmpet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ifts.applicazioneufficialetmpet.interfaces.MyApiEndPointInterface;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;

import java.io.ByteArrayOutputStream;
import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_signup extends AppCompatActivity {

    VideoView vvVideoBackrgound;
    EditText etNome,
            etCognome,
            etEmail,
            etControlloPassword,
            etPassword,
            etUsername;


    Button btnUpload,
            btnSignUP;

    ImageView ivProfile;

    Bitmap imageProfile = null;

    boolean controlloUploadImmagine = false;

    private static final CharSequence[] OPTIONS_UPLOAD = {"Take Photo", "Choose from Gallery", "Cancel"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initializeView();


        //SET BACKGROUND VIDEO
        vvVideoBackrgound = findViewById(R.id.vvBackground);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+ "/" + R.raw.cane_app);
        vvVideoBackrgound.setVideoURI(uri);
        vvVideoBackrgound.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        vvVideoBackrgound.start();

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = dialogUpload();
                dialog.show();
            }
        });

        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    registrazioneUtente(etNome.getText().toString(), etCognome.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), "proprietario");
            }
        });
    }







    public AlertDialog dialogUpload() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_signup.this);
        builder.setTitle("Aggiungi la tua foto profilo");
        builder.setItems(OPTIONS_UPLOAD, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (OPTIONS_UPLOAD[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                } else if (OPTIONS_UPLOAD[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else if (OPTIONS_UPLOAD[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog _return = builder.create();
        return _return;

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

           //ZOZZATA
            initializeView();
            controlloUploadImmagine = true;


            //SET BACKGROUND VIDEO
            vvVideoBackrgound = findViewById(R.id.vvBackground);
            Uri uri = Uri.parse("android.resource://"+getPackageName()+ "/" + R.raw.cane_app);
            vvVideoBackrgound.setVideoURI(uri);
            vvVideoBackrgound.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setLooping(true);
                }
            });
            vvVideoBackrgound.start();

            btnUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog dialog = dialogUpload();
                    dialog.show();
                }
            });



            //FINE ZOZZATA



            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        imageProfile = BitmapFactory.decodeFile(f.getAbsolutePath());
                        f.delete(); //OCCHIO
                        break;
                    }
                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                imageProfile = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of img gallery", picturePath + "");
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageProfile.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            imageProfile = getResizedBitmap(imageProfile, 400);

            ivProfile.setImageBitmap(imageProfile);


            //Convert Bitmap to BASE64 String
             byte[] imageBytes = baos.toByteArray();
             String base64Image = Base64.encodeToString(imageBytes, Base64.DEFAULT);

            btnSignUP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (controlloUploadImmagine) {
                        registrazioneImmagine(base64Image, etUsername.getText().toString());
                        registrazioneUtente(etNome.getText().toString(), etCognome.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), "proprietario");
                    } else {
                        registrazioneUtente(etNome.getText().toString(), etCognome.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), "proprietario");
                    }
                }
            });

        }

    }






    public void registrazioneUtente (String nome, String cognome, String username, String password, String email, String tipoUtente) {
        ApplicationWebService webService = (ApplicationWebService) getApplication();
        MyApiEndPointInterface apiService= webService.getRetrofit().create(MyApiEndPointInterface.class);
        apiService.signUpUser(nome, cognome, username, password, email, tipoUtente).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int responseCode = response.code();



                //Non so perchè risponde con un 500, bisogna sistemare!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
              /*
                if (responseCode == 200) {
                    String controlloUsernameEsistente = response.body();
                    if (controlloUsernameEsistente != null) {
                        Toast.makeText(Activity_signup.this, controlloUsernameEsistente, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Activity_signup.this, "Utente registrato con successo", Toast.LENGTH_SHORT).show();
                        sendUserToLogin();
                    }
                    if(responseCode != 200) {
                        Toast.makeText(Activity_signup.this, "Problemi con la registrazione utente, Response code: " + responseCode, Toast.LENGTH_SHORT).show();
                    }
                }
                */
                //ALTRA ZOZZATA
                String controlloUsernameEsistente = response.body();
                if (controlloUsernameEsistente != null) {
                    Toast.makeText(Activity_signup.this, controlloUsernameEsistente, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Activity_signup.this, "Utente registrato con successo", Toast.LENGTH_SHORT).show();
                    sendUserToLogin();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Activity_signup.this, "Problemi con la registrazione utente: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void registrazioneImmagine(String base64Image, String username) {
        ApplicationWebService webService = (ApplicationWebService) getApplication();
        MyApiEndPointInterface apiService= webService.getRetrofit().create(MyApiEndPointInterface.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse(base64Image), base64Image);
        apiService.setImage2(username, null,requestBody).enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                int responseCode = response.code();
                if (responseCode == 200) {
                    Toast.makeText(Activity_signup.this, "Immagine caricata con successo", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Activity_signup.this, "Problemi con il caricamento dell'immagine, Response code: " + responseCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {
                Toast.makeText(Activity_signup.this, "Problemi con il caricamento dell'immagine: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }







    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public void initializeView(){
        etPassword = findViewById(R.id.etPasswordRegistrazione);
        etNome = findViewById(R.id.etNomeRegistrazione);
        etCognome = findViewById(R.id.etCognomeRegistrazione);
        etUsername= findViewById(R.id.etUsernameRegistrazione);
        etControlloPassword = findViewById(R.id.etConfermaPasswordRegistrazione);
        etPassword = findViewById(R.id.etPasswordRegistrazione);
        etEmail = findViewById(R.id.etEmailRegistrazione);

        btnSignUP=findViewById(R.id.btnSignUp);
        btnUpload=findViewById(R.id.btnUpload);

        ivProfile=findViewById(R.id.ivProfile);

    }

    public void sendUserToLogin (){
        Intent toLogin = new Intent(Activity_signup.this, Activity_login.class);
        startActivity(toLogin);
        finish();
    }

}
