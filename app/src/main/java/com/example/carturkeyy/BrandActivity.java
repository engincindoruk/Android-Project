package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.carturkeyy.Adapters.BrandAdapter;
import com.example.carturkeyy.Models.BrandPojo;
import com.example.carturkeyy.Models.CarAddPojo;
import com.example.carturkeyy.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandActivity extends AppCompatActivity {

    ListView lvBrand;
    List<BrandPojo> list;
    BrandAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        lvBrand=findViewById(R.id.lvBrand);

        ShowBrand();
    }

    void ShowBrand()
    {
        list=new ArrayList<>();
        Call<List<BrandPojo>> x= ManagerAll.getInstance().GetBrand();
        x.enqueue(new Callback<List<BrandPojo>>() {
            @Override
            public void onResponse(Call<List<BrandPojo>> call, Response<List<BrandPojo>> response) {
                if (response.isSuccessful())
                {
                    list=response.body();
                    adp=new BrandAdapter(list,getApplicationContext(),BrandActivity.this);
                    lvBrand.setAdapter(adp);
                }
            }

            @Override
            public void onFailure(Call<List<BrandPojo>> call, Throwable t) {

            }
        });
    }
}
//Please Enter The Code That Comes To Your Mail
//This Mail Account Already Exists.