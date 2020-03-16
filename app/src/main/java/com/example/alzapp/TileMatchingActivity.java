package com.example.alzapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alzapp.R;

import java.util.Arrays;
import java.util.Collections;

/*******
 Created on: 21/01/2020

 By: Rishab Kulkarni & Shaik Idrisulla


 ********/

public class TileMatchingActivity extends AppCompatActivity {

    TextView counter;
    ImageView i11,i12,i13,i14;
    ImageView i21,i22,i23,i24;
    ImageView i31,i32,i33,i34;
    ImageView i41,i42,i43,i44;

    Integer[] cardsArray={101,102,103,104,105,106,107,108,201,202,203,204,205,206,207,208};

       int  img101,img102,img103,img104,img105,img106,img107,img108,
            img201,img202,img203,img204,img205,img206,img207,img208,
            firstCard,secondCard,clickedFirst,clickedSecond,cardNumber=1,playerMoves=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_matching);
        counter=(TextView) findViewById(R.id.counter1);

        i11=(ImageView) findViewById(R.id.i11);
        i12=(ImageView) findViewById(R.id.i12);
        i13=(ImageView) findViewById(R.id.i13);
        i14=(ImageView) findViewById(R.id.i14);

        i21=(ImageView) findViewById(R.id.i21);
        i22=(ImageView) findViewById(R.id.i22);
        i23=(ImageView) findViewById(R.id.i23);
        i24=(ImageView) findViewById(R.id.i24);

        i31=(ImageView) findViewById(R.id.i31);
        i32=(ImageView) findViewById(R.id.i32);
        i33=(ImageView) findViewById(R.id.i33);
        i34=(ImageView) findViewById(R.id.i34);

        i41=(ImageView) findViewById(R.id.i41);
        i42=(ImageView) findViewById(R.id.i42);
        i43=(ImageView) findViewById(R.id.i43);
        i44=(ImageView) findViewById(R.id.i44);

        i11.setTag("0");
        i12.setTag("1");
        i13.setTag("2");
        i14.setTag("3");

        i21.setTag("4");
        i22.setTag("5");
        i23.setTag("6");
        i24.setTag("7");

        i31.setTag("8");
        i32.setTag("9");
        i33.setTag("10");
        i34.setTag("11");

        i41.setTag("12");
        i42.setTag("13");
        i43.setTag("14");
        i44.setTag("15");

        frontCards();

        Collections.shuffle(Arrays.asList(cardsArray));

        i11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i11, theCard);

            }
        });

        i12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i12, theCard);
            }
        });

        i13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i13, theCard);
            }
        });

        i14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i14, theCard);
            }
        });

        i21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i21, theCard);
            }
        });

        i22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i22, theCard);

            }
        });

        i23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i23, theCard);
            }
        });

        i24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i24, theCard);
            }
        });

        i31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i31, theCard);
            }
        });

        i32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i32, theCard);
            }
        });

        i33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i33, theCard);
            }
        });

        i34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i34, theCard);

            }
        });

        i41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i41, theCard);
            }
        });

        i42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i42, theCard);
            }
        });

        i43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i43, theCard);       }
        });

        i44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                doStuff(i44, theCard);
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

    private void doStuff(ImageView img,int card){
        if(cardsArray[card]==101){
            img.setImageResource(img101);
        }
        else if(cardsArray[card]==102){
            img.setImageResource(img102);
        }
        else if(cardsArray[card]==103){
            img.setImageResource(img103);
        }
        else if(cardsArray[card]==104){
            img.setImageResource(img104);
        }
        else if(cardsArray[card]==105){
            img.setImageResource(img105);
        }
        else if(cardsArray[card]==106){
            img.setImageResource(img106);
        }
        else if(cardsArray[card]==107){
            img.setImageResource(img107);
        }
        else if(cardsArray[card]==108){
            img.setImageResource(img108);
        }
        else if(cardsArray[card]==201){
            img.setImageResource(img201);
        }
        else if(cardsArray[card]==202){
            img.setImageResource(img202);
        }
        else if(cardsArray[card]==203){
            img.setImageResource(img203);
        }
        else if(cardsArray[card]==204){
            img.setImageResource(img204);
        }
        else if(cardsArray[card]==205){
            img.setImageResource(img205);
        }
        else if(cardsArray[card]==206){
            img.setImageResource(img206);
        }
        else if(cardsArray[card]==207){
            img.setImageResource(img207);
        }
        else if(cardsArray[card]==208){
            img.setImageResource(img208);
        }
        playerMoves++;
        counter.setText("MOVES= "+playerMoves/2);

        if (cardNumber==1){
            firstCard=cardsArray[card];
            if(firstCard>200){
                firstCard=firstCard-100;
            }
            cardNumber=2;
            clickedFirst=card;
            img.setEnabled(false);
        }
        else if(cardNumber==2){
            secondCard=cardsArray[card];
            if(secondCard>200){
                secondCard=secondCard-100;
            }
            cardNumber=1;
            clickedSecond=card;

            i11.setEnabled(false);
            i12.setEnabled(false);
            i13.setEnabled(false);
            i14.setEnabled(false);
            i21.setEnabled(false);
            i22.setEnabled(false);
            i23.setEnabled(false);
            i24.setEnabled(false);
            i31.setEnabled(false);
            i32.setEnabled(false);
            i33.setEnabled(false);
            i34.setEnabled(false);
            i41.setEnabled(false);
            i42.setEnabled(false);
            i43.setEnabled(false);
            i44.setEnabled(false);

            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            },1000);

        }

    }

    private void calculate(){
        if(firstCard==secondCard){
            if(clickedFirst==0){
                i11.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==1){
                i12.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==2){
                i13.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==3){
                i14.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==4){
                i21.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==5){
                i22.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==6){
                i23.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==7){
                i24.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==8){
                i31.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==9){
                i32.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==10){
                i33.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==11){
                i34.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==12){
                i41.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==13){
                i42.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==14){
                i43.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==15){
                i44.setVisibility(View.INVISIBLE);
            }


            if(clickedSecond==0){
                i11.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==1){
                i12.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==2){
                i13.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==3){
                i14.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==4){
                i21.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==5){
                i22.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==6){
                i23.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==7){
                i24.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==8){
                i31.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==9){
                i32.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==10){
                i33.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==11){
                i34.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==12){
                i41.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==13){
                i42.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==14){
                i43.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==15){
                i44.setVisibility(View.INVISIBLE);
            }


        }
        else {
            i11.setImageResource(R.drawable.question);
            i12.setImageResource(R.drawable.question);
            i13.setImageResource(R.drawable.question);
            i14.setImageResource(R.drawable.question);
            i21.setImageResource(R.drawable.question);
            i22.setImageResource(R.drawable.question);
            i23.setImageResource(R.drawable.question);
            i24.setImageResource(R.drawable.question);
            i31.setImageResource(R.drawable.question);
            i32.setImageResource(R.drawable.question);
            i33.setImageResource(R.drawable.question);
            i34.setImageResource(R.drawable.question);
            i41.setImageResource(R.drawable.question);
            i42.setImageResource(R.drawable.question);
            i43.setImageResource(R.drawable.question);
            i44.setImageResource(R.drawable.question);
        }

        i11.setEnabled(true);
        i12.setEnabled(true);
        i13.setEnabled(true);
        i14.setEnabled(true);
        i21.setEnabled(true);
        i22.setEnabled(true);
        i23.setEnabled(true);
        i24.setEnabled(true);
        i31.setEnabled(true);
        i32.setEnabled(true);
        i33.setEnabled(true);
        i34.setEnabled(true);
        i41.setEnabled(true);
        i42.setEnabled(true);
        i43.setEnabled(true);
        i44.setEnabled(true);

        checkEnd();
    }

    private void checkEnd(){
        if(i11.getVisibility()==View.INVISIBLE &&
                i12.getVisibility()==View.INVISIBLE &&
                i13.getVisibility()==View.INVISIBLE &&
                i14.getVisibility()==View.INVISIBLE &&
                i21.getVisibility()==View.INVISIBLE &&
                i22.getVisibility()==View.INVISIBLE &&
                i23.getVisibility()==View.INVISIBLE &&
                i24.getVisibility()==View.INVISIBLE &&
                i31.getVisibility()==View.INVISIBLE &&
                i32.getVisibility()==View.INVISIBLE &&
                i33.getVisibility()==View.INVISIBLE &&
                i34.getVisibility()==View.INVISIBLE &&
                i41.getVisibility()==View.INVISIBLE &&
                i42.getVisibility()==View.INVISIBLE &&
                i43.getVisibility()==View.INVISIBLE &&
                i44.getVisibility()==View.INVISIBLE
        ){
            AlertDialog.Builder message= new AlertDialog.Builder(TileMatchingActivity.this);
            message.setMessage("GAME OVER!!\nMOVES= "+counter)
                    .setCancelable(false)
                    .setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

        }
    }

    private void frontCards(){
        img101=R.drawable.bear;
        img102=R.drawable.dog;
        img103=R.drawable.rat;
        img104=R.drawable.elephant;
        img105=R.drawable.lion;
        img106=R.drawable.zebra;
        img107=R.drawable.chicken;
        img108=R.drawable.cow;
        img201=R.drawable.bear;
        img202=R.drawable.dog;
        img203=R.drawable.rat;
        img204=R.drawable.elephant;
        img205=R.drawable.lion;
        img206=R.drawable.zebra;
        img207=R.drawable.chicken;
        img208=R.drawable.cow;
    }
}
