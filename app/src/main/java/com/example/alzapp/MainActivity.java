package com.example.alzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*******
 Created on: 21/01/2020

 By: B.Priyatham sai chand, Rishab Kulkarni & Shaik Idrisulla


 ********/


public class MainActivity extends AppCompatActivity {

    private TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(MainActivity.this, WelcomeActivity.class));


    }



}


