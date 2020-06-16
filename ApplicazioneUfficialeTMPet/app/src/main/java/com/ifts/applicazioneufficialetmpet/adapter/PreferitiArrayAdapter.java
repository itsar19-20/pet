package com.ifts.applicazioneufficialetmpet.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ifts.applicazioneufficialetmpet.R;
import com.ifts.applicazioneufficialetmpet.model.AnnuncioModel;
import com.ifts.applicazioneufficialetmpet.model.EventoModel;
import com.ifts.applicazioneufficialetmpet.model.PreferitoModel;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PreferitiArrayAdapter extends ArrayAdapter<PreferitoModel> {
    private List<PreferitoModel> listaPreferiti;
    ApplicationWebService applicationWebService;
    private final String user;
    private final String tipo;

    public static final String SHARED_PREFERENCE = "shared_preference";
    private static final String USERNAME = "username";
    private static final String TIPOUTENTE = "tipoUtente";
    SharedPreferences sharedPreferences;

    public PreferitiArrayAdapter(Context context,int resource, @NonNull List<PreferitoModel> listaPreferiti) {
        super(context, 0, listaPreferiti);
        this.listaPreferiti= listaPreferiti;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        this.user = sharedPreferences.getString(USERNAME, null);
        this.tipo = sharedPreferences.getString(TIPOUTENTE, null);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PreferitoModel preferito = listaPreferiti.get(position);

        View view = convertView;


            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.single_item_lista_preferiti, parent, false);
            }

            ImageView ivImaggineEvento = view.findViewById(R.id.set_image_eventi);
            ImageView ivImaggineUtente = view.findViewById(R.id.set_image_utente);

            TextView tvNomeAnnuncio = view.findViewById(R.id.textView_NomeEvento);
            TextView tvUtente = view.findViewById(R.id.textView_singleItemLista_UsernameUtente);
            TextView tvOrganizzatore = view.findViewById(R.id.textView_item_autore_lista_eventi);
            TextView tvDescrizione = view.findViewById(R.id.textView_item_testo_lista_eventi);
            TextView tvDataEvento = view.findViewById(R.id.textView_item_data_lista_eventi);

            Picasso.get().load(preferito.getAnnuncioPreferito().getUrlImmagineAnnuncio()).placeholder(R.drawable.logoapppet).

            error(R.drawable.logoapppet).into(ivImaggineEvento);
                //Controllare tipo utente
                if (tipo.contentEquals("petsitter")) {
                    Picasso.get().load(preferito.getAnnuncioPreferito().getProprietario().getImmagineProfilo().getUrlImmagine()).placeholder(R.drawable.logoapppet).
                            error(R.drawable.logoapppet).into(ivImaggineUtente);
                    tvUtente.setText(preferito.getAnnuncioPreferito().getProprietario().getUsername());

                } else if (tipo.contentEquals("proprietario")) {
                    Picasso.get().load(preferito.getPreferitoDelPetSitter().getImmagineProfilo().getUrlImmagine()).placeholder(R.drawable.logoapppet)
                            .error(R.drawable.logoapppet).into(ivImaggineUtente);
                    tvUtente.setText(preferito.getPreferitoDelPetSitter().getUsername());
                }

                tvDescrizione.setText(preferito.getAnnuncioPreferito().getDescrizione());


                tvNomeAnnuncio.setText(preferito.getAnnuncioPreferito().getNomeAnnuncio());
                tvOrganizzatore.setText(preferito.getAnnuncioPreferito().getProprietario().getUsername());
                //CONVERTO DATA IN STRINGA
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(preferito.getAnnuncioPreferito().getDataAnnuncio());
                tvDataEvento.setText(strDate);

                if (preferito.getPetSitterPreferitoDelProprietario()!= null) {
                    view.setVisibility(View.GONE);
                    listaPreferiti.remove(position);

        }
        return view;

    }
    }

