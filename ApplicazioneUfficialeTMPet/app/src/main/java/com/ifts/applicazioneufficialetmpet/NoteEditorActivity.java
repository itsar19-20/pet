package com.ifts.applicazioneufficialetmpet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

import static com.ifts.applicazioneufficialetmpet.Activity_petsitter.arrayAdapter;
import static com.ifts.applicazioneufficialetmpet.Activity_petsitter.notes;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;
    EditText testo;
    String testo2;
    Button save;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        save = (Button) findViewById(R.id.btnSaveUpdate);
        delete = (Button) findViewById(R.id.btnRemove);
        testo = (EditText) findViewById(R.id.etNote) ;


        final EditText editText = (EditText) findViewById(R.id.etNote);
       // EditText editText = (EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      testo2 =  testo.getText().toString();
       editText.setText(testo2);
       SendUserToPetsitter();
    }
});
delete.setOnClickListener(new View.OnClickListener() {
    int i;
    final int itemToDelete = i;

    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(NoteEditorActivity.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are you sure?")
                .setMessage("Do you want to delete this note?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        testo.setText("");
                    }
                })
                .setNegativeButton("NO", null)
                .show();
    }
});
        noteId = intent.getIntExtra("noteId", -1);
            if (noteId != -1) {
                editText.setText(notes.get(noteId));
            }
            //viene aggiunta una nuova nota
            else{
                notes.add("");
                noteId = notes.size() -1;
                arrayAdapter.notifyDataSetChanged();
            }
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        notes.set(noteId, String.valueOf(charSequence));
                        arrayAdapter.notifyDataSetChanged();

                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.robpercival.notes", Context.MODE_PRIVATE);

                        HashSet<String> set = new HashSet(notes);
                        sharedPreferences.edit().putStringSet("notes", set).apply();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

    }
    public void SendUserToPetsitter(){
        Intent petsitterIntent = new Intent(NoteEditorActivity.this, Activity_petsitter.class);
        startActivity(petsitterIntent);
    }
}
