package com.example.carturkeyy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carturkeyy.Models.BireyselilanlarPojo;
import com.example.carturkeyy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IlanlarimAdapter extends BaseAdapter {

    List<BireyselilanlarPojo>list;
    Context context;

    public IlanlarimAdapter(List<BireyselilanlarPojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.bireyselilanlar,parent,false);
        final ImageView imageView=convertView.findViewById(R.id.imgilanresim);
        final TextView baslik,adres,fiyat;
        baslik=convertView.findViewById(R.id.tvilanbaslik);
        adres=convertView.findViewById(R.id.tvilanadres);
        fiyat=convertView.findViewById(R.id.tvilanprice);

        baslik.setText(list.get(position).getBaslik());
        adres.setText(list.get(position).getIl()+"/"+list.get(position).getIlce());
        fiyat.setText(list.get(position).getFiyat()+" "+ list.get(position).getCurrency());
        Picasso.with(context).load("http://enginandroid.cf/"+list.get(position).getResim()).resize(100,100).into(imageView);


        return convertView;
    }
}
