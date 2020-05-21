package com.example.carturkeyy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carturkeyy.Models.GetBireyselilanCommentPojo;
import com.example.carturkeyy.R;

import java.util.List;

public class BireyselilancommentAdapter extends BaseAdapter {

    List<GetBireyselilanCommentPojo>list;
    Context context;

    public BireyselilancommentAdapter(List<GetBireyselilanCommentPojo> list, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.bireyselilancomment,parent,false);

        final TextView baslik,kimden,text;
        baslik=convertView.findViewById(R.id.tvbireyseladvertcommentheader);
        kimden=convertView.findViewById(R.id.tvbireyseladvertcommentkimden);
        text=convertView.findViewById(R.id.tvbireyseladvertcommenttext);

        baslik.setText(list.get(position).getBaslik());
        kimden.setText(list.get(position).getName()+" "+list.get(position).getSurname());
        text.setText(list.get(position).getText());

        return convertView;
    }
}
