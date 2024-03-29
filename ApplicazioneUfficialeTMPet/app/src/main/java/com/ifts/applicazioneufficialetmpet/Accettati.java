package com.ifts.applicazioneufficialetmpet;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ifts.applicazioneufficialetmpet.adapter.PreferitiArrayAdapter;
import com.ifts.applicazioneufficialetmpet.interfaces.MyApiEndPointInterface;
import com.ifts.applicazioneufficialetmpet.model.PreferitoModel;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;

import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.ifts.applicazioneufficialetmpet.Activity_login.SHARED_PREFERENCE;

public class Accettati extends Fragment {
    private static final String USERNAME = "username",
            TIPOUTENTE="tipoUtente";
    private ApplicationWebService applicationWebService;
    private static final int ENTER_DATA_REQUEST_CODE = 1;
    private ListView listView;

    TextView tvNessunAccettato;
    ImageView ivImmagineCaneTriste;

    Slider_Adapter_Eventi adapter_eventi;
    ViewPager viewPager;

    List<PreferitoModel> listaPreferiti;
    //private FragmentActivity myContext;
    private BottomNavigationView topNavigationView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        applicationWebService =(ApplicationWebService) getActivity().getApplication();
        SharedPreferences sharedPref = getActivity().getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        String tipoUtente=sharedPref.getString(TIPOUTENTE,null);
        String username=sharedPref.getString(USERNAME,null);

        View view = inflater.inflate(R.layout.fragment_accettati, container, false);
         tvNessunAccettato = view.findViewById(R.id.textView_avviso_accettati);
         ivImmagineCaneTriste=view.findViewById(R.id.imgView_attesa_accettati);
        listView= view.findViewById(R.id.listView_preferiti);


        //=========If per decidere quale preferito da vsiualizzare in base al tipo dell'utente=======================
        if(tipoUtente.contentEquals("petsitter")) {
            setVisualizzaPreferitiPetsitter(username);

        }
        else if(tipoUtente.contentEquals("proprietario")){
            setVisualizzaPreferitiProprietario(username);

        }

//====================DIALOG per eliminare un preferito===================
        listView.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final int itemToDelete = position;
                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Sei sicuro?")
                        .setMessage("Vuoi cancellare questo evento?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                               // if(tipoUtente.contentEquals("petsitter")) {
                                    removePreferitoPetSitter(position);
                                    listView.invalidate();

                                /* }
                               else{
                                    Toast.makeText(getContext(), "Non pui cacellare i preferiti dei Pet Sitter", Toast.LENGTH_SHORT).show();
                                }

                                */
                            }
                        })
                        .setNegativeButton("NO", null).show();
                return true;
            }
        });


        //======VERSIONE PER FIREBASE=======================================
        // topNavigationView = container.findViewById(R.id.nav_view);
        // topNavigationView.setSelectedItemId(R.id.navigation_annunci_preferiti);

       /* if (savedInstanceState == null) {
            myContext.getSupportFragmentManager().beginTransaction().replace(R.id.container, new Swap()).commit();
        }*/

//        topNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                Fragment fragment = null;
//                switch (menuItem.getItemId()) {
//                    case R.id.navigation_annunci_preferiti:
//                        fragment = new Preferiti();
//                        break;
//                    case R.id.navigation_annunci_accettati:
//                        fragment = new Accettati();
//                        break;
//                }
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
//                return true;
//            }
//        });
        // Inflate the layout for this fragment
        return view;
    }
    //====================================Metodi=============================
    //==============================Call================================
    private void setVisualizzaPreferitiPetsitter(String usernamePetSitter) {
        ApplicationWebService webService = (ApplicationWebService) getActivity().getApplication();
        MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);
        apiService.getPetSitterPreferiti(usernamePetSitter).enqueue(new Callback<List<PreferitoModel>>() {
            @Override
            public void onResponse(Call<List<PreferitoModel>> call, Response<List<PreferitoModel>> response) {
                if (response.isSuccessful()) {
                    listaPreferiti=response.body();

                    ListIterator<PreferitoModel> i=listaPreferiti.listIterator();
                    while (i.hasNext()){
                        if (i.next().getPetSitterPreferitoDelProprietario()== null) {
                           i.remove();
                        }
                    }


 /*
                   //controlla che l'annuncio sia stato accettato
                    for(PreferitoModel p:listaPreferiti) {
                        if (p.getPetSitterPreferitoDelProprietario()== null) {
                            listaPreferiti.remove(p);

                        }
                    }
*/
                    // sel a lista è vuota rende visbile il testo e l'immagine
                    if(listaPreferiti.isEmpty()){
                        tvNessunAccettato.setVisibility(View.VISIBLE);
                        ivImmagineCaneTriste.setVisibility(View.VISIBLE);
                        return;
                    }
                    if(getActivity()!=null) {
                        PreferitiArrayAdapter preferitiArrayAdapter= new PreferitiArrayAdapter(getContext(),R.id.listView_preferiti,listaPreferiti);
                        listView.setAdapter(preferitiArrayAdapter);
                    }

                }


                else{
                    Toast.makeText(getContext(), "Chiamata non riuscita", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PreferitoModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Server offline" + t.getCause() + t.getStackTrace() + " " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setVisualizzaPreferitiProprietario(String usernameProprietario) {
        ApplicationWebService webService = (ApplicationWebService) getActivity().getApplication();
        MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);
        apiService.getProprietarioPreferiti(usernameProprietario).enqueue(new Callback<List<PreferitoModel>>() {
            @Override
            public void onResponse(Call<List<PreferitoModel>> call, Response<List<PreferitoModel>> response) {
                if (response.isSuccessful()) {
                    listaPreferiti=response.body();



                    //controlla che l'annuncio sia stato accettato
                    ListIterator<PreferitoModel> i=listaPreferiti.listIterator();
                    while (i.hasNext()){
                        if (i.next().getPetSitterPreferitoDelProprietario()== null) {
                            i.remove();
                        }
                    }

                    // sel a lista è vuota rende visbile il testo e l'immagine
                    if(listaPreferiti.isEmpty()){
                        tvNessunAccettato.setVisibility(View.VISIBLE);
                        ivImmagineCaneTriste.setVisibility(View.VISIBLE);
                        return;
                    }

                    if(getActivity()!=null) {
                        PreferitiArrayAdapter preferitiArrayAdapter = new PreferitiArrayAdapter(getContext(), R.id.listView_preferiti, listaPreferiti);
                        listView.setAdapter(preferitiArrayAdapter);
                    }

                }

                else{
                    Toast.makeText(getContext(), "Chiamata non riuscita", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<PreferitoModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Server offline" + t.getCause() + t.getStackTrace() + " " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void removePreferitoPetSitter(int position){
        ApplicationWebService webService = (ApplicationWebService) getActivity().getApplication();
        MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);
        PreferitoModel preferito= listaPreferiti.get(position);
        apiService.removePreferitoPetSitter(preferito.getId()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getContext(), "Hai tolro dai preferiti l'annuncio " + preferito.getAnnuncioPreferito().getNomeAnnuncio(), Toast.LENGTH_LONG).show();
                listaPreferiti.remove(position);
                PreferitiArrayAdapter preferitiArrayAdapter = new PreferitiArrayAdapter(getContext(), R.id.listView_preferiti, listaPreferiti);
                listView.setAdapter(preferitiArrayAdapter);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Problemi con la cancellazione dai preferiti dell'annuncio: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
