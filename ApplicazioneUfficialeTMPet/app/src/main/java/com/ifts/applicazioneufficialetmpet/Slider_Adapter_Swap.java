package com.ifts.applicazioneufficialetmpet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class Slider_Adapter_Swap extends PagerAdapter {

    private List<Slider_Swap> sliders_swap;
    private LayoutInflater inflater;
    private Context context;

    public Slider_Adapter_Swap(List<Slider_Swap> sliders_swap, Context context) {
        this.sliders_swap = sliders_swap;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliders_swap.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_swap, container, false);
        ImageView imageView;

        imageView = view.findViewById(R.id.image_single_item_swap);

        imageView.setImageResource(sliders_swap.get(position).getImg());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
