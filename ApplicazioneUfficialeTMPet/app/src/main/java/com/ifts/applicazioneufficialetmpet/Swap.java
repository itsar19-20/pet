package com.ifts.applicazioneufficialetmpet;

import android.animation.ArgbEvaluator;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ifts.applicazioneufficialetmpet.adapter.AnnuncioArrayAdapter;
import com.ifts.applicazioneufficialetmpet.adapter.AnnuncioPageAdapter;
import com.ifts.applicazioneufficialetmpet.adapter.EventiArrayAdapter;
import com.ifts.applicazioneufficialetmpet.interfaces.MyApiEndPointInterface;
import com.ifts.applicazioneufficialetmpet.model.AnnuncioModel;
import com.ifts.applicazioneufficialetmpet.model.EventoModel;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.ifts.applicazioneufficialetmpet.Activity_login.SHARED_PREFERENCE;


public class Swap extends Fragment {
    private Button chiudi_annuncio;
    private TextView aggiungi_ai_preferiti;
    private TextView scarta_annuncio;
    private TextView visualizza_annuncio;
    private TextView crea_annuncio;
private ApplicationWebService applicationWebService;
    String TIPOUTENTE="tipoUtente",
            USERNAME="username";







    // private Dialog dialog_swap;
    private Dialog dialog_visualizza_annuncio;

    ViewPager viewPager;
    Slider_Adapter_Swap adapter_swap;
    List<Slider_Swap> sliders_swap;
    Slider_Adapter_Visualizza_Annuncio adapter_annuncio;
    List<Slider_Visualizza_Annunci> sliders_annunci;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    AnnuncioPageAdapter annuncioPageAdapter;
    List<AnnuncioModel> listaAnnunci;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        applicationWebService =(ApplicationWebService) getActivity().getApplication();
        SharedPreferences sharedPref = getActivity().getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
       String tipoUtente=sharedPref.getString(TIPOUTENTE,null);
       String proprietarioAnnuncio=sharedPref.getString(USERNAME,null);


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_swap, container, false);
        sliders_swap = new ArrayList<>();
        sliders_swap.add(new Slider_Swap(R.drawable.logoapppet));
        sliders_swap.add(new Slider_Swap(R.drawable.zampa_voto));
        sliders_swap.add(new Slider_Swap(R.drawable.zampa_voto2));

        adapter_swap = new Slider_Adapter_Swap(sliders_swap, getContext());


        viewPager = view.findViewById(R.id.viewPager_swap);
        viewPager.setPadding(130,0,130,0);


        //=========If per decidere quale annunci visualizare=======================
        if(tipoUtente.contentEquals("petsitter")) {
            setVisualizzaAnnunciPetsitter();

        }
        else if(tipoUtente.contentEquals("proprietario")){
            setVisualizzaAnnunciProprietario(proprietarioAnnuncio);

        }
        else {
            viewPager.setAdapter(adapter_swap);
                }




        Integer[] colors_temp = {
                getResources().getColor(R.color.colorVerdeApp),
                getResources().getColor(R.color.colorAzzurroApp),
                getResources().getColor(R.color.colorMarroneApp),
                getResources().getColor(R.color.colorRossoApp),
                getResources().getColor(R.color.white),
        };
        colors = colors_temp;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter_swap.getCount() - 1) && position < (colors.length - 1)){
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]));
                }else{
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }

                viewPager.setBackgroundColor(colors[4]);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
       // dialog_swap = new Dialog(getContext());
        dialog_visualizza_annuncio = new Dialog(getContext());
        visualizza_annuncio = view.findViewById(R.id.button_visualizza_annuncio);




