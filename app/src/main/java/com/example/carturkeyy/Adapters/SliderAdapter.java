package com.example.carturkeyy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.carturkeyy.Models.IlanDetaySliderPojo;
import com.example.carturkeyy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    List<IlanDetaySliderPojo>list;
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(List<IlanDetaySliderPojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(RelativeLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.sliderlayout,container,false);

        ImageView imageView=view.findViewById(R.id.sliderImageview);
        Picasso.with(context).load("http://enginandroid.cf/"+list.get(position).getResim()).resize(850,500).into(imageView);

        container.addView(view);
        return view;
    }
}
