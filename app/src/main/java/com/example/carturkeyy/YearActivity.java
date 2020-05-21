package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.carturkeyy.Adapters.YearAdapter;
import com.example.carturkeyy.Models.YearPojo;
import com.example.carturkeyy.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YearActivity extends AppCompatActivity {

    ListView lvYear;
    List<YearPojo> list;
    YearAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);

        lvYear = findViewById(R.id.lvYear);
        ShowYear();
    }

    public void ShowYear() {
        list = new ArrayList<>();
        Call<List<YearPojo>> x = ManagerAll.getInstance().GetYear();
        x.enqueue(new Callback<List<YearPojo>>() {
            @Override
            public void onResponse(Call<List<YearPojo>> call, Response<List<YearPojo>> response) {
                if (response.isSuccessful())
                {
                    list=response.body();
                    adp = new YearAdapter(list, getApplicationContext(),YearActivity.this);
                    lvYear.setAdapter(adp);
                }
            }

            @Override
            public void onFailure(Call<List<YearPojo>> call, Throwable t) {

            }
        });

    }
}
