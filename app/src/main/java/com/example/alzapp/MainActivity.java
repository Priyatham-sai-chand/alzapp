package com.example.alzapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
/*******
 Created on: 21/01/2020

 By: B.Priyatham sai chand, Rishab Kulkarni & Shaik Idrisulla


 ********/


public class MainActivity extends AppCompatActivity{

    private TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(MainActivity.this, WelcomeActivity.class));


    }




}


