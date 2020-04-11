package com.ifts.applicazioneufficialetmpet.list;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.ifts.applicazioneufficialetmpet.R;


public class NotaCursorAdapter extends CursorAdapter {

    public NotaCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        // when the view will be created for first time,
        // we need to tell the adapters, how each item will look
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.single_item_lista_eventi, parent, false);
        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // here we are setting our data
        // that means, take the data from the cursor and put it in views
        TextView textViewDate = (TextView) view.findViewById(R.id.textView_item_data_lista_eventi);
        textViewDate.setText(cursor.getString(cursor.getColumnIndex( cursor.getColumnName(1))));

        TextView textViewAuthor = (TextView) view.findViewById(R.id.textView_item_autore_lista_eventi);
        textViewAuthor.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));

        TextView textViewTitle = (TextView) view.findViewById(R.id.textView_item_titolo_lista_eventi);
        textViewTitle.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));

        TextView textViewTesto = (TextView) view.findViewById(R.id.textView_item_testo_lista_eventi);
        textViewTesto.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));
    }
}