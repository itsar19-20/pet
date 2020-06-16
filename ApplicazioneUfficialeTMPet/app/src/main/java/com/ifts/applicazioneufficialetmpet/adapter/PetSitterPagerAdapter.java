package com.ifts.applicazioneufficialetmpet.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.ifts.applicazioneufficialetmpet.R;
import com.ifts.applicazioneufficialetmpet.interfaces.MyApiEndPointInterface;
import com.ifts.applicazioneufficialetmpet.model.PetSitter;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetSitterPagerAdapter extends PagerAdapter {
    private List<PetSitter> listaPetSitter;
    private LayoutInflater inflater;
    private Context context;
    private final String user;
    private final String tipo;
    private final int id_annuncio;

    ApplicationWebService applicationWebService;
    public static final String SHARED_PREFERENCE = "shared_preference";
    private static final String USERNAME = "username";
    private static final String TIPOUTENTE = "tipoUtente";
    SharedPreferences sharedPreferences;


    public PetSitterPagerAdapter(List<PetSitter> list, Context context, ApplicationWebService applicationWebService, int id_annuncio) {
        this.listaPetSitter = list;
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        this.user = sharedPreferences.getString(USERNAME, null);
        this.tipo = sharedPreferences.getString(TIPOUTENTE, null);
        this.applicationWebService = applicationWebService;
        this.id_annuncio = id_annuncio;
    }


    @Override
    public int getCount() {
        return listaPetSitter.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_lista_petsitter, container, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {
                MyApiEndPointInterface apiService = applicationWebService.getRetrofit().create(MyApiEndPointInterface.class);
                Call<String> call = apiService.setNewPetSitterPreferitiProprietario(user,listaPetSitter.get(position).getUsername(),id_annuncio);
               call.enqueue(new Callback<String>() {
                   @Override
                   public void onResponse(Call<String> call, Response<String> response) {
                       if(!response.isSuccessful()) {
                           Toast.makeText(context, "Errore nel server " + response.message(), Toast.LENGTH_SHORT).show();
                       } else {
                           Toast.makeText(context, "PetSitter aggiunto ai preferiti", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onFailure(Call<String> call, Throwable t) {
                       Toast.makeText(context, "Impossibile collegari al server", Toast.LENGTH_LONG).show();
                   }
               });

            }
        });
        ImageView ivImagginePetSitter = view.findViewById(R.id.set_image_petsitter);

        TextView tvUsername = view.findViewById(R.id.textView_username);
        TextView tvNome = view.findViewById(R.id.textView_nome);
        TextView tvCognome = view.findViewById(R.id.textView_cognome);
        TextView tvDescrizione = view.findViewById(R.id.textView_descrizione);
        TextView tvEmail = view.findViewById(R.id.textView_email_listapetsitter);

        tvUsername.setText(listaPetSitter.get(position).getUsername());
        tvNome.setText(listaPetSitter.get(position).getNome());
        tvCognome.setText(listaPetSitter.get(position).getCognome());
        tvDescrizione.setText(listaPetSitter.get(position).getDescrizione());
        if (listaPetSitter.get(position).getEmails().size() > 0)
            tvEmail.setText(listaPetSitter.get(position).getEmails().get(0).getEmail());


        Picasso.get().load(listaPetSitter.get(position).getImmagineProfilo().getUrlImmagine()).placeholder(R.drawable.logoapppet).error(R.drawable.logoapppet).into(ivImagginePetSitter);
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

}
