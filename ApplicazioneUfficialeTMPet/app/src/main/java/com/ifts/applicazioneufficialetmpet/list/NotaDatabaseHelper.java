package com.ifts.applicazioneufficialetmpet.list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ifts.applicazioneufficialetmpet.database.DatabaseOpenHelper;

public class NotaDatabaseHelper {

    private static final String TAG = NotaDatabaseHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bloccoNote.db";
    private static final String TABLE_NAME = "nota"; // Table name
    private static final String NOTE_TABLE_COLUMN_ID = "_id"; // a column named "_id" is required for cursor
    private static final String NOTE_TABLE_COLUMN_DATE = "data";
    private static final String NOTE_TABLE_COLUMN_AUTHOR = "autore";
    private static final String NOTE_TABLE_COLUMN_TITLE = "titolo";
    private static final String NOTE_TABLE_COLUMN_TEXT = "testo";

    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase database;

    public NotaDatabaseHelper(Context aContext) {
        openHelper = new DatabaseOpenHelper(aContext);
        database = openHelper.getWritableDatabase();
    }
    public void insertData (String data, String autore, String titolo, String testo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTE_TABLE_COLUMN_DATE, data);
        contentValues.put(NOTE_TABLE_COLUMN_AUTHOR, autore);
        contentValues.put(NOTE_TABLE_COLUMN_TITLE, titolo);
        contentValues.put(NOTE_TABLE_COLUMN_TEXT, testo);

        database.insert(TABLE_NAME, null, contentValues);
    }
    public Cursor getAllData () {
        String buildSQL = "SELECT * FROM " + TABLE_NAME;
        Log.d(TAG, "getAllData SQL: " + buildSQL);
        return database.rawQuery(buildSQL, null);
    }
}