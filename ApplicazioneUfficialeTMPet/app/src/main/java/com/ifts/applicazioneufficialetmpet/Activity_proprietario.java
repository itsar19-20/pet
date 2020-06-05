package com.ifts.applicazioneufficialetmpet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashSet;

public class Activity_proprietario extends AppCompatActivity {

    //===================inizio=================
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    //===================00fine====================
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView topNavigationView;

    private TextView titolo_chat;

    private FirebaseAuth auth;
    private String mailID;

    public static final String SHARED_PREFERENCE = "shared_preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprietario);
        //reciclerView = (RecyclerView) findViewById(R.id.provaR);
        // mailID = auth.getCurrentUser().getEmail();
        titolo_chat = (TextView) findViewById(R.id.textView_title_fragment_chat);
        //===================================INIZIO========================00
        final ListView listView = (ListView) findViewById(R.id.listView);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.robpercival.notes", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet) sharedPreferences.getStringSet("notes", null);
        if (set == null) {
            notes.add("example notes");
        }else{
            notes = new ArrayList(set);
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
                intent.putExtra("noteId", i);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int itemToDelete = i;

                new AlertDialog.Builder(Activity_proprietario.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                notes.remove(itemToDelete);
                                arrayAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.robpercival.notes", Context.MODE_PRIVATE);

                                HashSet<String> set = new HashSet(notes);
                                sharedPreferences.edit().putStringSet("notes", set).apply();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
                return true;
            }
        });

        //=============================FINE=======================================0

        auth = FirebaseAuth.getInstance();

        toolbar = (Toolbar) findViewById(R.id.proprietario_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_switch);
        topNavigationView = findViewById(R.id.nav_viewProva);
        topNavigationView.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.INVISIBLE);
        titolo_chat.setVisibility(View.INVISIBLE);



        // topNavigationView.setSelectedItemId(R.id.navigation_switch);

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
                        topNavigationView.setVisibility(View.INVISIBLE);
                        listView.setVisibility(View.INVISIBLE);
                        titolo_chat.setVisibility(View.INVISIBLE);

                        break;
                    case R.id.navigation_switch:
                        fragment = new Swap();
                        topNavigationView.setVisibility(View.INVISIBLE);
                        listView.setVisibility(View.INVISIBLE);
                        titolo_chat.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.navigation_chat:
                        fragment = new Chat();
                        topNavigationView.setVisibility(View.INVISIBLE);
                        listView.setVisibility(View.VISIBLE);
                        titolo_chat.setVisibility(View.VISIBLE);

                        break;
                    case R.id.navigation_preferiti:
                        fragment = new Preferiti();
                        topNavigationView.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.VISIBLE);
                        titolo_chat.setVisibility(View.INVISIBLE);
                        topNavigationView.setSelectedItemId(R.id.navigation_annunci_preferiti);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                return true;
            }
        });

        topNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.navigation_annunci_preferiti:
                        fragment = new Preferiti();
                        listView.setVisibility(View.VISIBLE);
                        titolo_chat.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.navigation_annunci_accettati:
                        fragment = new Accettati();
                        listView.setVisibility(View.INVISIBLE);
                        titolo_chat.setVisibility(View.INVISIBLE);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                return true;
            }
        });
    }

    /* @Override
     public boolean onCreateOptionsMenu(Menu menu){
         super.onCreateOptionsMenu(menu);
         getMenuInflater().inflate(R.menu.menu_petsitter, menu);
         return true;
     }*/
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_petsitter, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.profilo_menu){
            SendUserToProfile();
        }
        if (item.getItemId() == R.id.logout_menu){
            SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.clear().commit();
            sendUserToLogin();
        } if (item.getItemId() == R.id.add_people){
            SendUserToNewNote();
        }
        return true;
    }

    private void sendUserToLogin(){
        Intent registrationIntent = new Intent(Activity_proprietario.this, Activity_login.class);
        startActivity(registrationIntent);
    }
    private void SendUserToProfile(){
        Intent profiloIntent = new Intent(Activity_proprietario.this, Activity_profilo.class);
        startActivity(profiloIntent);
    }
    private void SendUserToNewNote(){
        Intent noteIntent = new Intent(Activity_proprietario.this, NoteEditorActivity.class);
        startActivity(noteIntent);
    }
}
