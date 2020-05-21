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

public class CarAdd_1Activity extends AppCompatActivity {

    Spinner spnTransmission,spnFuel,spnCasatype;
    String [] Transmission={"Automatic","Manual","Half-Automatic"};
    String [] Fuel={"Petrol","Diesel","Hybrid","Lpg/Petrol"};
    String [] Casa={"Cabrio","Coupe","Hatchback 3","Hatchback 5","Sedan","Station Wagon"};
    EditText etPower,etCapacity;
    Button btnSave1;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add_1);
        tanimla();
        Transmissionfill();
        FuelFill();
        CasaFill();

        getInfo();
        clickbutton();
    }


    void tanimla()
    {
        spnTransmission=findViewById(R.id.spnTransmission);
        spnFuel=findViewById(R.id.spnFuel);
        spnCasatype=findViewById(R.id.spnCasatype);
        etPower=findViewById(R.id.etPower);
        etCapacity=findViewById(R.id.etCapacity);
        btnSave1=findViewById(R.id.btnSave1);
    }
    void Transmissionfill()
    {
        adapter=new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,Transmission);
        spnTransmission.setAdapter(adapter);
        spnTransmission.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarAddPojo.setTransmission(spnTransmission.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    void FuelFill()
    {
        adapter=new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,Fuel);
        spnFuel.setAdapter(adapter);
        spnFuel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarAddPojo.setFuel(spnFuel.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void CasaFill() {
        adapter=new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,Casa);
        spnCasatype.setAdapter(adapter);
        spnCasatype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarAddPojo.setCase_type(spnCasatype.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    
    void clickbutton()
    {
        btnSave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPower.getText().toString().trim().equals("") || etCapacity.getText().toString().trim().equals(""))
                {
                    Toast.makeText(CarAdd_1Activity.this, "Please Fill The Empty Spaces", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    CarAddPojo.setPower(etPower.getText().toString().trim());
                    CarAddPojo.setCapacity(etCapacity.getText().toString().trim());
                    Intent intent=new Intent(getApplicationContext(),CarAdd_2Activity.class);
                    startActivity(intent);
                              }
            }
        });
    }

    void getInfo()
    {
        try {
            for(int i=0;i<Transmission.length;i++)
            {
                if(Transmission[i]==CarAddPojo.getTransmission())
                {
                    spnTransmission.setSelection(i);
                }
            }
            for(int i=0;i<Fuel.length;i++)
            {
                if(Fuel[i]==CarAddPojo.getFuel())
                {
                    spnFuel.setSelection(i);
                }
            }
            for(int i=0;i<Casa.length;i++)
            {
                if(Casa[i]==CarAddPojo.getCase_type())
                {
                    spnCasatype.setSelection(i);
                }
            }
            etPower.setText(CarAddPojo.getPower());
            etCapacity.setText(CarAddPojo.getCapacity());
        }catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}


