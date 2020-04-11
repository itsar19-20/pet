package com.ifts.applicazioneufficialetmpet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class Slider_Adapter_Visualizza_Annuncio extends PagerAdapter {

    private List<Slider_Visualizza_Annunci> sliders_annunci;
    private LayoutInflater inflater;
    private Context context;

    public Slider_Adapter_Visualizza_Annuncio(List<Slider_Visualizza_Annunci> sliders_annunci, Context context) {
        this.sliders_annunci = sliders_annunci;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliders_annunci.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_visualizza_annuncio, container, false);
        ImageView imageView;

        imageView = view.findViewById(R.id.image_single_item_visualizza_annuncio);

        imageView.setImageResource(sliders_annunci.get(position).getImg());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
