package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.carturkeyy.Models.CarAddPojo;

public class CarAdd_5Activity extends AppCompatActivity {

    EditText etAFC,etUFC,etEFC,etFT;
    Button btnSave1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add_5);
        tanimla();
        getInfo();
        check();
    }
    void tanimla()
    {
        etAFC=findViewById(R.id.etAFC);
        etUFC=findViewById(R.id.etUFC);
        etEFC=findViewById(R.id.etEFC);
        etFT=findViewById(R.id.etFT);
        btnSave1=findViewById(R.id.btnSave1);
        btnSave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
                Intent intent=new Intent(getApplicationContext(),CarAdd_4Activity.class);
                startActivity(intent);
            }
        });
    }
    void check()
    {
        String afc=etAFC.getText().toString().trim();
        String ufe=etUFC.getText().toString().trim();
        String efc=etEFC.getText().toString().trim();
        String ft=etFT.getText().toString().trim();

        CarAddPojo.setOrtyakittuketimi(afc);
        CarAddPojo.setSehiriciyakittuketimi(ufe);
        CarAddPojo.setSehirdisiyakittuketimi(efc);
        CarAddPojo.setDepo(ft);

    }
    void getInfo()
    {
        etAFC.setText(CarAddPojo.getOrtyakittuketimi());
        etUFC.setText(CarAddPojo.getSehiriciyakittuketimi());
        etEFC.setText(CarAddPojo.getSehirdisiyakittuketimi());
        etFT.setText(CarAddPojo.getDepo());
    }
}
