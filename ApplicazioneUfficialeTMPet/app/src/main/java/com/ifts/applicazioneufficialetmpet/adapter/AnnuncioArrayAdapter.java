package com.ifts.applicazioneufficialetmpet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifts.applicazioneufficialetmpet.R;
import com.ifts.applicazioneufficialetmpet.model.AnnuncioModel;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AnnuncioArrayAdapter extends ArrayAdapter<AnnuncioModel> {
    public AnnuncioArrayAdapter(Context context, ArrayList<AnnuncioModel> listaAnnuncio) {
        super(context, 0, listaAnnuncio);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AnnuncioModel annuncio = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item_lista_annunci, parent, false);
        }

        ImageView ivImaggineAnnuncio=convertView.findViewById(R.id.set_image_annuncio);
        TextView tvDataAnnuncio = convertView.findViewById(R.id.textView_item_data_lista_eventi);
        TextView tvOrganizzatore = convertView.findViewById(R.id.textView_item_autore_lista_eventi);
        TextView tvNomeAnnuncio = convertView.findViewById(R.id.textView_item_titolo_lista_eventi);
        TextView tvDescrizione = convertView.findViewById(R.id.textView_item_testo_lista_eventi);



        //visualizzazione dell'immagine
        Picasso.get().load(annuncio.getUrlImmagineAnnuncio()).placeholder(R.drawable.logoapppet).error(R.drawable.logoapppet).into(ivImaggineAnnuncio);

        tvNomeAnnuncio.setText(annuncio.getNomeAnnuncio());
        tvDescrizione.setText(annuncio.getDescrizione());
        tvOrganizzatore.setText(annuncio.getUsernameProprietario());
        int adapterIdAnnuncio=annuncio.getId_annuncio();

        //CONVERTO DATA IN STRINGA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(annuncio.getDataAnnuncio());
        tvDataAnnuncio.setText(strDate);

        return convertView;
    }

    
}