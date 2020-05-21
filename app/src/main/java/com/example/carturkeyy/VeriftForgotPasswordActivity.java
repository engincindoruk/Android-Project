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

import com.example.carturkeyy.Models.SetForgotPasswordPojo;
import com.example.carturkeyy.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VeriftForgotPasswordActivity extends AppCompatActivity {

    EditText etforgotverificationMail,etforgotverificationcode;
    Button btnforgotVerifytoCode;
    String mail,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verift_forgot_password);

        tanimla();
        Bundle  bundle=getIntent().getExtras();
        mail=bundle.getString("mail");
        password=bundle.getString("password");
        etforgotverificationMail.setText(mail);
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
        etforgotverificationMail=findViewById(R.id.etforgotverificationMail);
        etforgotverificationcode=findViewById(R.id.etforgotverificationcode);
        btnforgotVerifytoCode=findViewById(R.id.btnforgotVerifytoCode);
        btnforgotVerifytoCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }
    void check()
    {
        String verifycode=etforgotverificationcode.getText().toString().trim();
        final String email=etforgotverificationMail.getText().toString().trim();
        if (verifycode.equals(""))
        {
            Toast.makeText(this, "Please Enter The Verify Code From Your Mail", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Call<SetForgotPasswordPojo> x= ManagerAll.getInstance().SetForgotPassword(mail,password,verifycode);
            x.enqueue(new Callback<SetForgotPasswordPojo>() {
                @Override
                public void onResponse(Call<SetForgotPasswordPojo> call, Response<SetForgotPasswordPojo> response) {
                    if (response.body().isTf())
                    {
                        Toast.makeText(VeriftForgotPasswordActivity.this, response.body().getResult(), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("email",email);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(VeriftForgotPasswordActivity.this, response.body().getResult(), Toast.LENGTH_SHORT).show();
                        etforgotverificationcode.setText("");
                    }
                }

                @Override
                public void onFailure(Call<SetForgotPasswordPojo> call, Throwable t) {

                }
            });

        }

    }
}
