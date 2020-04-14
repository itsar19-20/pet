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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
    btnSignUP=findViewById(R.id.btnUpload);

    ivProfile=findViewById(R.id.ivProfile);

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
        //Bitmap imageProfile = null;
        if (resultCode == RESULT_OK) {
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
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);


            //Retrofit

            ApplicationWebService webService = (ApplicationWebService) getApplication();
            MyApiEndPointInterface apiService= webService.getRetrofit().create(MyApiEndPointInterface.class);

            RequestBody requestBody = RequestBody.create(MediaType.parse(encodedImage), encodedImage);
            apiService.setImage2("pippo",null, requestBody).enqueue(new Callback<RequestBody>() {
                @Override
                public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                    int responseCode = response.code();
                    Toast.makeText(Activity_signup.this, "Response Code:    " + responseCode, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<RequestBody> call, Throwable t) {
                    Toast.makeText(Activity_signup.this, "FAILURE!", Toast.LENGTH_SHORT).show();
                }
            });

        }

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
}
