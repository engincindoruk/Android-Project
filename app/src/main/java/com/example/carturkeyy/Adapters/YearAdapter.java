package com.example.carturkeyy.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.carturkeyy.BrandActivity;
import com.example.carturkeyy.Models.CarAddPojo;
import com.example.carturkeyy.Models.YearPojo;
import com.example.carturkeyy.R;

import java.util.List;

public class YearAdapter extends BaseAdapter {
    List<YearPojo> list;
    Context context;
    Activity activity;

    public YearAdapter(List<YearPojo> list, Context context,Activity activity) {
        this.list = list;
        this.context = context;
        this.activity=activity;
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
        convertView= LayoutInflater.from(context).inflate(R.layout.carlayoutforadapter,parent,false);
        TextView textView=convertView.findViewById(R.id.id);
        textView.setText(list.get(position).getYear());
        LinearLayout linearLayout=convertView.findViewById(R.id.foradapter);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarAddPojo.setYear(list.get(position).getYear());
                Intent intent=new Intent(context, BrandActivity.class);
                activity.startActivity(intent);
            }
        });


        return convertView;
    }
}
