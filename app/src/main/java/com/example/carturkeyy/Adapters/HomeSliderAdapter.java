package com.example.carturkeyy.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.carturkeyy.AllilanlarDetayActivity;
import com.example.carturkeyy.Models.HomeSliderPojo;
import com.example.carturkeyy.Models.IlanDetaySliderPojo;
import com.example.carturkeyy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeSliderAdapter extends PagerAdapter {
    List<HomeSliderPojo>list;
    Context context;
    LayoutInflater layoutInflater;
    Activity activity;

    public HomeSliderAdapter(List<HomeSliderPojo> list, Context context,Activity activity) {
        this.list = list;
        this.context = context;
        this.activity=activity;
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
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.sliderlayout,container,false);

        ImageView imageView=view.findViewById(R.id.sliderImageview);
        Picasso.with(context).load("http://enginandroid.cf/"+list.get(position).getResimyolu()).resize(1050,500).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getIlanid()!=null)
                {
                    Intent intent=new Intent(context, AllilanlarDetayActivity.class);
                    intent.putExtra("ilanid",list.get(position).getIlanid());
                    activity.startActivity(intent);
                }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
