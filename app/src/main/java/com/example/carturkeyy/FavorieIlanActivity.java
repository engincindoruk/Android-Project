package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carturkeyy.Adapters.UserFavorieAdvert;
import com.example.carturkeyy.Models.UserFavorieAdvertPojo;
import com.example.carturkeyy.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavorieIlanActivity extends AppCompatActivity {

    ListView lvFavorieAdvert;
    List<UserFavorieAdvertPojo>list;
    UserFavorieAdvert adapter;
    String SHARED_PREFERENCES="com.example.carturkeyy";
    String uyeid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorie_ilan);

        lvFavorieAdvert=findViewById(R.id.lvFavorieAdvert);

        SharedPreferences preferences=getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        uyeid=preferences.getString("id",null);

        getFavAdvert();

        lvFavorieAdvert.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),AllilanlarDetayActivity.class);
                intent.putExtra("ilanid",list.get(position).getIlanid());
                startActivity(intent);
            }
        });
    }

    void getFavAdvert()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading,Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        list=new ArrayList<>();
        Call<List<UserFavorieAdvertPojo>>x= ManagerAll.getInstance().GetUserFavorieAdvert(uyeid);
        x.enqueue(new Callback<List<UserFavorieAdvertPojo>>() {
            @Override
            public void onResponse(Call<List<UserFavorieAdvertPojo>> call, Response<List<UserFavorieAdvertPojo>> response) {
                if (response.isSuccessful())
                {
                    if (response.body().get(0).getFiyat()!=null)
                    {
                        list=response.body();
                        adapter=new UserFavorieAdvert(list,getApplicationContext());
                        lvFavorieAdvert.setAdapter(adapter);
                        progressDialog.cancel();
                    }
                    else
                    {
                        Toast.makeText(FavorieIlanActivity.this, response.body().get(0).getResult(), Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UserFavorieAdvertPojo>> call, Throwable t) {

            }
        });
    }
}
