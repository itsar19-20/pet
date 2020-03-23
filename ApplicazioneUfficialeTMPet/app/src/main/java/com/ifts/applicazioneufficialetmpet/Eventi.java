package com.ifts.applicazioneufficialetmpet;

import android.animation.ArgbEvaluator;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;


public class Eventi extends Fragment {
    private Button chiudi_evento;
    private Button visualizza_evento;
    private Dialog dialog_evento;
    private Dialog dialog_visualizza_evento;


    ViewPager viewPager;
    Slider_Adapter_Eventi adapter_eventi;
    List<Slider_Eventi> sliders_eventi;
    Slider_Adapter_Visualizza_Evento adapter_visualizza_eventi;
    List<Slider_Visualizza_Eventi> sliders_visualizza_eventi;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eventi, container, false);

        sliders_eventi = new ArrayList<>();
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
        });
        return view;
    }
}
