package com.ifts.applicazioneufficialetmpet.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ifts.applicazioneufficialetmpet.Activity_creaAnnuncio;
import com.ifts.applicazioneufficialetmpet.Eventi;
import com.ifts.applicazioneufficialetmpet.R;
import com.ifts.applicazioneufficialetmpet.Swap;
import com.ifts.applicazioneufficialetmpet.interfaces.MyApiEndPointInterface;
import com.ifts.applicazioneufficialetmpet.model.AnnuncioModel;
import com.ifts.applicazioneufficialetmpet.model.PetSitter;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnuncioPageAdapter extends PagerAdapter {
    private List<AnnuncioModel> listaAnnunci;
    private LayoutInflater inflater;
    private Context context;
    private final String user;
    private final String tipo;
    ApplicationWebService applicationWebService;
    public static final String SHARED_PREFERENCE = "shared_preference";
    private static final String USERNAME = "username";
    private static final String TIPOUTENTE = "tipoUtente";
    SharedPreferences sharedPreferences;

    public AnnuncioPageAdapter(List<AnnuncioModel> list, Context context, ApplicationWebService applicationWebService) {
        this.listaAnnunci = list;
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        this.user = sharedPreferences.getString(USERNAME, null);
        this.tipo = sharedPreferences.getString(TIPOUTENTE, null);
        this.applicationWebService = applicationWebService;
    }

    @Override
    public int getCount() {
        return listaAnnunci.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.single_item_lista_annunci, container, false);

        //====================DIALOG per eliminare un preferito===================
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Sei sicuro?")
                        .setMessage("Vuoi cancellare questo evento?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if(tipo.contentEquals("proprietario")) {
                                    Toast.makeText(context, "Sei un proprietario", Toast.LENGTH_SHORT).show();
                                  rimuoviAnnuncio(listaAnnunci.get(position).getId_annuncio(),position);


                                }
                                else{
                                    Toast.makeText(context, "Non pui cacellare gli annunci creati dagli altri",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("NO", null).show();
                return true;
            }

        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Tipo " + tipo, Toast.LENGTH_LONG).show();
                if (tipo.contentEquals("petsitter")) {
                    setNewPetSitterPreferitiProprietario(listaAnnunci.get(position).getId_annuncio());

                } else if (tipo.contentEquals("proprietario")) {
                    setPopup(listaAnnunci.get(position).getId_annuncio());
                }
                ;
            }
        });

        ImageView ivImaggineAnnuncio = view.findViewById(R.id.image_annuncio);
        TextView tvDataAnnuncio = view.findViewById(R.id.textView_item_data_lista_annunci);
        TextView tvOrganizzatore = view.findViewById(R.id.textView_item_autore_lista_annunci);
        TextView tvNomeAnnuncio = view.findViewById(R.id.textView_item_titolo_lista_annunci);
        TextView tvDescrizione = view.findViewById(R.id.textView_item_testo_lista_annunci);


        if (listaAnnunci.get(position).getDataAnnuncio() != null) {

            //CONVERTO DATA IN STRINGA
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String strDate = dateFormat.format(listaAnnunci.get(position).getDataAnnuncio());
            // tvDataAnnuncio.setText(listaAnnunci.get(position).getDataAnnuncio().toLocaleString());
            tvDataAnnuncio.setText(strDate);
        }

        tvOrganizzatore.setText(listaAnnunci.get(position).getProprietario().getUsername());
        tvNomeAnnuncio.setText(listaAnnunci.get(position).getNomeAnnuncio());
        tvDescrizione.setText(listaAnnunci.get(position).getDescrizione());

        //UrlImmagine
        //if(listaAnnunci.get(position).getUrlImmagineAnnucio()!=null) {
        Picasso.get().load(listaAnnunci.get(position).getUrlImmagineAnnuncio()).placeholder(R.drawable.logoapppet)
                .error(R.drawable.logoapppet).into(ivImaggineAnnuncio);

        container.addView(view, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    // =====METODI====
    public void setPopup(int id_annuncio) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.popup_petsitter);
        MyApiEndPointInterface apiService = applicationWebService.getRetrofit().create(MyApiEndPointInterface.class);
        Call<List<PetSitter>> call = apiService.getPetSitter(id_annuncio);
        call.enqueue(new Callback<List<PetSitter>>() {
            @Override
            public void onResponse(Call<List<PetSitter>> call, Response<List<PetSitter>> response) {
        if(response.isSuccessful())
        {
            List<PetSitter> list = response.body();
            ViewPager viewPager = dialog.findViewById(R.id.viewpager_popup_petsitter);
            PetSitterPagerAdapter adapter = new PetSitterPagerAdapter(list, context, applicationWebService, id_annuncio);
            viewPager.setAdapter(adapter);
            dialog.show();
            dialog.setCanceledOnTouchOutside(true);

        }
        else
            Toast.makeText(context, "Problemi con il server", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<PetSitter>> call, Throwable t) {

            }
        });

    }

    public void setNewPetSitterPreferitiProprietario(int idAnnuncio) {
        MyApiEndPointInterface apiService = applicationWebService.getRetrofit().create(MyApiEndPointInterface.class);

        apiService.setNewPetSitterPreferiti(user, idAnnuncio).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                    Toast.makeText(context, "Annuncio aggiunto ai preferiti", Toast.LENGTH_SHORT).show();
                else if (response.code() == 500)
                    Toast.makeText(context, "L'annuncio è già in preferiti", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Non riesco a contattare il server.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    private void rimuoviAnnuncio(int idAnnuncioString, int position){
        MyApiEndPointInterface apiService = applicationWebService.getRetrofit().create(MyApiEndPointInterface.class);

        apiService.removeAnnouncement(idAnnuncioString).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


               listaAnnunci.remove(position);
               notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Problemi con la cancellazione dell'annuncio: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
