package com.ifts.applicazioneufficialetmpet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ifts.applicazioneufficialetmpet.interfaces.MyApiEndPointInterface;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ifts.applicazioneufficialetmpet.Activity_login.SHARED_PREFERENCE;

public class Activity_profilo extends AppCompatActivity {

    private CircleImageView ivProfile;
    Bitmap imageProfile = null;
    private static final String USERNAME = "username";
    private static final String NOME = "nome";
    private static final String COGNOME = "cognome";
    private static final String DESCRIZIONE = "descrizione";
    private static final String TIPOUTENTE = "tipoUtente";
    private static final String IMGPROFILE = "immagineProfilo_id_Immagine";

    private static final int galleryPick = 1;
    private StorageReference imgUserProfile;
    private ProgressDialog loadingBar;

    public TextView twUsername;
    public TextView twName;
    public TextView twSurname;
    public TextView twUserType;
    public EditText etDescrizione;
    private Button update;
    private Button delete;
    private String mail;
    private Activity_registrazione activity_registrazione = new Activity_registrazione();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

       // imgUserProfile = FirebaseStorage.getInstance().getReference().child("profile images");
      //  imgDBprofile = FirebaseDatabase.getInstance().getReference().child("image");

        initialize();
        verifyUser();

        // username.setVisibility(View.INVISIBLE);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(Activity_profilo.this);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingBar.setTitle("set profile img");
                loadingBar.setMessage("pls wait the uploading");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                updateProfilo();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToLogin();
            }
        });
        saveUserInfo();
    }

//=======================================INIZIALIZZAZIONE VARIABILI========================================================================
    public void initialize(){
        ivProfile = (CircleImageView) findViewById(R.id.set_image_profilo);
        twUsername = (TextView) findViewById(R.id.textView_username_profilo);
        twName = (TextView) findViewById(R.id.textView_name_profilo);
        twSurname = (TextView) findViewById(R.id.textView_surname_profilo);
        twUserType = (TextView) findViewById(R.id.textView_userType_profilo);
        etDescrizione = (EditText) findViewById(R.id.editText_userStatus_profilo);

        update = (Button) findViewById(R.id.button_update_profilo);
        delete = (Button) findViewById(R.id.button_delete_profilo);
        loadingBar = new ProgressDialog(this);
    }

//========================================================IMPOSTARE L'IMMAGINE===============================================================

    //per prendere l'immagine
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // controlloUploadImmagine = true;
        CropImage.ActivityResult result = CropImage.getActivityResult(data);
        Uri resultUri = result.getUri();
        try {
            imageProfile = MediaStore.Images.Media.getBitmap(this.getContentResolver(),resultUri);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageProfile.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        imageProfile = getResizedBitmap(imageProfile, 400);

        ivProfile.setImageBitmap(imageProfile);

        //Convert Bitmap to BASE64 String
        byte[] imageBytes = baos.toByteArray();
        String base64Image = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
            }

//===========================================SALVA DATI PROFILO==================================================================
    private void saveUserInfo(){
    }

//===========================================UPDATE PROFILO==========================================================================

    public void updateProfilo(){

        String descrizione = etDescrizione.getText().toString();

            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(DESCRIZIONE,descrizione);
            editor.apply();
            loadingBar.dismiss();
            Toast.makeText(Activity_profilo.this, "Profilo Aggiornato", Toast.LENGTH_SHORT).show();
        }

public void registrazioneImmagine(String base64Image, String username) {
    ApplicationWebService webService = (ApplicationWebService) getApplication();
    MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);
    RequestBody requestBody = RequestBody.create(MediaType.parse(base64Image), base64Image);
    apiService.setImage2(username, null, requestBody).enqueue(new Callback<RequestBody>() {
        @Override
        public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
            // creaUtente.dismiss();
            Toast.makeText(Activity_profilo.this, "Immagine caricata con successo", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<RequestBody> call, Throwable t) {
            // creaUtente.dismiss();
            Toast.makeText(Activity_profilo.this, "Problemi con il caricamento dell'immagine: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(Activity_profilo.this, "ha scelto proprietario vero? ", Toast.LENGTH_SHORT).show();
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

    public void sendUserToMain(){
        Intent mainIntent = new Intent(Activity_profilo.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
       // finish();
    }
    public void sendUserToLogin(){
        Intent loginIntent = new Intent(Activity_profilo.this, Activity_login.class);
       // loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        // finish();
    }
    public void verifyUser() {
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        String username = sharedPref.getString(USERNAME, null);
        String name = sharedPref.getString(NOME, null);
        String surname = sharedPref.getString(COGNOME, null);
        String descrizione = sharedPref.getString(DESCRIZIONE, null);
        String userType = sharedPref.getString(TIPOUTENTE, null);

        etDescrizione.setText(descrizione);



        ApplicationWebService webService = (ApplicationWebService) getApplication();
        MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);

        apiService.getImage(twUsername.getText().toString()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    String urlImage = response.body();
                    if (urlImage != null) {
                        InputStream in = null;
                        try {
                            in = new java.net.URL(urlImage).openStream();
                        } catch (IOException e) {
                            Log.e("ERROR", e.getMessage());
                        }
                        imageProfile = BitmapFactory.decodeStream(in);
                        ivProfile.setImageBitmap(imageProfile);
                    }
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });

        twUsername.setText(username);
        twName.setText(name);
        twSurname.setText(surname);
        etDescrizione.setText(descrizione);
        twUserType.setText(userType);
       // ivProfile.setImageBitmap(imageProfile);

      /*  public void registrazioneUtente(String descrizione) {
            //questo viene richiamato al clik del btnSignUp
            loadingBar.setTitle("Logging");
            loadingBar.setMessage("Please Wait...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            ApplicationWebService webService = (ApplicationWebService) getApplication();
            MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);
            apiService.signUpUser(descrizione,).enqueue(new Callback<String>() {
                //questo viene fatto dopo la registrazione
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String controlloUsernameEsistente = response.body();
                    if (controlloUsernameEsistente != null) {
                        Toast.makeText(Activity_profilo.this, controlloUsernameEsistente, Toast.LENGTH_LONG).show();
                        if(imageProfile == null) {
                            loadingBar.dismiss();
                            sendUserToLogin();

                        }
                    } else {
                        loadingBar.dismiss();
                        Toast.makeText(Activity_profilo.this, "Utente registrato con successo", Toast.LENGTH_SHORT).show();
                    }
                }

            });}*/}}



