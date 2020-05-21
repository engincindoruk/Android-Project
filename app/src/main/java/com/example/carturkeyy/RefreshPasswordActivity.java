package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carturkeyy.Models.ForgotPasswordPojo;
import com.example.carturkeyy.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RefreshPasswordActivity extends AppCompatActivity {

    EditText etForgotEmail,etForgotnewpassword,etForgotnewpasswordagain;
    Button btnForgotSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_password);

        tanimla();
    }
    void tanimla()
    {
        etForgotEmail=findViewById(R.id.etForgotEmail);
        etForgotnewpassword=findViewById(R.id.etForgotnewpassword);
        etForgotnewpasswordagain=findViewById(R.id.etForgotnewpasswordagain);
        btnForgotSave=findViewById(R.id.btnForgotSave);
        btnForgotSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }
    void check()
    {
        final String email = etForgotEmail.getText().toString().trim();
        final String password = etForgotnewpassword.getText().toString().trim();
        String passwordagain = etForgotnewpasswordagain.getText().toString().trim();

        if (email.equals("") || !(email.contains("@")) || !(email.endsWith(".com"))) {
            Toast.makeText(this, "Please Check Your Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals("") || password.length() < 3) {
            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (passwordagain.equals("") || passwordagain.length() < 3) {
            Toast.makeText(this, "Please Check Your Verify Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!passwordagain.equals(password.trim())) {
            Toast.makeText(this, "Not Same Entered Password to Verify Password", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Call<ForgotPasswordPojo>x= ManagerAll.getInstance().ForgotPassword(email);
            x.enqueue(new Callback<ForgotPasswordPojo>() {
                @Override
                public void onResponse(Call<ForgotPasswordPojo> call, Response<ForgotPasswordPojo> response) {
                    if (response.body().isTf())
                    {
                        Toast.makeText(RefreshPasswordActivity.this,response.body().getText(), Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(getApplicationContext(),VeriftForgotPasswordActivity.class);
                        intent.putExtra("mail",email);
                        intent.putExtra("password",password);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(RefreshPasswordActivity.this,response.body().getText(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ForgotPasswordPojo> call, Throwable t) {

                }
            });

        }
    }
}

