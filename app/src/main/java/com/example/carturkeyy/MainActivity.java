package com.example.carturkeyy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carturkeyy.Models.CarAddPojo;
import com.example.carturkeyy.Models.LoginPojo;
import com.example.carturkeyy.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnRegister, btnLogin;
    CheckBox cbRememberme;
    TextView tvforgotpassword;
    Bundle bundle;
    String SHARED_PREFERENCES="com.example.carturkeyy";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();


        bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            etEmail.setText(bundle.getString("email"));
        }

        SharedPreferences preferences=getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        etEmail.setText(preferences.getString("Email",null));
        etPassword.setText(preferences.getString("Password",null));
        String cbchecked =  preferences.getString("checkbox","0");

        if (cbchecked.equals("1"))
        {
            cbRememberme.setChecked(true);
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Exit");
        builder.setMessage("Do You Want To Exit The Application?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    void tanimla() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        cbRememberme=findViewById(R.id.cbRememberme);
        tvforgotpassword=findViewById(R.id.tvforgotpassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterScreen.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
                progressDialog.cancel();
            }
        });
        tvforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RefreshPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    void check()
    {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (email.equals("") || !(email.contains("@")) || !(email.endsWith(".com"))) {
            Toast.makeText(this, "Please Check Your Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals("") || password.length() < 3) {
            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            login();
            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading,Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }
    }

    void login() {
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        Call<LoginPojo> x = ManagerAll.getInstance().Login(email, password);
        x.enqueue(new Callback<LoginPojo>() {
            @Override
            public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    if (cbRememberme.isChecked())
                    {
                        CarAddPojo.setUyeid(response.body().getId());
                        CarAddPojo.setKimden(response.body().getName()+" "+response.body().getSurname());
                        SharedPreferences.Editor editor=getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE).edit();
                        editor.putString("Email",email);
                        editor.putString("Password",password);
                        editor.putString("checkbox","1");
                        editor.putString("id",response.body().getId());
                        editor.commit();
                        finish();
                    }
                    else {
                        SharedPreferences.Editor editor=getSharedPreferences("SHARED_PREFERENCES",MODE_PRIVATE).edit();
                        editor.clear();
                        editor.commit();
                        finish();
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginPojo> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(MainActivity.this, "Please Check Your Mail And Password", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
