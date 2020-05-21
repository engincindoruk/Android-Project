package com.example.carturkeyy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carturkeyy.Models.UpdateUserInfoPojo;
import com.example.carturkeyy.Models.UserInfoPojo;
import com.example.carturkeyy.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutInformationActivity extends AppCompatActivity {

    EditText etCheckName, etCheckSurname, etCheckPhone, etCheckTc, etCheckmail, etChecknewpassword, etChecknewpasswordagain;
    Button btncheckandsave;
    String SHARED_PREFERENCES = "com.example.carturkeyy";
    String uyeid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_information);

        tanimla();
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        uyeid = preferences.getString("id", null);

        getInfo();
    }


    void tanimla() {
        etCheckName = findViewById(R.id.etCheckName);
        etCheckSurname = findViewById(R.id.etCheckSurname);
        etCheckPhone = findViewById(R.id.etCheckPhone);
        etCheckTc = findViewById(R.id.etCheckTc);
        etCheckmail = findViewById(R.id.etCheckmail);
        etChecknewpassword = findViewById(R.id.etChecknewpassword);
        etChecknewpasswordagain = findViewById(R.id.etChecknewpasswordagain);
        btncheckandsave = findViewById(R.id.btncheckandsave);

        btncheckandsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInfo();
            }
        });
    }

    void getInfo() {
        Call<UserInfoPojo> x = ManagerAll.getInstance().GetUserInfo(uyeid);
        x.enqueue(new Callback<UserInfoPojo>() {
            @Override
            public void onResponse(Call<UserInfoPojo> call, Response<UserInfoPojo> response) {
                if (response.isSuccessful()) {
                    Log.i("dede", response.body().toString());
                    etCheckName.setText(response.body().getName());
                    etCheckSurname.setText(response.body().getSurname());
                    etCheckPhone.setText(response.body().getPhone());
                    etCheckTc.setText(response.body().getTc());
                    etCheckmail.setText(response.body().getEmail());
                }
            }

            @Override
            public void onFailure(Call<UserInfoPojo> call, Throwable t) {

            }
        });

    }


    void checkInfo() {
        final String name = etCheckName.getText().toString().trim();
        final String surname = etCheckSurname.getText().toString().trim();
        final String phone = etCheckPhone.getText().toString().trim();
        final String tc = etCheckTc.getText().toString().trim();
        final String email = etCheckmail.getText().toString().trim();
        final String password = etChecknewpassword.getText().toString().trim();
        String passwordagain = etChecknewpasswordagain.getText().toString().trim();

        if (name.equals("")) {
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (surname.equals("")) {
            Toast.makeText(this, "Please Enter Your Surname", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone.length() != 11) {
            Toast.makeText(this, "Please Check Your Phone", Toast.LENGTH_SHORT).show();
            return;
        }
        if (tc.length() != 11) {
            Toast.makeText(this, "Please Check Your T.C", Toast.LENGTH_SHORT).show();
            return;
        }
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
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Information");
            builder.setMessage("Do You Want To Change Your Information?");
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Call<UpdateUserInfoPojo> x=ManagerAll.getInstance().UpdateUserInfo(uyeid,name,surname,phone,tc,email,password);
                    x.enqueue(new Callback<UpdateUserInfoPojo>() {
                        @Override
                        public void onResponse(Call<UpdateUserInfoPojo> call, Response<UpdateUserInfoPojo> response) {
                            if (response.body().isTf())
                            {
                                Toast.makeText(AboutInformationActivity.this, response.body().getText(), Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(AboutInformationActivity.this, response.body().getText(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UpdateUserInfoPojo> call, Throwable t) {

                        }
                    });

                    Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }


}
