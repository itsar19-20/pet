package com.ifts.applicazioneufficialetmpet;

import android.animation.ArgbEvaluator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifts.applicazioneufficialetmpet.adapter.EventiArrayAdapter;
import com.ifts.applicazioneufficialetmpet.database.NotaAdapter;
import com.ifts.applicazioneufficialetmpet.interfaces.MyApiEndPointInterface;
import com.ifts.applicazioneufficialetmpet.list.NotaCursorAdapter;
import com.ifts.applicazioneufficialetmpet.list.NotaDatabaseHelper;
import com.ifts.applicazioneufficialetmpet.model.EventoModel;
import com.ifts.applicazioneufficialetmpet.retrofit.ApplicationWebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Eventi extends Fragment {
    private Button chiudi_evento;
    private Dialog dialog_visualizza_evento;
    //===========================INIZIO XD====================================
    private NotaAdapter notaAdapter;
    private NotaCursorAdapter customAdapter;
    private NotaDatabaseHelper databaseOpenHelper;
    private static final int ENTER_DATA_REQUEST_CODE = 1;
    private ListView listView;
    private static final String TAG = MainActivity.class.getSimpleName();
    //=============================FINE XD=============================

    //===================inizio=================
   // static ArrayList<String> notes = new ArrayList<>();
    //static ArrayAdapter arrayAdapter;
    //===================fine====================
    ViewPager viewPager;
    Slider_Adapter_Eventi adapter_eventi;
    ArrayList<Slider_Eventi> sliders_eventi;
    Slider_Adapter_Visualizza_Evento adapter_visualizza_eventi;
    List<Slider_Visualizza_Eventi> sliders_visualizza_eventi;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eventi, container, false);

//===================================INIZIO XD========================00

       //QUI SOSTITUISCO CON L'ARRAY ADAPTER DOPO AVER PRESO LA LISTA DAL SERVER!!
        listView = view.findViewById(R.id.listView_eventi);

        ApplicationWebService webService = (ApplicationWebService) getActivity().getApplication();
        MyApiEndPointInterface apiService = webService.getRetrofit().create(MyApiEndPointInterface.class);
        apiService.getAllEvents().enqueue(new Callback<List<EventoModel>>() {
            @Override
            public void onResponse(Call<List<EventoModel>> call, Response<List<EventoModel>> response) {
                List<EventoModel> listaEventi;
                listaEventi = response.body();
                EventiArrayAdapter arrayAdapter = new EventiArrayAdapter(getContext(),(ArrayList)listaEventi);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<EventoModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Errore caricamento eventi: "+t.getCause() + t.getStackTrace() + " "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               /* //change activity
                Intent i = new Intent( getContext(), CreateActivity.class);
                i.putExtra("edit", "true");
                i.putExtra("position", position);

                startActivity(i);*/
                dialog_visualizza_evento = new Dialog(getContext());

                dialog_visualizza_evento.setContentView(R.layout.visualizza_evento);
                //=============================INIZIO=========================================================0
                sliders_visualizza_eventi = new ArrayList<>();
                sliders_visualizza_eventi.add(new Slider_Visualizza_Eventi(R.drawable.logoapppet));
                sliders_visualizza_eventi.add(new Slider_Visualizza_Eventi(R.drawable.zampa_voto));
                sliders_visualizza_eventi.add(new Slider_Visualizza_Eventi(R.drawable.zampa_voto2));

                adapter_visualizza_eventi = new Slider_Adapter_Visualizza_Evento(sliders_visualizza_eventi, getContext());

                viewPager = dialog_visualizza_evento.findViewById(R.id.viewPager_dialog_visualizza_evento);
                viewPager.setAdapter(adapter_eventi);
                viewPager.setPadding(130,0,130,0);

                Integer[] colors_temp = {
                        getResources().getColor(R.color.colorVerdeApp),
                        getResources().getColor(R.color.colorAzzurroApp),
                        getResources().getColor(R.color.colorMarroneApp)
                };
                colors = colors_temp;
                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        if (position < (adapter_eventi.getCount() - 1) && position < (colors.length - 1)) {
                            viewPager.setBackgroundColor(
                                    (Integer) argbEvaluator.evaluate(
                                            positionOffset,
                                            colors[position],
                                            colors[position + 1]));
                        } else {
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
                //=======================================FINE====================================================
                dialog_visualizza_evento.show();
                chiudi_evento = dialog_visualizza_evento.findViewById(R.id.textView_close_visualizza_evento);
                chiudi_evento.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_visualizza_evento.dismiss();
                    }
                });
            }
        });

        //QUI BISOGNA SOSTITUIRE IL DELETE SU SQLITE CON UNA CHIAMATA DELETE AL SERVER


        listView.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final int itemToDelete = position;

                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                //memorizzo l'id dell'oggetto selezionato
                                Cursor cursor = (Cursor) customAdapter.getItem(position);
                                int notaId = cursor.getInt(0);

                                Boolean result = notaAdapter.deleteNota(notaId);

                                customAdapter.swapCursor(databaseOpenHelper.getAllData());
                                listView.invalidate();

                                if (result) {

                                    Toast.makeText(getContext(), "Hai eliminato l’elemento in posizione " + position, Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
                return true;
            }
        });

