package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carturkeyy.Adapters.IlanCommentAdapter;
import com.example.carturkeyy.Models.AddAdvertCommentPojo;
import com.example.carturkeyy.Models.GetYorumPojo;
import com.example.carturkeyy.Models.SilBireyselCommentPojo;
import com.example.carturkeyy.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllilanCommentsActivity extends AppCompatActivity {

    EditText etaddcommentheader,etaddcommenttext;
    Button btnaddcomment;
    ListView lvallcommenttoadvert;
    String ilanid="";
    List<GetYorumPojo> list;
    IlanCommentAdapter adapter;

    String SHARED_PREFERENCES="com.example.carturkeyy";
    String uyeid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allilan_comments);
        tanimla();
        Bundle bundle=getIntent().getExtras();
        ilanid=bundle.getString("ilanid");

        SharedPreferences preferences=getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        uyeid=preferences.getString("id","");

        lvallcommenttoadvert.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertComment(list.get(position).getBaslik(),list.get(position).getName()+" "+list.get(position).getSurname(),list.get(position).getText(),list.get(position).getId(),list.get(position).getUyeid());
            }
        });

        getComment();
    }

    void tanimla()
    {
        etaddcommentheader=findViewById(R.id.etaddcommentheader);
        etaddcommenttext=findViewById(R.id.etaddcommenttext);
        btnaddcomment=findViewById(R.id.btnaddcomment);
        lvallcommenttoadvert=findViewById(R.id.lvallcommenttoadvert);

        btnaddcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComment();
            }
        });
    }


    void getComment()
    {
        list=new ArrayList<>();
        Call<List<GetYorumPojo>> x= ManagerAll.getInstance().GetComment(ilanid);
        x.enqueue(new Callback<List<GetYorumPojo>>() {
            @Override
            public void onResponse(Call<List<GetYorumPojo>> call, Response<List<GetYorumPojo>> response) {
                list=response.body();
                if (list.get(0).getBaslik()!=null)
                {
                    adapter=new IlanCommentAdapter(list,getApplicationContext());
                    lvallcommenttoadvert.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<GetYorumPojo>> call, Throwable t) {

            }
        });
    }

    void alertComment(String header, String kimden, String text, final String Commentid, final String Uyeid)
    {
        if (!Uyeid.equals(uyeid))
        {
            //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            //hiç bir şey yapmamış olucak
        }
        else
        {
            LayoutInflater inflater=this.getLayoutInflater();
            View view=inflater.inflate(R.layout.alertlayoutcomment,null);

            TextView Header,Kimden,Text;
            Header=view.findViewById(R.id.tvadvertcommentheader);
            Kimden=view.findViewById(R.id.tvadvertcommentkimden);
            Text=view.findViewById(R.id.tvadvertcommenttext);
            Button btnalertyes,btnalertno;
            btnalertyes=view.findViewById(R.id.btnalertyes);
            btnalertno=view.findViewById(R.id.btnalertno);

            AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this);
            builder.setView(view);
            builder.setCancelable(true);
            final AlertDialog dialog=builder.create();

            Header.setText(header);
            Kimden.setText(kimden);
            Text.setText(text);

            btnalertyes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteComment(Uyeid,Commentid);
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

    }

    void deleteComment(String uid,String comid)
    {
        Call<SilBireyselCommentPojo> x=ManagerAll.getInstance().DeleteComment(uid, comid);
        x.enqueue(new Callback<SilBireyselCommentPojo>() {
            @Override
            public void onResponse(Call<SilBireyselCommentPojo> call, Response<SilBireyselCommentPojo> response) {
                if (response.body().isTf())
                {
                    Toast.makeText(AllilanCommentsActivity.this, response.body().getText(), Toast.LENGTH_SHORT).show();
                    getComment();
                }
            }

            @Override
            public void onFailure(Call<SilBireyselCommentPojo> call, Throwable t) {

            }
        });
    }


    void addComment()
    {
        String header=etaddcommentheader.getText().toString().trim();
        String text=etaddcommenttext.getText().toString().trim();
        if (header.equals(""))
        {
            Toast.makeText(this, "Please Enter Header Of Comment", Toast.LENGTH_SHORT).show();
        }
        if (text.equals(""))
        {
            Toast.makeText(this, "Please Enter Comment", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Call<AddAdvertCommentPojo> x=ManagerAll.getInstance().AddComment(uyeid,ilanid,header,text);
            Log.i("denemes",uyeid+" " +ilanid+" "+ header+" "+ text);
            x.enqueue(new Callback<AddAdvertCommentPojo>() {
                @Override
                public void onResponse(Call<AddAdvertCommentPojo> call, Response<AddAdvertCommentPojo> response) {
                    if (response.body().isTf())
                    {
                        Toast.makeText(AllilanCommentsActivity.this, response.body().getText(), Toast.LENGTH_SHORT).show();
                        etaddcommentheader.setText("");
                        etaddcommenttext.setText("");
                        getComment();
                    }
                    else
                    {
                        Toast.makeText(AllilanCommentsActivity.this, response.body().getText(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AddAdvertCommentPojo> call, Throwable t) {

                }
            });
        }

    }
}
