package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carturkeyy.Adapters.BireyselilancommentAdapter;
import com.example.carturkeyy.Models.GetBireyselilanCommentPojo;
import com.example.carturkeyy.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BireyselilanMessageActivity extends AppCompatActivity {

    String uyeid="";
    ListView lvbireyselilanmessage;
    List<GetBireyselilanCommentPojo>list;
    BireyselilancommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bireyselilan_message);

        lvbireyselilanmessage=findViewById(R.id.lvbireyselilanmessage);
        uyeid=getIntent().getStringExtra("uyeid");
        Log.i("deneme",uyeid);

        getir();

       lvbireyselilanmessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent=new Intent(getApplicationContext(),AllilanlarDetayActivity.class);
               intent.putExtra("ilanid",list.get(position).getIlanid());
               startActivity(intent);
           }
       });
    }

    void getir()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading,Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        list=new ArrayList<>();
        Call<List<GetBireyselilanCommentPojo>> x= ManagerAll.getInstance().GetBireyselIlanComment(uyeid);
        x.enqueue(new Callback<List<GetBireyselilanCommentPojo>>() {
            @Override
            public void onResponse(Call<List<GetBireyselilanCommentPojo>> call, Response<List<GetBireyselilanCommentPojo>> response) {
                if (response.body().get(0).isTf())
                {
                    list=response.body();
                    adapter=new BireyselilancommentAdapter(list,getApplicationContext());
                    lvbireyselilanmessage.setAdapter(adapter);
                    progressDialog.cancel();
                }
                else
                {
                    Toast.makeText(BireyselilanMessageActivity.this,"You Have Not Any Message", Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();
                    Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<List<GetBireyselilanCommentPojo>> call, Throwable t) {

            }
        });
    }
}
