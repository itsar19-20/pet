package com.ifts.applicazioneufficialetmpet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifts.applicazioneufficialetmpet.R;
import com.ifts.applicazioneufficialetmpet.model.EventoModel;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EventiArrayAdapter extends ArrayAdapter<EventoModel> {

    public EventiArrayAdapter(Context context, ArrayList<EventoModel> listaEventi) {
        super(context, 0, listaEventi);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventoModel evento = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item_lista_eventi, parent, false);
        }
        ImageView ivImaggineEvento=convertView.findViewById(R.id.set_image_profilo);
        TextView tvDataEvento = convertView.findViewById(R.id.textView_item_data_lista_eventi);
        TextView tvOrganizzatore = convertView.findViewById(R.id.textView_item_autore_lista_eventi);
        TextView tvNomeEvento = convertView.findViewById(R.id.textView_item_titolo_lista_eventi);
        TextView tvDescrizione = convertView.findViewById(R.id.textView_item_testo_lista_eventi);



        //visualizzazione dell'immagine
        Picasso.get().load(evento.getUrlImmagineEvento()).placeholder(R.drawable.logoapppet).error(R.drawable.logoapppet).into(ivImaggineEvento);

        tvNomeEvento.setText(evento.getNomeEvento());
        tvDescrizione.setText(evento.getDescrizione());
        tvOrganizzatore.setText(evento.getOrganizzatore().getUsername());
        int adapterIdEvento=evento.getId_Evento();

        //CONVERTO DATA IN STRINGA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(evento.getDataEvento());
        tvDataEvento.setText(strDate);

        return convertView;
    }
}
