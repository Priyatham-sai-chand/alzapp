package com.example.alzapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

/*******
 Created on: 21/01/2020

 By: Rishab Kulkarni & Shaik Idrisulla


 ********/

public class QuickPlayMenu extends AppCompatActivity {

    private Button jumble;
    private Button tilematch;
    private Button game3;
    private Button game4;
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play_menu);

        jumble = (Button) findViewById(R.id.jumble);
        tilematch = (Button) findViewById(R.id.tilematch);

        jumble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuickPlayMenu.this,JumbleActivity.class);
                startActivity(intent);

            }
        });

        tilematch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuickPlayMenu.this,TileMatchingActivity.class);
                startActivity(intent);

            }
        });

        Button back = (Button) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }
    public void startChronometer(View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
}
