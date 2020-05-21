package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carturkeyy.Models.CarAddPojo;

public class CarAdd_4Activity extends AppCompatActivity {

    Button btnSave4;
    Spinner spnColor,spnGuarantee;
    String []Color={"Red","Blue","White","Gray","Green","Other"};
    String []Guaraante={"Yes","No"};
    ArrayAdapter adapter;
    LinearLayout layoutgohardware;
    EditText etLuggagevolume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add_4);
        tanimla();
        ColorFill();
        GuaranteeFill();
        getInfo();
        Gohardware();
    }
    void tanimla()
    {
        btnSave4=findViewById(R.id.btnSave4);
        spnColor=findViewById(R.id.spnColor);
        spnGuarantee=findViewById(R.id.spnGuarantee);
        etLuggagevolume=findViewById(R.id.etLuggagevolume);
        layoutgohardware=findViewById(R.id.layoutgohardware);
        btnSave4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String volume=etLuggagevolume.getText().toString().trim();
                if (volume.equals(""))
                {
                    Toast.makeText(CarAdd_4Activity.this, "Fill The Volume Space", Toast.LENGTH_SHORT).show();
                    etLuggagevolume.requestFocus();
                    return;
                }
                else {
                    CarAddPojo.setLuggagevolume(volume);
                    Intent intent=new Intent(getApplicationContext(),CarAdd_6Activity.class);
                    startActivity(intent);
                }

            }
        });
    }
    void ColorFill()
    {
        adapter=new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item, Color);
        spnColor.setAdapter(adapter);
        spnColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarAddPojo.setColor(spnColor.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    void GuaranteeFill()
    {
        adapter=new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,Guaraante);
        spnGuarantee.setAdapter(adapter);
        spnGuarantee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarAddPojo.setGuarantee(spnGuarantee.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    void Gohardware()
    {
        layoutgohardware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CarAdd_5Activity.class);
                startActivity(intent);
            }
        });
    }
    void getInfo()
    {
        try {
            for(int i=0;i<Color.length;i++)
            {
                if(Color[i]==CarAddPojo.getColor())
                {
                    spnColor.setSelection(i);
                }
            }
            for(int i=0;i<Guaraante.length;i++)
            {
                if(Guaraante[i]==CarAddPojo.getGuarantee())
                {
                    spnGuarantee.setSelection(i);
                }
            }
            etLuggagevolume.setText(CarAddPojo.getLuggagevolume());

        }catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
