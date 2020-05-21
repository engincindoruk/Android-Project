package com.example.carturkeyy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carturkeyy.Models.GetYorumPojo;
import com.example.carturkeyy.R;

import java.util.List;

public class IlanCommentAdapter extends BaseAdapter {
    List<GetYorumPojo>list;
    Context context;

    public IlanCommentAdapter(List<GetYorumPojo> list, Context context) {
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
        convertView= LayoutInflater.from(context).inflate(R.layout.allcomment,parent,false);

        final TextView baslik,kimden,text;
        baslik=convertView.findViewById(R.id.tvadvertcommentheader);
        kimden=convertView.findViewById(R.id.tvadvertcommentkimden);
        text=convertView.findViewById(R.id.tvadvertcommenttext);

        baslik.setText(list.get(position).getBaslik());
        kimden.setText(list.get(position).getName()+" "+list.get(position).getSurname());
        text.setText(list.get(position).getText());

        return convertView;
    }
}
