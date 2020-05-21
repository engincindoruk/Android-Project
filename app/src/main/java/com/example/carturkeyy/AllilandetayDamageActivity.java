package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AllilandetayDamageActivity extends AppCompatActivity {

    EditText etdetayColordetails,etdetayChangedetails,etdetayTramerdetails;
    TextView tvdetayadvertexplanation;
    RadioGroup rgdetaycolor,rgdetaychangetrack,rgdetaytramer;
    RadioButton rbdetaycoloryes,rbdetaycolorno,rbdetaychangeyes,rbdetaychangeno,rbdetaytrameryes,rbdetaytramerno;
    String color,tramer,changing,explanation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allilandetay_damage);

        tanimla();
        Bundle bundle=getIntent().getExtras();
        color= bundle.getString("color");
        tramer= bundle.getString("changing");
        changing= bundle.getString("tramer");
        explanation= bundle.getString("acıklama");
        getInfo();
    }

    void tanimla()
    {
        etdetayColordetails=findViewById(R.id.etdetayColordetails);
        rgdetaycolor=findViewById(R.id.rgdetaycolor);
        rbdetaycoloryes=findViewById(R.id.rbdetaycoloryes);
        rbdetaycolorno=findViewById(R.id.rbdetaycolorno);
        etdetayChangedetails=findViewById(R.id.etdetayChangedetails);
        rgdetaychangetrack=findViewById(R.id.rgdetaychangetrack);
        rbdetaychangeyes=findViewById(R.id.rbdetaychangeyes);
        rbdetaychangeno=findViewById(R.id.rbdetaychangeno);
        etdetayTramerdetails=findViewById(R.id.etdetayTramerdetails);
        rgdetaytramer=findViewById(R.id.rgdetaytramer);
        rbdetaytrameryes=findViewById(R.id.rbdetaytrameryes);
        rbdetaytramerno=findViewById(R.id.rbdetaytramerno);
        tvdetayadvertexplanation=findViewById(R.id.tvdetayadvertexplanation);

    }
    void getInfo()
    {
        if (color.equals(""))
        {
            rbdetaycolorno.isChecked();
        }
        if (!color.equals(""))
        {
            rbdetaycoloryes.setChecked(true);
            etdetayColordetails.setText(color);
        }
        if (changing.equals(""))
        {
            rbdetaychangeno.isChecked();
        }
        if (!changing.equals(""))
        {
            rbdetaychangeyes.setChecked(true);
            etdetayChangedetails.setText(changing);
        }
        if (tramer.equals(""))
        {
            rbdetaytramerno.isChecked();
        }
        if (!tramer.equals(""))
        {
            rbdetaytrameryes.setChecked(true);
            etdetayTramerdetails.setText(tramer);
        }
        if (!explanation.equals(""))
        {
            tvdetayadvertexplanation.setText(explanation);
        }

    }

}
