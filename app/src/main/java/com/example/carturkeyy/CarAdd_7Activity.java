package com.example.carturkeyy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.carturkeyy.Models.CarAddPojo;
import com.example.carturkeyy.Models.ImagePojo;
import com.example.carturkeyy.RestApi.ManagerAll;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarAdd_7Activity extends AppCompatActivity {

    Button btnChooseımage, btnSaveımage, btnShare;
    ImageView imgsecilenresim;
    Bitmap bitmap;
    String UYEID = "", ILANID = "";

    public static final int GALLERY_REQUEST_CODE = 105;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add_7);
        tanimla();
        butonsclick();
        Bundle bundle = getIntent().getExtras();
        UYEID = bundle.getString("uyeid");
        ILANID = bundle.getString("ilanid");

    }

    void tanimla() {
        btnChooseımage = findViewById(R.id.btnChooseımage);
        btnSaveımage = findViewById(R.id.btnSaveımage);
        btnShare = findViewById(R.id.btnShare);
        imgsecilenresim = findViewById(R.id.ımgsecilenresim);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("You Can Not Go To Details Information Page");
        builder.setPositiveButton("Okey", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void butonsclick() {

        btnChooseımage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Showımage();
            }
        });

        btnSaveımage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
            }
        });


        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(CarAdd_7Activity.this, "Advert Added Successfully", Toast.LENGTH_SHORT).show();
                Clear_CarAddPojo();
            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GALLERY_REQUEST_CODE && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();
            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imgsecilenresim.setImageBitmap(bitmap);
                imgsecilenresim.setVisibility(View.VISIBLE);

            }catch (Exception e)
            {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

        }

    }

    public void Showımage()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }


    String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byt = byteArrayOutputStream.toByteArray();
        String imageToString = Base64.encodeToString(byt, Base64.DEFAULT);
        return imageToString;
    }


    void UploadImage() {
        if (imgsecilenresim.getDrawable()!=null)
        {
            String image= imageToString();
            if(image!=null)
            {
                String uyeid = UYEID;
                String ilanid = ILANID;
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Loading,Please Wait");
                progressDialog.setCancelable(false);
                progressDialog.show();
                Call<ImagePojo> x = ManagerAll.getInstance().AddImage(uyeid, ilanid, image);
                x.enqueue(new Callback<ImagePojo>() {
                    @Override
                    public void onResponse(Call<ImagePojo> call, Response<ImagePojo> response) {
                        if (response.isSuccessful()) {
                            progressDialog.cancel();
                            Toast.makeText(CarAdd_7Activity.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ImagePojo> call, Throwable t) {

                    }
                });
            }
        }
        else
        {
            Toast.makeText(this, "Choose Image For Load", Toast.LENGTH_SHORT).show();
        }


    }

    void Clear_CarAddPojo() {
        CarAddPojo.setYear("");
        CarAddPojo.setBrand("");
        CarAddPojo.setModel("");
        CarAddPojo.setTransmission("");
        CarAddPojo.setFuel("");
        CarAddPojo.setCase_type("");
        CarAddPojo.setPower("");
        CarAddPojo.setCapacity("");
        CarAddPojo.setAdverttitle("");
        CarAddPojo.setPrice("");
        CarAddPojo.setCurrency("");
        CarAddPojo.setKm("");
        CarAddPojo.setPlate("");
        CarAddPojo.setExplanation("");
        CarAddPojo.setColorstatus("");
        CarAddPojo.setChangestatus("");
        CarAddPojo.setTramerstatus("");
        CarAddPojo.setColor("");
        CarAddPojo.setGuarantee("");
        CarAddPojo.setOrtyakittuketimi("");
        CarAddPojo.setLuggagevolume("");
        CarAddPojo.setSehiriciyakittuketimi("");
        CarAddPojo.setSehirdisiyakittuketimi("");
        CarAddPojo.setDepo("");
        CarAddPojo.setCity("");
        CarAddPojo.setDistrict("");
        CarAddPojo.setFulladres("");
        CarAddPojo.setKimden("");
    }

}
