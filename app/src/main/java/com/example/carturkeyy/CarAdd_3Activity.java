package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.carturkeyy.Models.CarAddPojo;

public class CarAdd_3Activity extends AppCompatActivity {

    EditText etColordetails,etChangedetails,etTramerdetails;
    RadioGroup rgcolor,rgchangetrack,rgtramer;
    RadioButton radioButton,rbcoloryes,rbcolorno,rbchangeyes,rbchangeno,rbtrameryes,rbtramerno;
    Button btnSave3;
    Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add_3);
        tanimla();
        checked();
        getInfo();

    }
    void tanimla()
    {
        etColordetails=findViewById(R.id.etColordetails);
        rgcolor=findViewById(R.id.rgcolor);
        rbcoloryes=findViewById(R.id.rbcoloryes);
        rbcolorno=findViewById(R.id.rbcolorno);
        etChangedetails=findViewById(R.id.etChangedetails);
        rgchangetrack=findViewById(R.id.rgchangetrack);
        rbchangeyes=findViewById(R.id.rbchangeyes);
        rbchangeno=findViewById(R.id.rbchangeno);
        etTramerdetails=findViewById(R.id.etTramerdetails);
        rgtramer=findViewById(R.id.rgtramer);
        rbtrameryes=findViewById(R.id.rbtrameryes);
        rbtramerno=findViewById(R.id.rbtramerno);
        btnSave3=findViewById(R.id.btnSave3);
        btnSave3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                //Log.i("info",CarAddPojo.getColorstatus()+"" + CarAddPojo.getChangestatus()+" "+ CarAddPojo.getTramerstatus());
                Intent intent=new Intent(getApplicationContext(),CarAdd_4Activity.class);
                startActivity(intent);
            }
        });

    }

    void checked()
    {
        rgcolor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                id=rgcolor.getCheckedRadioButtonId();
                radioButton=findViewById(id);
                if (radioButton==rbcoloryes)
                {
                    etColordetails.setEnabled(true);
                }
                else {
                    etColordetails.setEnabled(false);
                    etColordetails.setText("");
                }
            }
        });

        rgchangetrack.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                id=rgchangetrack.getCheckedRadioButtonId();
                radioButton=findViewById(id);
                if (radioButton==rbchangeyes)
                {
                    etChangedetails.setEnabled(true);
                }
                else{
                    etChangedetails.setEnabled(false);
                    etChangedetails.setText("");
                }

            }
        });

        rgtramer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                id=rgtramer.getCheckedRadioButtonId();
                radioButton=findViewById(id);
                if (radioButton==rbtrameryes)
                {
                    etTramerdetails.setEnabled(true);
                }
                else{
                    etTramerdetails.setEnabled(false);
                    etTramerdetails.setText("");
                }
            }
        });
    }
    void save()
    {
        CarAddPojo.setColorstatus(etColordetails.getText().toString().trim());
        CarAddPojo.setChangestatus(etChangedetails.getText().toString().trim());
        CarAddPojo.setTramerstatus(etTramerdetails.getText().toString().trim());
    }

    void getInfo()
    {
        String color=CarAddPojo.getColorstatus().trim();
        String changedetails=CarAddPojo.getChangestatus().trim();
        String tramer=CarAddPojo.getTramerstatus();
        if (color.equals(""))
        {
            rbcolorno.isChecked();
        }
        if (!color.equals(""))
        {
            rbcoloryes.setChecked(true);
            etColordetails.setText(color);
        }
        if (changedetails.equals(""))
        {
            rbchangeno.isChecked();
        }
        if (!changedetails.equals(""))
        {
            rbchangeyes.setChecked(true);
            etChangedetails.setText(changedetails);
        }
        if (tramer.equals(""))
        {
            rbtramerno.isChecked();
        }
        if (!tramer.equals(""))
        {
            rbtrameryes.setChecked(true);
            etTramerdetails.setText(tramer);
        }

    }
}
