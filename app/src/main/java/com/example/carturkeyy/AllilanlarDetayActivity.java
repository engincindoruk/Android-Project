package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carturkeyy.Adapters.SliderAdapter;
import com.example.carturkeyy.Models.FavoriePojo;
import com.example.carturkeyy.Models.FavorieislemPojo;
import com.example.carturkeyy.Models.IlanDetayPojo;
import com.example.carturkeyy.Models.IlanDetaySliderPojo;
import com.example.carturkeyy.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllilanlarDetayActivity extends AppCompatActivity {

    private TextView tvdetailsofadvertheader, tvadvertdetailadress, tvadvertdetailsPrice, tvadvertdetailsDate, tvadvertdetailsBrand, tvadvertdetailsModel;
    private TextView tvadvertdetailsYear, tvadvertdetailsPlate, tvadvertdetailsFuelType, tvadvertdetailsCasaType, tvadvertdetailsColor, tvadvertdetailsGuarantee;
    private TextView tvadvertdetailsKm, tvadvertdetailsLuggagevolume;
    private TextView tvadvertdetailsMotorCapacity, tvadvertdetailsMotorPower, tvadvertdetailsTransmission, tvadvertdetailsAverageFuelConsumption;
    private TextView tvadvertdetailsExternalFuelConsumption, tvadvertdetailsUrbanFuelConsumption, tvadvertdetailsFuelTank;
    private TextView tvadvertdetailsFullAdress;
    private Button btnadvertdetailadvertiser, btnadvertcommemnts, btnadvertaddfavorie, btnadverddetailCall, btnadverddetailMessage;
    private ViewPager ilandetayslider;
    private LinearLayout layoutdamagequery;
    String ilanid = "";
    String uyeid = "";
    List<IlanDetaySliderPojo> list;
    SliderAdapter adapter;
    String SHARED_PREFERENCES="com.example.carturkeyy";

    CircleIndicator circleIndicator;
    ProgressDialog progressDialog;
    String uyetel="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allilanlar_detay);

        tanimla();
        Bundle bundle = getIntent().getExtras();
        ilanid = bundle.getString("ilanid");

        SharedPreferences preferences=getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        uyeid=preferences.getString("id",null);

        getAdvertDetails();

        checkFavorie();

        event();


    }

    void event()
    {
        btnadvertaddfavorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFavorie();

            }
        });

        btnadverddetailCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+uyetel));
                startActivity(intent);
            }
        });

        btnadverddetailMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:"+uyetel));
                startActivity(intent);
            }
        });
        btnadvertcommemnts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AllilanCommentsActivity.class);
                intent.putExtra("ilanid",ilanid);
                startActivity(intent);
            }
        });

    }

    void setFavorie()
    {
        Call<FavorieislemPojo> x=ManagerAll.getInstance().FavorieIslem(uyeid,ilanid);
        x.enqueue(new Callback<FavorieislemPojo>() {
            @Override
            public void onResponse(Call<FavorieislemPojo> call, Response<FavorieislemPojo> response) {
                if (response.body().isTf())
                {
                    Toast.makeText(AllilanlarDetayActivity.this, response.body().getText(), Toast.LENGTH_SHORT).show();
                    checkFavorie();
                }
                else {
                    Toast.makeText(AllilanlarDetayActivity.this, response.body().getText(), Toast.LENGTH_SHORT).show();
                    checkFavorie();
                }
            }

            @Override
            public void onFailure(Call<FavorieislemPojo> call, Throwable t) {

            }
        });
    }

    void checkFavorie()
    {
        Call<FavoriePojo> x=ManagerAll.getInstance().CheckFavorie(uyeid,ilanid);
        x.enqueue(new Callback<FavoriePojo>() {
            @Override
            public void onResponse(Call<FavoriePojo> call, Response<FavoriePojo> response) {
                if (response.body().isTf())
                {
                    btnadvertaddfavorie.setText(response.body().getText());
                }
                else
                {
                    btnadvertaddfavorie.setText(response.body().getText());
                }
            }

            @Override
            public void onFailure(Call<FavoriePojo> call, Throwable t) {

            }
        });
    }

    public void getAdvertDetails() {
        progressDialog=new ProgressDialog(AllilanlarDetayActivity.this);
        progressDialog.setMessage("Loading,Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<IlanDetayPojo> x = ManagerAll.getInstance().GetAdvertDetails(ilanid);
        x.enqueue(new Callback<IlanDetayPojo>() {
            @Override
            public void onResponse(Call<IlanDetayPojo> call, final Response<IlanDetayPojo> response) {
                if (response.isSuccessful()) {
                    tvdetailsofadvertheader.setText(response.body().getIlanbaslik());
                    tvadvertdetailadress.setText(response.body().getCity() + "/" + response.body().getDistrict());
                    tvadvertdetailsPrice.setText(response.body().getPrice()+" "+response.body().getCurrency());
                    tvadvertdetailsDate.setText(response.body().getIlandate());
                    tvadvertdetailsBrand.setText(response.body().getBrand());
                    tvadvertdetailsModel.setText(response.body().getModel());
                    tvadvertdetailsYear.setText(response.body().getProductyear());
                    tvadvertdetailsPlate.setText(response.body().getPlate());
                    tvadvertdetailsFuelType.setText(response.body().getYakittipi());
                    tvadvertdetailsCasaType.setText(response.body().getKasatipi());
                    tvadvertdetailsColor.setText(response.body().getColor());
                    tvadvertdetailsGuarantee.setText(response.body().getGuarantee());
                    tvadvertdetailsKm.setText(response.body().getKm());
                    tvadvertdetailsLuggagevolume.setText(response.body().getBagajkapasitesi() + " LT");
                    tvadvertdetailsMotorCapacity.setText(response.body().getMotorhacmi() + " CC");
                    tvadvertdetailsMotorPower.setText(response.body().getMotorgucu() + " HP");
                    tvadvertdetailsTransmission.setText(response.body().getVites());
                    tvadvertdetailsAverageFuelConsumption.setText(response.body().getOrtalamayakit() + " LT");
                    tvadvertdetailsExternalFuelConsumption.setText(response.body().getSehirdisi() + " LT");
                    tvadvertdetailsUrbanFuelConsumption.setText(response.body().getSehirici() + " LT");
                    tvadvertdetailsFuelTank.setText(response.body().getDepo() + " LT");
                    tvadvertdetailsFullAdress.setText(response.body().getFulladress());
                    btnadvertdetailadvertiser.setText(response.body().getKimden());
                    uyetel=response.body().getUyetel();

                    layoutdamagequery.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent =new Intent(getApplicationContext(),AllilandetayDamageActivity.class);
                            intent.putExtra("color",response.body().getColorstatus());
                            intent.putExtra("changing",response.body().getChanging());
                            intent.putExtra("tramer",response.body().getTramer());
                            intent.putExtra("acıklama",response.body().getExplanation());
                            startActivity(intent);
                        }
                    });

                    if (response.body().getOrtalamayakit().equals(""))
                    {
                        tvadvertdetailsAverageFuelConsumption.setText("Undefined");

                    }
                    if (response.body().getSehirdisi().equals(""))
                    {
                        tvadvertdetailsExternalFuelConsumption.setText("Undefined");
                    }
                    if (response.body().getSehirici().equals(""))
                    {
                        tvadvertdetailsUrbanFuelConsumption.setText("Undefined");
                    }
                    if (response.body().getDepo().equals(""))
                    {
                        tvadvertdetailsFuelTank.setText("Undefined");
                    }
                    if (response.body().getFulladress().equals(""))
                    {
                        tvadvertdetailsFullAdress.setText("Undefined");
                    }
                    getImage();
                    progressDialog.cancel();

                }
            }

            @Override
            public void onFailure(Call<IlanDetayPojo> call, Throwable t) {

            }
        });

    }


    public void getImage() {
        list = new ArrayList<>();
        Call<List<IlanDetaySliderPojo>> x = ManagerAll.getInstance().GetAdvertImage(ilanid);
        x.enqueue(new Callback<List<IlanDetaySliderPojo>>() {
            @Override
            public void onResponse(Call<List<IlanDetaySliderPojo>> call, Response<List<IlanDetaySliderPojo>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    adapter = new SliderAdapter(list, getApplicationContext());
                    ilandetayslider.setAdapter(adapter);
                    circleIndicator.setViewPager(ilandetayslider);
                    circleIndicator.bringToFront();
                }
            }

            @Override
            public void onFailure(Call<List<IlanDetaySliderPojo>> call, Throwable t) {

            }
        });
    }


    public void tanimla() {
        circleIndicator = findViewById(R.id.sliderNokta);
        tvdetailsofadvertheader = findViewById(R.id.tvdetailsofadvertheader);
        tvadvertdetailadress = findViewById(R.id.tvadvertdetailadress);
        tvadvertdetailsPrice = findViewById(R.id.tvadvertdetailsPrice);
        tvadvertdetailsDate = findViewById(R.id.tvadvertdetailsDate);
        tvadvertdetailsBrand = findViewById(R.id.tvadvertdetailsBrand);
        tvadvertdetailsModel = findViewById(R.id.tvadvertdetailsModel);
        tvadvertdetailsYear = findViewById(R.id.tvadvertdetailsYear);
        tvadvertdetailsPlate = findViewById(R.id.tvadvertdetailsPlate);
        tvadvertdetailsFuelType = findViewById(R.id.tvadvertdetailsFuelType);
        tvadvertdetailsCasaType = findViewById(R.id.tvadvertdetailsCasaType);
        tvadvertdetailsColor = findViewById(R.id.tvadvertdetailsColor);
        tvadvertdetailsGuarantee = findViewById(R.id.tvadvertdetailsGuarantee);
        tvadvertdetailsKm = findViewById(R.id.tvadvertdetailsKm);
        tvadvertdetailsLuggagevolume = findViewById(R.id.tvadvertdetailsLuggagevolume);
        tvadvertdetailsMotorCapacity = findViewById(R.id.tvadvertdetailsMotorCapacity);
        tvadvertdetailsMotorPower = findViewById(R.id.tvadvertdetailsMotorPower);
        tvadvertdetailsTransmission = findViewById(R.id.tvadvertdetailsTransmission);
        tvadvertdetailsAverageFuelConsumption = findViewById(R.id.tvadvertdetailsAverageFuelConsumption);
        tvadvertdetailsExternalFuelConsumption = findViewById(R.id.tvadvertdetailsExternalFuelConsumption);
        tvadvertdetailsUrbanFuelConsumption = findViewById(R.id.tvadvertdetailsUrbanFuelConsumption);
        tvadvertdetailsFuelTank = findViewById(R.id.tvadvertdetailsFuelTank);
        tvadvertdetailsFullAdress = findViewById(R.id.tvadvertdetailsFullAdress);

        btnadvertdetailadvertiser = findViewById(R.id.btnadvertdetailadvertiser);
        btnadvertcommemnts = findViewById(R.id.btnadvertcommemnts);
        btnadvertaddfavorie = findViewById(R.id.btnadvertaddfavorie);
        btnadverddetailCall = findViewById(R.id.btnadverddetailCall);
        btnadverddetailMessage = findViewById(R.id.btnadverddetailMessage);
        ilandetayslider = findViewById(R.id.ilandetayslider);
        layoutdamagequery=findViewById(R.id.layoutdamagequery);

    }
}
