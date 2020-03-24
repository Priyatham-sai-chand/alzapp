package com.example.alzapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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
    private long jumble_elapsed_millis = 0;
    private String username;
    private TextView username_text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play_menu);


        jumble = findViewById(R.id.jumble);
        tilematch =  findViewById(R.id.tilematch);
        Intent intent = getIntent();
       username = intent.getStringExtra(login.EXTRA_TEXT);
       username_text = findViewById(R.id.username);
       username_text.setText("username : "+ username);

        jumble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuickPlayMenu.this, JumbleActivity.class);
                intent.putExtra("jumble_elapsed_millis", jumble_elapsed_millis);
                startActivityForResult(intent, 1);

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

        if(jumble_elapsed_millis != 0) {

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            Intent intent = new Intent(QuickPlayMenu.this, UserAreaActivity.class);
                            startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(QuickPlayMenu.this);
                            builder.setMessage("Register Failed")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            JumbleSender jumble_sender = new JumbleSender(username, jumble_elapsed_millis, responseListener);
            RequestQueue queue = Volley.newRequestQueue(QuickPlayMenu.this);
            queue.add(jumble_sender);
        }




    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
               jumble_elapsed_millis = data.getLongExtra("jumble_elapsed_millis", 0);



            }
            if (resultCode == RESULT_CANCELED) {


            }
        }
    }

}
