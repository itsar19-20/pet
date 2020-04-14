package com.ifts.applicazioneufficialetmpet;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_signup extends AppCompatActivity {

    VideoView vvVideoBackrgound;
    EditText etNome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

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
}
