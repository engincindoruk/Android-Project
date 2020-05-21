package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.carturkeyy.Adapters.IlanlarAdapter;
import com.example.carturkeyy.Models.IlanlarPojo;
import com.example.carturkeyy.R;
import com.example.carturkeyy.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllilanlarActivity extends AppCompatActivity {

    ListView lvAllAdvert;
    IlanlarAdapter adapter;
    List<IlanlarPojo> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allilanlar);

        lvAllAdvert=findViewById(R.id.lvAllAdvert);
        lvAllAdvert.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),AllilanlarDetayActivity.class);
                intent.putExtra("ilanid",list.get(position).getIlanid());
                startActivity(intent);
            }
        });
        getir();
    }

    void getir()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading,Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        list=new ArrayList<>();
        Call<List<IlanlarPojo>> x= ManagerAll.getInstance().GetAllAdvert();
        x.enqueue(new Callback<List<IlanlarPojo>>() {
            @Override
            public void onResponse(Call<List<IlanlarPojo>> call, Response<List<IlanlarPojo>> response) {
                if (response.isSuccessful())
                {
                    list=response.body();
                    adapter=new IlanlarAdapter(list,getApplicationContext());
                    lvAllAdvert.setAdapter(adapter);
                    progressDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<List<IlanlarPojo>> call, Throwable t) {

            }
        });

    }
}
