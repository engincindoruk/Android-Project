package com.example.carturkeyy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carturkeyy.Models.DogrulamaPojo;
import com.example.carturkeyy.Models.RegisterUserPojo;
import com.example.carturkeyy.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationActivity extends AppCompatActivity {

    EditText etverificationcode,etverificationMail;
    Button btnVerifytoCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        tanimla();
        Bundle bundle=getIntent().getExtras();
        etverificationMail.setText(bundle.getString("email"));


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("You Have To Enter Code From Your Email");
        builder.setPositiveButton("Okey", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    void tanimla()
    {
        etverificationcode=findViewById(R.id.etverificationcode);
        etverificationMail=findViewById(R.id.etverificationMail);
        btnVerifytoCode=findViewById(R.id.btnVerifytoCode);
        btnVerifytoCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }
    void check()
    {
        String verifycode=etverificationcode.getText().toString().trim();
        final String email=etverificationMail.getText().toString().trim();
        if (verifycode.equals(""))
        {
            Toast.makeText(this, "Please Enter The Verify Code From Your Mail", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Call<DogrulamaPojo> x= ManagerAll.getInstance().Verify(email,verifycode);
            x.enqueue(new Callback<DogrulamaPojo>() {
                @Override
                public void onResponse(Call<DogrulamaPojo> call, Response<DogrulamaPojo> response) {
                    if (response.body().isTf()==true)
                    {
                        Toast.makeText(VerificationActivity.this, response.body().getResult(), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("email",email);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(VerificationActivity.this, response.body().getResult(), Toast.LENGTH_SHORT).show();
                        etverificationcode.setText("");
                    }
                }

                @Override
                public void onFailure(Call<DogrulamaPojo> call, Throwable t) {

                }
            });
        }

    }

}
