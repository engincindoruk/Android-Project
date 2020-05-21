package com.example.carturkeyy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.carturkeyy.Adapters.HomeSliderAdapter;
import com.example.carturkeyy.Models.HomeSliderPojo;
import com.example.carturkeyy.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    Button btnadvert,btnMyadvert,btnAllAdvert,btnContactInfo,btnFavadvert,btnMessage;
    Intent intent;

    String SHARED_PREFERENCES="com.example.carturkeyy";
    String uyeid="";
    List<HomeSliderPojo> list;

    ViewPager homepageslider;
    CircleIndicator homepagesliderNokta;

    HomeSliderAdapter homeSliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activtiy);
        tanimla();
        Event();

        SharedPreferences preferences=getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        uyeid=preferences.getString("id",null);

        getSlider();
    }
    void tanimla()
    {
        btnadvert=findViewById(R.id.btnadvert);
        btnMyadvert=findViewById(R.id.btnMyadvert);
        btnAllAdvert=findViewById(R.id.btnAllAdvert);
        homepageslider=findViewById(R.id.homepageslider);
        homepagesliderNokta=findViewById(R.id.homepagesliderNokta);
        btnContactInfo=findViewById(R.id.btnContactInfo);
        btnFavadvert=findViewById(R.id.btnFavadvert);
        btnMessage=findViewById(R.id.btnMessage);

    }

    public void getSlider()
    {
        list=new ArrayList<>();
        Call<List<HomeSliderPojo>> x= ManagerAll.getInstance().GetFavorieImage(uyeid);
        x.enqueue(new Callback<List<HomeSliderPojo>>() {
            @Override
            public void onResponse(Call<List<HomeSliderPojo>> call, Response<List<HomeSliderPojo>> response) {
                if (response.body().get(0).isTf())
                {
                    list=response.body();
                    homeSliderAdapter=new HomeSliderAdapter(list,getApplicationContext(),HomeActivity.this);
                    homepageslider.setAdapter(homeSliderAdapter);
                    homepagesliderNokta.setViewPager(homepageslider);
                    homepagesliderNokta.bringToFront();
                }
                else
                {
                    list=response.body();
                    homeSliderAdapter=new HomeSliderAdapter(list,getApplicationContext(),HomeActivity.this);
                    homepageslider.setAdapter(homeSliderAdapter);
                    homepagesliderNokta.setViewPager(homepageslider);
                    homepagesliderNokta.bringToFront();

                }
            }

            @Override
            public void onFailure(Call<List<HomeSliderPojo>> call, Throwable t) {

            }
        });
    }

    void Event()
    {
        btnadvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getApplicationContext(),YearActivity.class);
                startActivity(intent);
            }
        });
        btnMyadvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getApplicationContext(),BireyselilanActivity.class);
                startActivity(intent);
            }
        });
        btnAllAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getApplicationContext(), AllilanlarActivity.class);
                startActivity(intent);
            }
        });
        btnContactInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getApplicationContext(),AboutInformationActivity.class);
                startActivity(intent);
            }
        });
        btnFavadvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getApplicationContext(),FavorieIlanActivity.class);
                startActivity(intent);
            }
        });
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getApplicationContext(),BireyselilanMessageActivity.class);
                intent.putExtra("uyeid",uyeid);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Exit");
        builder.setMessage("Do You Want To Exit The Application?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getSlider();
    }
}
