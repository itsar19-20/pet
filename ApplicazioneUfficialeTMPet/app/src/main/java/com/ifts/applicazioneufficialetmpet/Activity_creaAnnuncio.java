package com.ifts.applicazioneufficialetmpet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ifts.applicazioneufficialetmpet.interfaces.MyApiEndPointInterface;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ifts.applicazioneufficialetmpet.Activity_login.SHARED_PREFERENCE;

public class Activity_creaAnnuncio extends AppCompatActivity {

    VideoView vvVideoBackrgound;
    EditText etOrganizzatore,
            etNomeAnnuncio,
            etDescrizioneProprietario,
            etDate;

    String username;

    private static final String USERNAME = "username";
    FloatingActionButton btnSave;

    String urlImmagineAnnuncio;
    private CircleImageView ivProfile;

    Bitmap imageProfile = null;
    Date dataAnnuncio;
    //Date picker

    DatePickerDialog picker;
    Button btnGet;
    TextView tvw;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if (ContextCompat.checkSelfPermission(Activity_creaAnnuncio.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Activity_creaAnnuncio.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        username = sharedPref.getString(USERNAME, null);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_annuncio);
        //
        // etOrganizzatore = findViewById(R.id.edit_date);
        etNomeAnnuncio = findViewById(R.id.edit_title);

        etDescrizioneProprietario = findViewById(R.id.edit_text);
       // etOrganizzatore.setText(username);
        ivProfile = findViewById(R.id.ivProfile);

        btnSave = findViewById(R.id.fab_save);

        tvw= findViewById(R.id.textView1);

        etDate = (EditText) findViewById(R.id.edit_date);
        etDate.setInputType(InputType.TYPE_NULL);



        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(Activity_creaAnnuncio.this);
            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Activity_creaAnnuncio.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        btnGet=findViewById(R.id.buttonGetDate);
       btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvw.setText("Selected Date: "+ etDate.getText());

                try {
                   dataAnnuncio=formatter.parse(etDate.getText().toString());

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }



        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){

            super.onActivityResult(requestCode, resultCode, data);
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Uri resultUri = result.getUri();
            try {
                imageProfile = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
            } catch (IOException e) {
                e.printStackTrace();
            }


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageProfile.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            imageProfile = getResizedBitmap(imageProfile, 400);

            ivProfile.setImageBitmap(imageProfile);

            //Convert Bitmap to BASE64 Stdataring
            byte[] imageBytes = baos.toByteArray();
            String base64Image = Base64.encodeToString(imageBytes, Base64.DEFAULT);

            ApplicationWebService webService = (ApplicationWebService) getApplication();
            MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);
            RequestBody requestBody = RequestBody.create(MediaType.parse(base64Image), base64Image);
            Call<ResponseBody> call = apiService.setImage3(username, null, requestBody);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (!response.isSuccessful()) {
                        Toast.makeText(Activity_creaAnnuncio.this, "Problemi con il server" + response.message(), Toast.LENGTH_SHORT).show();

                    } else {
                        try {
                            urlImmagineAnnuncio = response.body().string();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Toast.makeText(Activity_creaEvento.this, "Il server funziona"+response.message(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(Activity_creaAnnuncio.this, "Il server funziona" + urlImmagineAnnuncio, Toast.LENGTH_SHORT).show();


                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(Activity_creaAnnuncio.this, "Problemi con il caricamento dell'immagine: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( etDate.getText().toString().length()<1){
                        Toast.makeText(Activity_creaAnnuncio.this, "Devi selezionare una data", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ApplicationWebService webService = (ApplicationWebService) getApplication();
                    MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);
                    apiService.setNewAnnouncement(etNomeAnnuncio.getText().toString(),username.toString(), etDescrizioneProprietario.getText().toString(), etDate.getText().toString(), urlImmagineAnnuncio = urlImmagineAnnuncio.substring(1, urlImmagineAnnuncio.length() - 1)).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            Toast.makeText(Activity_creaAnnuncio.this, "Annuncio salvato con successo", Toast.LENGTH_SHORT).show();
                            android.content.Intent toEventi = new Intent(Activity_creaAnnuncio.this, Eventi.class);
                            startActivity(toEventi);

                            finish();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(Activity_creaAnnuncio.this, "Errore salvataggio annuncio: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            });


        }

        //==============================METODI PER L'IMMAGINE===========================================================
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

