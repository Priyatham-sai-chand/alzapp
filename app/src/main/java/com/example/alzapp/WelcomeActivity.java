package com.example.alzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
/*******
 Created on: 21/01/2020

 By: B.Priyatham sai chand


 ********/

public class WelcomeActivity extends AppCompatActivity {

    private Button wel_login;
    private Button wel_register;
    private Button wel_quick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        wel_login = (Button) findViewById(R.id.login);
        wel_register = (Button) findViewById(R.id.register);
        wel_quick = (Button) findViewById(R.id.quick);

        wel_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,login.class);
                startActivity(intent);
            }
        });

        wel_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,registration.class);
                startActivity(intent);
            }
        });
        wel_quick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,QuickPlayMenu.class);
                startActivity(intent);
            }
        });




    }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}
