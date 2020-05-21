package com.example.carturkeyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carturkeyy.Models.RegisterUserPojo;

public class RegisterScreen extends AppCompatActivity {

    EditText etName,etSurname,etPhone,etTc;
    Button btnSaveUserInfo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        tanimla();

        etName.setText("");
        etSurname.setText("");
        etPhone.setText("");
        etTc.setText("");
        etName.setText(RegisterUserPojo.getName());
        etSurname.setText(RegisterUserPojo.getSurname());
        etPhone.setText(RegisterUserPojo.getPhone());
        etTc.setText(RegisterUserPojo.getTc());
    }
    void tanimla()
    {
        etName=findViewById(R.id.etName);
        etSurname=findViewById(R.id.etSurname);
        etPhone=findViewById(R.id.etPhone);
        etTc=findViewById(R.id.etTc);
        btnSaveUserInfo1=findViewById(R.id.btnSaveUserInfo1);

        btnSaveUserInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control();
            }
        });
    }

    void control()
    {
        String name=etName.getText().toString().trim();
        String surname=etSurname.getText().toString().trim();
        String phone=etPhone.getText().toString().trim();
        String tc=etTc.getText().toString().trim();

        if (name.equals(""))
        {
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
            etName.requestFocus();
            return;
        }
        if (surname.equals(""))
        {
            Toast.makeText(this, "Please Enter Your Surname", Toast.LENGTH_SHORT).show();
            etSurname.requestFocus();
            return;
        }
        if (phone.length()!=11)
        {
            Toast.makeText(this, "Please Check Your Phone", Toast.LENGTH_SHORT).show();
            etPhone.requestFocus();
            return;
        }
        if (tc.length()!=11)
        {
            Toast.makeText(this, "Please Check Your T.C", Toast.LENGTH_SHORT).show();
            etTc.requestFocus();
            return;
        }
        else {
            RegisterUserPojo.setName(name);
            RegisterUserPojo.setSurname(surname);
            RegisterUserPojo.setPhone(phone);
            RegisterUserPojo.setTc(tc);
            Intent intent=new Intent(getApplicationContext(),RegisterScreen2.class);
            startActivity(intent);

        }
    }

}

