package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.carturkeyy.Adapters.ModelAdapter;
import com.example.carturkeyy.Models.CarAddPojo;
import com.example.carturkeyy.Models.ModelPojo;
import com.example.carturkeyy.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelActivity extends AppCompatActivity {

    ListView lvModel;
    List<ModelPojo>list;
    ModelAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);
        lvModel=findViewById(R.id.lvModel);
        ShowModel();
    }
    public void ShowModel()
    {
        list=new ArrayList<>();
        String year= CarAddPojo.getYear().trim();
        String brand=CarAddPojo.getBrand().trim();
        Log.i("modelll",year+ " " + brand);
        Call<List<ModelPojo>> x= ManagerAll.getInstance().GetModel(brand,year);
        x.enqueue(new Callback<List<ModelPojo>>() {
            @Override
            public void onResponse(Call<List<ModelPojo>> call, Response<List<ModelPojo>> response) {
                if (response.isSuccessful())
                {
                    list=response.body();
                    adp=new ModelAdapter(list,getApplicationContext(),ModelActivity.this);
                    lvModel.setAdapter(adp);
                }
            }

            @Override
            public void onFailure(Call<List<ModelPojo>> call, Throwable t) {

            }
        });
    }
}
