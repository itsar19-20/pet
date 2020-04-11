package com.ifts.applicazioneufficialetmpet;

import android.animation.ArgbEvaluator;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;


public class Swap extends Fragment {
    private Button chiudi_annuncio;
    private TextView aggiungi_ai_preferiti;
    private TextView scarta_annuncio;
    private TextView visualizza_annuncio;
    private ListView listView_preferiti;

    // private Dialog dialog_swap;
    private Dialog dialog_visualizza_annuncio;

    ViewPager viewPager;
    Slider_Adapter_Swap adapter_swap;
    List<Slider_Swap> sliders_swap;
    Slider_Adapter_Visualizza_Annuncio adapter_annuncio;
    List<Slider_Visualizza_Annunci> sliders_annunci;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_swap, container, false);
        sliders_swap = new ArrayList<>();
        sliders_swap.add(new Slider_Swap(R.drawable.logoapppet));
        sliders_swap.add(new Slider_Swap(R.drawable.zampa_voto));
        sliders_swap.add(new Slider_Swap(R.drawable.zampa_voto2));

        adapter_swap = new Slider_Adapter_Swap(sliders_swap, getContext());

        viewPager = view.findViewById(R.id.viewPager_swap);
        viewPager.setAdapter(adapter_swap);
        viewPager.setPadding(130,0,130,0);

        listView_preferiti =  view.findViewById(R.id.listView_preferiti);

        Integer[] colors_temp = {
                getResources().getColor(R.color.colorVerdeApp),
                getResources().getColor(R.color.colorAzzurroApp),
                getResources().getColor(R.color.colorMarroneApp)
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
                        getResources().getColor(R.color.colorMarroneApp)
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


}
