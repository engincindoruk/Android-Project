package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carturkeyy.Adapters.IlanlarimAdapter;
import com.example.carturkeyy.Models.BireyselilanlarPojo;
import com.example.carturkeyy.Models.CarAddPojo;
import com.example.carturkeyy.Models.IlanlarimSilPojo;
import com.example.carturkeyy.Models.LoginPojo;
import com.example.carturkeyy.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BireyselilanActivity extends AppCompatActivity {

    ListView lvbireyselilanlar;
    IlanlarimAdapter adapter;
    List<BireyselilanlarPojo>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bireyselilan);

        lvbireyselilanlar=findViewById(R.id.lvbireyselilanlar);
        getir();

        lvbireyselilanlar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alertshow(list.get(position).getResim(),list.get(position).getBaslik(),list.get(position).getIl(),list.get(position).getIlce(),list.get(position).getFiyat(),list.get(position).getCurrency(),list.get(position).getIlanid());
            }
        });
    }

    public void getir()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading,Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        list=new ArrayList<>();
        String uyeid= CarAddPojo.getUyeid();
        Call<List<BireyselilanlarPojo>> x=ManagerAll.getInstance().GetAdvert(uyeid);
        x.enqueue(new Callback<List<BireyselilanlarPojo>>() {
            @Override
            public void onResponse(Call<List<BireyselilanlarPojo>> call, Response<List<BireyselilanlarPojo>> response) {
                if (response.isSuccessful())
                {
                    list=response.body();
                    if (response.body().get(0).getFiyat()!=null)
                    {
                        adapter=new IlanlarimAdapter(list,getApplicationContext());
                        lvbireyselilanlar.setAdapter(adapter);
                        Toast.makeText(BireyselilanActivity.this, response.body().get(0).getResult(), Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                    else{
                        Toast.makeText(BireyselilanActivity.this, response.body().get(0).getResult(), Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BireyselilanlarPojo>> call, Throwable t) {

            }
        });

    }

    public void Alertshow(String image, String ilanbaslik, String ilansehir, String ilanilce, String price,String currency, final String ilanid)
    {
        LayoutInflater inflater=this.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertlayout,null);

        ImageView imageView=view.findViewById(R.id.imgalertimage);
        TextView tvalertilanbaslik,tvalertilanadres,tvalertilanprice;
        tvalertilanbaslik=view.findViewById(R.id.tvalertilanbaslik);
        tvalertilanadres=view.findViewById(R.id.tvalertilanadres);
        tvalertilanprice=view.findViewById(R.id.tvalertilanprice);
        Button btnalertyes,btnalertno;
        btnalertyes=view.findViewById(R.id.btnalertyes);
        btnalertno=view.findViewById(R.id.btnalertno);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog=builder.create();

        tvalertilanbaslik.setText(ilanbaslik);
        tvalertilanadres.setText(ilansehir+"/"+ilanilce);
        tvalertilanprice.setText(price+" "+currency);
        Picasso.with(getApplicationContext()).load("http://enginandroid.cf/"+image).resize(100,100).into(imageView);

        btnalertyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(ilanid);
                dialog.cancel();
            }
        });

        btnalertno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();

    }

    public void delete(String id)
    {
        Call<IlanlarimSilPojo> x= ManagerAll.getInstance().DeleteAdvert(id);
        x.enqueue(new Callback<IlanlarimSilPojo>() {
            @Override
            public void onResponse(Call<IlanlarimSilPojo> call, Response<IlanlarimSilPojo> response) {
                if (response.body().isTf())
                {
                    getir();
                    Toast.makeText(BireyselilanActivity.this, response.body().getResult(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IlanlarimSilPojo> call, Throwable t) {

            }
        });
    }

}