//======================CREA EVENTO==================================================================
        FloatingActionButton fab = view.findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( getContext(), Activity_creaEvento.class);
                i.putExtra("edit", "false");
                startActivity(i);
            }
        });
        //===================================FINE XD========================00
        //===================================INIZIO========================00

       /* ListView listView = view.findViewById(R.id.listView_eventi);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.robpercival.notes", Context.MODE_PRIVATE);
        HashSet<Integer> set = (HashSet) sharedPreferences.getStringSet("notes", null);
        if (set == null) {
            notes.add("example note");
            //notes.add(R.drawable.logoapppet);
        }else{
            notes = new ArrayList(set);
        }

        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, notes);
        //arrayAdapter = new ArrayAdapter(getContext(), R.layout.activity_item_style, notes);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), NoteEditorActivity.class);
                intent.putExtra("noteId", i);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int itemToDelete = i;

                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                notes.remove(itemToDelete);
                                arrayAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.robpercival.notes", Context.MODE_PRIVATE);

                                HashSet<String> set = new HashSet(notes);
                                sharedPreferences.edit().putStringSet("notes", set).apply();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
                return true;
            }
        });
*/
        //=============================FINE=======================================0

       /* sliders_eventi = new ArrayList<>();
        sliders_eventi.add(new Slider_Eventi(R.drawable.logoapppet));
        sliders_eventi.add(new Slider_Eventi(R.drawable.zampa_voto));
        sliders_eventi.add(new Slider_Eventi(R.drawable.zampa_voto2));

        adapter_eventi = new Slider_Adapter_Eventi(sliders_eventi, getContext());

        viewPager = view.findViewById(R.id.viewPager_fragment_eventi);
        viewPager.setAdapter(adapter_eventi);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.colorVerdeApp),
                getResources().getColor(R.color.colorAzzurroApp),
                getResources().getColor(R.color.colorMarroneApp)
        };
        colors = colors_temp;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter_eventi.getCount() - 1) && position < (colors.length - 1)){
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]));
                    visualizza_evento.setTextColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]));

                }else{
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                   // visualizza_evento.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });//DEVE FINIRE QUA


        dialog_visualizza_evento = new Dialog(getContext());
        visualizza_evento = view.findViewById(R.id.button_visualizza_evento);

        visualizza_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_visualizza_evento.setContentView(R.layout.visualizza_evento);
                //=============================INIZIO=========================================================0
                sliders_visualizza_eventi = new ArrayList<>();
                sliders_visualizza_eventi.add(new Slider_Visualizza_Eventi(R.drawable.logoapppet));
                sliders_visualizza_eventi.add(new Slider_Visualizza_Eventi(R.drawable.zampa_voto));
                sliders_visualizza_eventi.add(new Slider_Visualizza_Eventi(R.drawable.zampa_voto2));

                adapter_visualizza_eventi = new Slider_Adapter_Visualizza_Evento(sliders_visualizza_eventi, getContext());

                viewPager = dialog_visualizza_evento.findViewById(R.id.viewPager_dialog_visualizza_evento);
                viewPager.setAdapter(adapter_eventi);
                viewPager.setPadding(130,0,130,0);

                Integer[] colors_temp = {
                        getResources().getColor(R.color.colorVerdeApp),
                        getResources().getColor(R.color.colorAzzurroApp),
                        getResources().getColor(R.color.colorMarroneApp)
                };
                colors = colors_temp;
                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        if (position < (adapter_eventi.getCount() - 1) && position < (colors.length - 1)){
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
                //=======================================FINE====================================================
                dialog_visualizza_evento.show();
                chiudi_evento = dialog_visualizza_evento.findViewById(R.id.textView_close_visualizza_evento);
                chiudi_evento.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_visualizza_evento.dismiss();
                    }
                });
            }
        });*/
        return view;
    }


}
