package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carturkeyy.Models.RegisterPojo;
import com.example.carturkeyy.Models.RegisterUserPojo;
import com.example.carturkeyy.RestApi.BaseManager;
import com.example.carturkeyy.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterScreen2 extends AppCompatActivity {

    EditText etEmailReg, etPasswordReg;
    Button btnRegisterReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen2);
        tanimla();

        etEmailReg.setText("");
        etPasswordReg.setText("");
        etEmailReg.setText(RegisterUserPojo.getUsermail());
        etPasswordReg.setText(RegisterUserPojo.getUserpassword());
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),RegisterScreen.class);
        startActivity(intent);
    }

    void tanimla() {
        etEmailReg = findViewById(R.id.etEmailReg);
        etPasswordReg = findViewById(R.id.etPasswordReg);
        btnRegisterReg = findViewById(R.id.btnRegisterReg);
        btnRegisterReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control();
            }
        });
    }

    void control() {
        String email = etEmailReg.getText().toString().trim();
        String password = etPasswordReg.getText().toString().trim();

        if (email.equals("") || !(email.contains("@")) || !(email.endsWith(".com"))) {
            Toast.makeText(this, "Please Check Your Email", Toast.LENGTH_SHORT).show();
            etEmailReg.requestFocus();
            return;
        }
        if (password.equals("") || password.length() < 3) {
            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            etPasswordReg.requestFocus();
            return;
        }
        else {
            RegisterUserPojo.setUsermail(email);
            RegisterUserPojo.setUserpassword(password);
            kaydet();
        }
    }

    void kaydet()
    {
        String name=RegisterUserPojo.getName();
        String surname=RegisterUserPojo.getSurname();
        String phone=RegisterUserPojo.getPhone();
        String tc=RegisterUserPojo.getTc();
        final String email=RegisterUserPojo.getUsermail();
        String password=RegisterUserPojo.getUserpassword();

        Call<RegisterPojo> x= ManagerAll.getInstance().Register(name,surname,phone,tc,email,password);
        x.enqueue(new Callback<RegisterPojo>() {
            @Override
            public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                if (response.body().isTf()==true)
                {
                    Toast.makeText(RegisterScreen2.this, response.body().getResult(), Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),VerificationActivity.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                    clearinRegisterUser();
                    finish();
                }
                else{
                    Toast.makeText(RegisterScreen2.this, response.body().getResult(), Toast.LENGTH_LONG).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<RegisterPojo> call, Throwable t) {

            }
        });
    }
    void clearinRegisterUser(){
        RegisterUserPojo.setName("");
        RegisterUserPojo.setSurname("");
        RegisterUserPojo.setPhone("");
        RegisterUserPojo.setTc("");
        RegisterUserPojo.setUsermail("");
        RegisterUserPojo.setUserpassword("");
    }
}
