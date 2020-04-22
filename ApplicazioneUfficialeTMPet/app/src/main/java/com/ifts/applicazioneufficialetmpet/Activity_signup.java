package com.ifts.applicazioneufficialetmpet;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ifts.applicazioneufficialetmpet.interfaces.MyApiEndPointInterface;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_signup extends AppCompatActivity {

    private ProgressDialog creaUtente;

    private static final CharSequence[] OPTIONS_UPLOAD = {"Take Photo", "Choose from Gallery", "Cancel"};
    VideoView vvVideoBackrgound;
    EditText etNome,
            etCognome,
            etEmail,
            etControlloPassword,
            etPassword,
            etUsername;

    Button  btnSignUP;

    TextView btnPetsitter,
             btnProprietario,
             twBackToLogin;

   public Boolean checkStato = true;

    String tipoUtente;

    //ImageView ivProfile;
    private CircleImageView ivProfile;
    Bitmap imageProfile = null;
    boolean controlloUploadImmagine = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initializeView();
        if (ContextCompat.checkSelfPermission(Activity_signup.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Activity_signup.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }




//================================CLICK SULLA SCELTA IMMAGINE PROFILO==============================
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = dialogUpload();
                dialog.show();
            }
        });
        btnPetsitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPetsitter.setBackgroundColor(getResources().getColor(R.color.colorVerdeApp));
                btnPetsitter.setTextColor(getResources().getColor(R.color.white));
                btnProprietario.setBackgroundColor(getResources().getColor(R.color.colorGrayWhatsApp));
                btnProprietario.setTextColor(getResources().getColor(R.color.black));
                checkStato = true;
            }
        });
        btnProprietario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnProprietario.setBackgroundColor(getResources().getColor(R.color.colorVerdeApp));
                btnProprietario.setTextColor(getResources().getColor(R.color.white));
                btnPetsitter.setBackgroundColor(getResources().getColor(R.color.colorGrayWhatsApp));
                btnPetsitter.setTextColor(getResources().getColor(R.color.black));
                checkStato = false;
            }
        });
        twBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToLogin();
            }
        });

        btnSignUP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                creaUtente.setTitle("Logging");
//                creaUtente.setMessage("Please Wait...");
//                creaUtente.setCanceledOnTouchOutside(false);
//                creaUtente.show();
                //verificaTipoUtente();
                if(checkStato) {
                    registrazioneUtente(etNome.getText().toString(), etCognome.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), "petsitter");
//                    creaUtente.dismiss();
                }else{
                    registrazioneUtente(etNome.getText().toString(), etCognome.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), "proprietario");
//                    creaUtente.dismiss();
                }
            }
        });


    }

    //=======================================GESTIONE PERMESSI ========================================
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }
//=======================================DIALOG PER LA SCELTA DELL'IMMAGINE========================================

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

//========================================VERIFICA L'IMMAGINE NEL DB======================================
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //ZOZZATA   PERCHè RIINIZIALIZZI??
           // initializeView();
            controlloUploadImmagine = true;
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
            } else if (requestCode == 2 ) {
                Uri selectedImage = data.getData();
                //=======================inizio prova crop========================================
               CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);
                CropImage.ActivityResult result = CropImage.getActivityResult(data);

//============================fine prova crop=================================================

                   // Uri resultUri = result.getUri();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
               // Cursor c = getContentResolver().query(resultUri, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                imageProfile = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of img gallery", picturePath + "");
               // imageProfile = (BitmapFactory.decodeFile(String.valueOf(result)));
              //  Log.w("path of img gallery", result + "");
            }


               /* if (resultCode == RESULT_OK){
                    /*loadingBar.setTitle("set profile img");
                    loadingBar.setMessage("pls wait the uploading");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();
                    Uri resultUri = result.getUri();*/

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
                  //  creaUtente.setTitle("Logging");
                  //  creaUtente.setMessage("Please Wait...");
                  //  creaUtente.setCanceledOnTouchOutside(false);
                  //  creaUtente.show();
                    //verificaTipoUtente();
                    if (controlloUploadImmagine) {
                        if (checkStato){
                        registrazioneUtente(etNome.getText().toString(), etCognome.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), "petsitter");
                        registrazioneImmagine(base64Image, etUsername.getText().toString());
                        creaUtente.dismiss();
                        }else{
                            registrazioneUtente(etNome.getText().toString(), etCognome.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), "proprietario");
                            registrazioneImmagine(base64Image, etUsername.getText().toString());
                           // creaUtente.dismiss();
                        }
                    } else {
                        if(checkStato) {
                            registrazioneUtente(etNome.getText().toString(), etCognome.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), "petsitter");
                            //creaUtente.dismiss();
                        }else{
                            registrazioneUtente(etNome.getText().toString(), etCognome.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), "proprietario");
                           // creaUtente.dismiss();
                        }
                    }
                }
            });

        }

    }


    public void registrazioneUtente(String nome, String cognome, String username, String password, String email, String tipoUtente) {
        ApplicationWebService webService = (ApplicationWebService) getApplication();
        MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);
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
        MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse(base64Image), base64Image);
        apiService.setImage2(username, null, requestBody).enqueue(new Callback<RequestBody>() {
            @Override
            //aggiungere una response lato server
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
                // da sistemare
                //Toast.makeText(Activity_signup.this, "Problemi con il caricamento dell'immagine: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

//=============================METODI RIUTILIZZATI========================================
    //richiamo alla riga 65
    public void initializeView() {
        etPassword = (EditText) findViewById(R.id.etPasswordRegistrazione);
        etNome = (EditText) findViewById(R.id.etNomeRegistrazione);
        etCognome = (EditText) findViewById(R.id.etCognomeRegistrazione);
        etUsername = (EditText) findViewById(R.id.etUsernameRegistrazione);
        etControlloPassword = (EditText) findViewById(R.id.etConfermaPasswordRegistrazione);
        etPassword = (EditText) findViewById(R.id.etPasswordRegistrazione);
        etEmail = (EditText) findViewById(R.id.etEmailRegistrazione);

        btnSignUP = (Button) findViewById(R.id.btnSignUp);
        btnPetsitter = (TextView) findViewById(R.id.button_petsitter);
        btnProprietario = (TextView) findViewById(R.id.button_proprietario);
      //  btnUpload = findViewById(R.id.btnUpload);

        twBackToLogin = (TextView) findViewById(R.id.textView_backToLogin);

        ivProfile = findViewById(R.id.ivProfile);

        creaUtente = new ProgressDialog(this);

    }

    public void sendUserToLogin() {
        Intent toLogin = new Intent(Activity_signup.this, Activity_login.class);
        startActivity(toLogin);
        finish();
    }

    /*public void verificaTipoUtente(){
        if(checkStato == true){
            tipoUtente = "petsitter";
        }else {
            tipoUtente = "proprietario";
        }
    }*/

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
