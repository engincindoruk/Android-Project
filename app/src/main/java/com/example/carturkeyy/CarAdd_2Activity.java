package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carturkeyy.Models.CarAddPojo;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class CarAdd_2Activity extends AppCompatActivity {

    EditText etAdverttitle,etPrice,etKm,etPlate,etExplanation;
    Button btnSave2;
    Spinner spnPrice;
    String [] Currency={"TL","USD","EURO"};
    ArrayAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add_2);
        tanimla();
        getInfo();
        CurrenctFill();

    }
    void  tanimla()
    {
        etAdverttitle=findViewById(R.id.etAdverttitle);
        etPrice=findViewById(R.id.etPrice);
        etKm=findViewById(R.id.etKm);
        etPlate=findViewById(R.id.etPlate);
        etExplanation=findViewById(R.id.etExplanation);
        btnSave2=findViewById(R.id.btnSave2);
        spnPrice=findViewById(R.id.spnPrice);
        btnSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

    }
    void CurrenctFill()
    {
        adp=new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,Currency);
        spnPrice.setAdapter(adp);
        spnPrice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarAddPojo.setCurrency(spnPrice.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void check()
    {
        String adverttitle=etAdverttitle.getText().toString().trim();
        String price=etPrice.getText().toString().trim();
        String km=etKm.getText().toString().trim();
        String plate=etPlate.getText().toString().trim();
        String explanation=etExplanation.getText().toString().trim();

        if (adverttitle.equals(""))
        {
            Toast.makeText(this, "Enter Advert Title", Toast.LENGTH_SHORT).show();
            etAdverttitle.requestFocus();
            return;
        }
        if (price.equals("") || price.length()<3)
        {
            Toast.makeText(this, "Enter Vehicle Price", Toast.LENGTH_SHORT).show();
            etPrice.requestFocus();
            return;
        }
        if (km.equals(""))
        {
            Toast.makeText(this, "Enter The Km ", Toast.LENGTH_SHORT).show();
            etKm.requestFocus();
            return;
        }
        if (plate.equals("") || plate.length()<5)
        {
            Toast.makeText(this, "Enter Plate Of Car", Toast.LENGTH_SHORT).show();
            etPlate.requestFocus();
            return;
        }
        if (explanation.equals(""))
        {
            Toast.makeText(this, "Enter Explanation Of Advert", Toast.LENGTH_SHORT).show();
            etExplanation.requestFocus();
            return;
        }
        else
        {
            CarAddPojo.setPrice("");
            CarAddPojo.setAdverttitle(adverttitle);
            CarAddPojo.setPrice(price);
            CarAddPojo.setKm(km);
            CarAddPojo.setPlate(plate);
            CarAddPojo.setExplanation(explanation);

            Intent intent=new Intent(getApplicationContext(),CarAdd_3Activity.class);
            startActivity(intent);
        }
    }
    void getInfo()
    {
        etAdverttitle.setText(CarAddPojo.getAdverttitle());
        for(int i=0;i<Currency.length;i++)
        {
            if(Currency[i]==CarAddPojo.getCurrency())
            {
                spnPrice.setSelection(i);
            }
        }

        etPrice.setText(CarAddPojo.getPrice());
        etKm.setText(CarAddPojo.getKm());
        etPlate.setText(CarAddPojo.getPlate());
        etExplanation.setText(CarAddPojo.getExplanation());

    }

}
