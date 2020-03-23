package com.ifts.applicazioneufficialetmpet;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_petsitter extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petsitter);
        auth = FirebaseAuth.getInstance();

        toolbar = (Toolbar) findViewById(R.id.petsitter_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_switch);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new Swap()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.navigation_eventi:
                        fragment = new Eventi();
                        break;
                    case R.id.navigation_switch:
                        fragment = new Swap();
                        break;
                    case R.id.navigation_chat:
                        fragment = new Chat();
                        break;
                    case R.id.navigation_preferiti:
                        fragment = new Preferiti();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_petsitter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.profilo_menu){
            SendUserToProfile();
        }
        if (item.getItemId() == R.id.logout_menu){
            auth.signOut();
            SendUserToLogin();
        }
        return true;
    }

    private void SendUserToLogin(){
        Intent registrationIntent = new Intent(Activity_petsitter.this, Activity_login.class);
        startActivity(registrationIntent);
    }
    private void SendUserToProfile(){
        Intent profiloIntent = new Intent(Activity_petsitter.this, Activity_profilo.class);
        startActivity(profiloIntent);
    }
}