//=============================Bottone crea annuncio===============================

        //====Nascondi bottone se l'utente è un PetSitter==========
        crea_annuncio = view.findViewById(R.id.textViewCreaAnnuncio);
        FloatingActionButton fab = view.findViewById(R.id.fab_add);

        if(tipoUtente.contentEquals("petsitter")) {
            fab.hide();
            crea_annuncio.setVisibility(View.GONE);

        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( getContext(), Activity_creaAnnuncio.class);
                i.putExtra("edit", "false");
                startActivity(i);
            }
        });


 //=======================================================================

        visualizza_annuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  dialog_swap.setContentView(R.layout.fragment_swap);
                dialog_visualizza_annuncio.setContentView(R.layout.visualizza_annuncio);

                //======================================INIZIO===================================================================
                sliders_annunci = new ArrayList<>();
                sliders_annunci.add(new Slider_Visualizza_Annunci(R.drawable.logoapppet));
                sliders_annunci.add(new Slider_Visualizza_Annunci(R.drawable.zampa_voto));
                sliders_annunci.add(new Slider_Visualizza_Annunci(R.drawable.zampa_voto2));

                adapter_annuncio = new Slider_Adapter_Visualizza_Annuncio(sliders_annunci, getContext());

              //  viewPager = dialog_swap.findViewById(R.id.viewPager_swap);
                viewPager = dialog_visualizza_annuncio.findViewById(R.id.viewPager_dialog_visualizza_annuncio);
                viewPager.setAdapter(adapter_annuncio);
                viewPager.setPadding(130,0,130,0);

                Integer[] colors_temp = {
                        getResources().getColor(R.color.colorVerdeApp),
                        getResources().getColor(R.color.colorAzzurroApp),
                        getResources().getColor(R.color.colorMarroneApp),
                        getResources().getColor(R.color.colorRossoApp)
                };
                colors = colors_temp;


                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        if (position < (adapter_annuncio.getCount() - 1) && position < (colors.length - 1)){
                            viewPager.setBackgroundColor(
                                    (Integer) argbEvaluator.evaluate(
                                            positionOffset,
                                            colors[position],
                                            colors[position + 1]));
                        }else{
                            viewPager.setBackgroundColor(colors[colors.length - 1]);
                        }


                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                //======================================FINE===================================================================
                dialog_visualizza_annuncio.show();
                aggiungi_ai_preferiti = dialog_visualizza_annuncio.findViewById(R.id.textView_preferiti_visualizza_annuncio);
                aggiungi_ai_preferiti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                scarta_annuncio = dialog_visualizza_annuncio.findViewById(R.id.textView_scarta_visualizza_annuncio);
                scarta_annuncio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_visualizza_annuncio.dismiss();
                    }
                });

                chiudi_annuncio = dialog_visualizza_annuncio.findViewById(R.id.textView_close_visualizza_annuncio);
                chiudi_annuncio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_visualizza_annuncio.dismiss();
                    }
                });
            }
        });
        return view;
    }

    //====================================Metodi=============================
    //==============================Call================================
    private void setVisualizzaAnnunciPetsitter(){
        ApplicationWebService webService = (ApplicationWebService) getActivity().getApplication();
        MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);
        apiService.getUserAnnouncement().enqueue(new Callback<List<AnnuncioModel>>() {
            @Override
            public void onResponse(Call<List<AnnuncioModel>> call, Response<List<AnnuncioModel>> response) {

                if (response.isSuccessful()) {
                    listaAnnunci = response.body();
                    AnnuncioPageAdapter annuncioPageAdapter = new AnnuncioPageAdapter(listaAnnunci, getContext(), applicationWebService);

                    viewPager.setAdapter(annuncioPageAdapter);
                   // Toast.makeText(getContext(), "Errore caricamento annunci: "+annuncioPageAdapter, Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<List<AnnuncioModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Errore caricamento annunci: " + t.getCause() + t.getStackTrace() + " " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                viewPager.setAdapter(adapter_swap);

            }
        });
    }

    private void setVisualizzaAnnunciProprietario(String username){
        ApplicationWebService webService = (ApplicationWebService) getActivity().getApplication();
        MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);
        apiService. getProprietarioAnnoucement(username).enqueue(new Callback<List<AnnuncioModel>>() {
            @Override
            public void onResponse(Call<List<AnnuncioModel>> call, Response<List<AnnuncioModel>> response) {

                if (response.isSuccessful()) {
                    listaAnnunci = response.body();
                    AnnuncioPageAdapter annuncioPageAdapter = new AnnuncioPageAdapter(listaAnnunci, getContext(), applicationWebService);

                    viewPager.setAdapter(annuncioPageAdapter);

                }
                else{
                    Toast.makeText(getContext(), "Chiamata non riuscita", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<AnnuncioModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Server offline" + t.getCause() + t.getStackTrace() + " " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                viewPager.setAdapter(adapter_swap);
            }
        });
    }
}

