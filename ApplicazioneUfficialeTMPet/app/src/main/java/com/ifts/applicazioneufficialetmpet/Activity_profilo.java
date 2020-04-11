package com.ifts.applicazioneufficialetmpet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class Activity_profilo extends AppCompatActivity {

    private CircleImageView imgProfilo;
    private static final int galleryPick = 1;
    private StorageReference imgUserProfile;
    private ProgressDialog loadingBar;

    private EditText username;
    private EditText status;
    private Button update;
    private String mail;

    private String userID;
    private String mailID;
    private FirebaseAuth auth;
    private DatabaseReference rootReference;
    private Activity_registrazione activity_registrazione = new Activity_registrazione();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        auth = FirebaseAuth.getInstance();
        userID = auth.getCurrentUser().getUid();
        mailID = auth.getCurrentUser().getEmail();
        rootReference = FirebaseDatabase.getInstance().getReference();
       // mail = activity_registrazione.getProva();
        imgUserProfile = FirebaseStorage.getInstance().getReference().child("profile images");
      //  imgDBprofile = FirebaseDatabase.getInstance().getReference().child("image");

        initialize();
       // username.setVisibility(View.INVISIBLE);
        imgProfilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, galleryPick);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfilo();
            }
        });
        saveUserInfo();
    }

//=======================================INIZIALIZZAZIONE VARIABILI========================================================================
    public void initialize(){
        imgProfilo = (CircleImageView) findViewById(R.id.set_image_profilo);
        username = (EditText) findViewById(R.id.editText_username_profilo);
        status = (EditText) findViewById(R.id.editText_status_profilo);
        update = (Button) findViewById(R.id.button_update_profilo);
        //usernameScelto  = (TextView) findViewById(R.id.editText_username_profilo);
        loadingBar = new ProgressDialog(this);
    }

//========================================================IMPOSTARE L'IMMAGINE===============================================================

    //per prendere l'immagine
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == galleryPick && resultCode == RESULT_OK && data != null){
            Uri imgUri = data.getData();
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            //salvare l'immagine nello storage di firebase
            if (resultCode == RESULT_OK){
                loadingBar.setTitle("set profile img");
                loadingBar.setMessage("pls wait the uploading");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                Uri resultUri = result.getUri();
                final StorageReference storageReference = imgUserProfile.child(userID + ".jpg");
               // StorageReference storageReferenceProva =FirebaseStorage.getInstance().getReference();
               // final StorageReference photoReference= storageReferenceProva.child("profile images/" + userID + ".jpg");
                storageReference.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Activity_profilo.this, "the img has been uploaded", Toast.LENGTH_SHORT).show();
                           /* final long ONE_MEGABYTE =1024*1024;
                            storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>(){
                                                                                           @Override
                                                                                           public void onSuccess(byte[] bytes){
                                                                                               Bitmap bmp = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
                                                                                               imgProfilo.setImageBitmap(bmp);
                                                                                               loadingBar.dismiss();
                                                                                           }
                                                                                       }
                            ).addOnFailureListener(new OnFailureListener(){
                                @Override
                                public void onFailure(@NonNull Exception exception){
                                    Toast.makeText(getApplicationContext(),"No Such file or Path found!!",Toast.LENGTH_LONG).show();
                                }
                            });*/
                            //mettere l'immagine nel database dallo storage
                           // final String downloadUrl = task.getResult().getMetadata().getReference().getDownloadUrl().toString();
                           // rootReference.child("Users").child(userID).child("image").setValue(downloadUrl)
                            rootReference.child("Users").child(userID).child("image").setValue(userID + ".jpg")
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(Activity_profilo.this, "the img saved in DB", Toast.LENGTH_SHORT).show();
                                                loadingBar.dismiss();
                                            }else{
                                                String message = task.getException().toString();
                                                Toast.makeText(Activity_profilo.this, "ATTENZIONE:" + message, Toast.LENGTH_LONG).show();
                                                loadingBar.dismiss();
                                            }
                                        }
                                    });
                        }else{
                            String message = task.getException().toString();
                            Toast.makeText(Activity_profilo.this, "ATTENZIONE:" + message, Toast.LENGTH_LONG).show();
                            loadingBar.dismiss();
                        }
                    }
                });
            }
        }
    }


//===========================================SALVA DATI PROFILO==================================================================
//String count = "";
    private void saveUserInfo(){
        rootReference.child("Users").child(userID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("name") && (dataSnapshot.hasChild("image")))){
                            String usernameSalvato = dataSnapshot.child("name").getValue().toString();
                            String statusSalvato = dataSnapshot.child("status").getValue().toString();
                            String imgProfiloSalvato = dataSnapshot.child("image").getValue().toString();

                            username.setText(usernameSalvato);
                            username.setEnabled(false);
                            status.setText(statusSalvato);

                            //serve per salvare l'immagine profilo come sfondo
                            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                            StorageReference dateRef = storageRef.child("profile images/" + imgProfiloSalvato);

                            dateRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String profileImageUrl=task.getResult().toString();
                                    Picasso.with(getApplicationContext()).load(profileImageUrl).into(imgProfilo);
                                    // count = "1";
                                }
                            });


                        }/*else if((dataSnapshot.exists()) && (dataSnapshot.hasChild("name"))){
                            String usernameSalvato = dataSnapshot.child("name").getValue().toString();
                            String statusSalvato = dataSnapshot.child("status").getValue().toString();

                            username.setText(usernameSalvato);
                            username.setEnabled(false);
                            status.setText(statusSalvato);
                        }*/
                        else{
                            Toast.makeText(Activity_profilo.this, "Aggiorna le tue informazioni perfavore", Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

//===========================================UPDATE PROFILO==========================================================================

    public void updateProfilo(){
        String setUsername = username.getText().toString();
        String setStatus = status.getText().toString();
        //String setImg = imgProfilo.toString();

        /*if(TextUtils.isEmpty(setImg)){
            Toast.makeText(Activity_profilo.this, "Inserire uno Username perfavore", Toast.LENGTH_SHORT).show();
        }else*/
        if(TextUtils.isEmpty(setUsername)){
            Toast.makeText(Activity_profilo.this, "Inserire uno Username perfavore", Toast.LENGTH_SHORT).show();
        }else
        if(TextUtils.isEmpty(setStatus)){
            Toast.makeText(Activity_profilo.this, "Inserire uno Stato perfavore", Toast.LENGTH_SHORT).show();
        }else{
           // setImg= userID +".jpg";
            HashMap<String, String> profileMap = new HashMap<>();
            profileMap.put("uid", userID);
            profileMap.put("emil", mailID);
            profileMap.put("name", setUsername);
            profileMap.put("status", setStatus);
            profileMap.put("image", userID +".jpg");
           // profileMap.put("image", setImg);


            rootReference.child("Users").child(userID).setValue(profileMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                SendUserToMain();
                                Toast.makeText(Activity_profilo.this, "Profilo aggiornato con successo", Toast.LENGTH_SHORT);
                            }else{
                                String messageError = task.getException().toString();
                                Toast.makeText(Activity_profilo.this, "ATTENZIONE: Qualcosa è andato storto" + messageError, Toast.LENGTH_LONG);
                            }
                        }
                    });
        }
    }


    private void SendUserToMain(){
        Intent mainIntent = new Intent(Activity_profilo.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }
}
