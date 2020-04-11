package com.ifts.applicazioneufficialetmpet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NotaAdapter {

    @SuppressWarnings("unused")
    private static final String LOG_TAG = NotaAdapter.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;
    private DatabaseOpenHelper dbHelper;
    // Database fields
    private static final String DATABASE_TABLE = "nota";
    public static final String KEY_NOTE_ID = "_id";
    public static final String KEY_DATE = "data";
    public static final String KEY_AUTHOR = "autore";
    public static final String KEY_TITLE = "titolo";
    public static final String KEY_TEXT = "testo";

    public NotaAdapter(Context context) {
        this.context = context;
    }

    public NotaAdapter open() throws SQLException {
        dbHelper = new DatabaseOpenHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String data, String autore, String titolo, String testo ) {
        ContentValues values = new ContentValues();
        values.put( KEY_DATE,  data );
        values.put( KEY_AUTHOR, autore );
        values.put( KEY_TITLE, titolo );
        values.put( KEY_TEXT, testo );
        return values;
    }
    //create note
    public long createNota(String data, String autore, String titolo, String testo) {
        ContentValues initialValues = createContentValues(data, autore, titolo, testo);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }
    //update note
    public boolean updateNota( long notaID, String data, String autore, String titolo, String testo ) {
        ContentValues updateValues = createContentValues(data, autore, titolo, testo);
        return database.update(DATABASE_TABLE, updateValues, KEY_NOTE_ID + "=" + notaID,
                null) > 0;
    }

    //delete note
    public boolean deleteNota(long notaID) {
        return database.delete(DATABASE_TABLE, KEY_NOTE_ID + "=" + notaID, null) > 0;
    }
    //fetch all notes
    public Cursor fetchAllNotes() {
        return database.query(DATABASE_TABLE, new String[] { KEY_NOTE_ID, KEY_DATE, KEY_AUTHOR,
                KEY_TITLE, KEY_TEXT}, null, null, null, null, null);
    }
    //fetch contacts filter by a string
    public Cursor fetchNotesByFilter(String autore, String titolo) {
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
                        KEY_NOTE_ID, KEY_DATE, KEY_AUTHOR, KEY_TITLE,
                        KEY_TEXT },
                KEY_AUTHOR + " like '%"+ autore + "%' AND " + KEY_TITLE + " like '%"+ titolo + "%'", null, null, null,
                null, null);
        return mCursor;
    }
}