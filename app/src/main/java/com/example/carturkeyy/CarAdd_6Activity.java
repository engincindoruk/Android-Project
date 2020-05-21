package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carturkeyy.Models.CarAddPojo;
import com.example.carturkeyy.Models.GetIlPojo;
import com.example.carturkeyy.Models.IlanVerGenel;
import com.example.carturkeyy.Models.IlanVerTeknik;
import com.example.carturkeyy.RestApi.ManagerAll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarAdd_6Activity extends AppCompatActivity {

    Button btnSave6;
    EditText etFulladress;
    Spinner spnDistrict,spnCity;
    List<GetIlPojo>list;
    String []City={"Istanbul","Izmir","Ankara","Bursa","Izmit","Balıkkesir"};
    String []District={"Gungören","Hazneadar","Bebek","Etiler","Akçay","Bahçelievler"};
    ArrayAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add_6);
        tanimla();
        CityFill();
        DistrictFill();
        getInfo();

    }
    void tanimla()
    {
        etFulladress=findViewById(R.id.etFulladress);
        spnCity=findViewById(R.id.spnCity);
        spnDistrict=findViewById(R.id.spnDistrict);
        btnSave6=findViewById(R.id.btnSave6);
        btnSave6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                ilaniYayinla(CarAddPojo.getUyeid(),CarAddPojo.getBrand(),CarAddPojo.getModel(),CarAddPojo.getPlate(),CarAddPojo.getYear(),CarAddPojo.getPrice(),CarAddPojo.getCurrency(),currentDate,CarAddPojo.getKimden(),CarAddPojo.getAdverttitle(),CarAddPojo.getExplanation(),CarAddPojo.getColor(),CarAddPojo.getGuarantee(),CarAddPojo.getCity(),CarAddPojo.getDistrict(),CarAddPojo.getFulladres());

            }
        });
    }


    void ilaniYayinla(String uyeid ,String brand,String model,String plate,String productyear,String price,String currency,String ilandate ,String kimden,String ilanbaslik,String explanation,String color,String guarantee,String city ,String district,String fulladres)
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading,Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<IlanVerGenel> x= ManagerAll.getInstance().Ilanver(uyeid, brand, model, plate, productyear, price,currency, ilandate, kimden, ilanbaslik, explanation, color, guarantee, city, district, fulladres);
        x.enqueue(new Callback<IlanVerGenel>() {
            @Override
            public void onResponse(Call<IlanVerGenel> call, Response<IlanVerGenel> response) {
                if (response.body().isTf())
                {
                    ilaniYayinlaTeknik(CarAddPojo.getUyeid(),response.body().getIlanid(), CarAddPojo.getFuel(),CarAddPojo.getCapacity(),CarAddPojo.getPower(),CarAddPojo.getTransmission(),CarAddPojo.getKm(),CarAddPojo.getCase_type(),CarAddPojo.getLuggagevolume(),CarAddPojo.getColorstatus(),CarAddPojo.getChangestatus(),CarAddPojo.getTramerstatus(),CarAddPojo.getOrtyakittuketimi(),CarAddPojo.getSehirdisiyakittuketimi(),CarAddPojo.getSehiriciyakittuketimi(),CarAddPojo.getDepo());
                    Intent intent = new Intent(getApplicationContext(), CarAdd_7Activity.class);
                    intent.putExtra("uyeid",CarAddPojo.getUyeid());
                    intent.putExtra("ilanid",response.body().getIlanid());
                    startActivity(intent);
                    Toast.makeText(CarAdd_6Activity.this, "Advert Has Been Uploaded Successfully,You Can Add Image For Advert", Toast.LENGTH_LONG).show();
                    progressDialog.cancel();
                }
                else
                {
                    Toast.makeText(CarAdd_6Activity.this, "Advert Couldn't Be Loaded Contact The Customer Service", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<IlanVerGenel> call, Throwable t) {

            }
        });
    }


    void ilaniYayinlaTeknik(String uyeid , String ilanid, String yakittipi, String motorhacmi, String motorgucu,String vites, String km, String kasatipi , String bagajkapasitesi, String colorstatus, String changing, String tramer, String ortalamayakit, String sehirdisi , String sehirici, String depo)
    {
        Call<IlanVerTeknik> x=ManagerAll.getInstance().IlanverTeknik(uyeid, ilanid, yakittipi, motorhacmi, motorgucu,vites, km, kasatipi, bagajkapasitesi, colorstatus, changing, tramer, ortalamayakit, sehirdisi, sehirici, depo);
        x.enqueue(new Callback<IlanVerTeknik>() {
            @Override
            public void onResponse(Call<IlanVerTeknik> call, Response<IlanVerTeknik> response) {

            }

            @Override
            public void onFailure(Call<IlanVerTeknik> call, Throwable t) {

            }
        });
    }

    void CityFill()
    {
        adp=new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,City);
        spnCity.setAdapter(adp);
        spnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarAddPojo.setCity(spnCity.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    void DistrictFill()
    {
        adp=new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,District);
        spnDistrict.setAdapter(adp);
        spnDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarAddPojo.setDistrict(spnDistrict.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

   void check()
    {
        String fulladres=etFulladress.getText().toString().trim();
        if (fulladres.equals(""))
        {
            Toast.makeText(this, "Enter The Full Adres", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            CarAddPojo.setFulladres(fulladres);
        }
    }
    void getInfo()
    {
        try {
            for(int i=0;i<City.length;i++)
            {
                if(City[i]==CarAddPojo.getCity())
                {
                    spnCity.setSelection(i);
                }
            }
            for(int i=0;i<District.length;i++)
            {
                if(District[i]==CarAddPojo.getDistrict())
                {
                    spnDistrict.setSelection(i);
                }
            }
            etFulladress.setText(CarAddPojo.getFulladres());

        }catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
