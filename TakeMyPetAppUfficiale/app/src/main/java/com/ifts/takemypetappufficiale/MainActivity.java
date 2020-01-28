package com.ifts.takemypetappufficiale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private ProgressBar cerchio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            cerchio = (ProgressBar)findViewById(R.id.progressBar_circle);
            start = (Button)findViewById(R.id.button_start);

            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivity_login();
                }
            });

        start.setVisibility(View.INVISIBLE);
        start.postDelayed(new Runnable() {
            public void run() {
                start.setVisibility(View.VISIBLE);
                cerchio.setVisibility(View.INVISIBLE);
            }
        }, 2000);
        }
    public void openActivity_login(){
        Intent intent = new Intent(MainActivity.this, Activity_login.class);
        startActivity(intent);
    }

}

