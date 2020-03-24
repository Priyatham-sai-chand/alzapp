package com.example.alzapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;

/*******
 Created on: 21/01/2020

 By: B.Priyatham sai chand


 ********/


public class JumbleActivity extends AppCompatActivity {

    private TextView wordTv;
    private EditText wordEnteredTv;
    private Button validate, newGame;
    private String wordToFind;
    private int score = 0;
    private TextView score_dis;
    private TextView timer;
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    private Button pauser;
    private Button resumer;
    private long elapsedMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumble);



            pauser =  findViewById(R.id.pause);
            resumer = findViewById(R.id.resume);
            timer = findViewById(R.id.time);
            score_dis = findViewById(R.id.score);
            wordTv = findViewById(R.id.wordTv);
            wordEnteredTv =findViewById(R.id.wordEnteredTv);
            validate = findViewById(R.id.validate);
            newGame = findViewById(R.id.newGame);
            newGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newGame();
                }
            });



            Button back = (Button) findViewById(R.id.back);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            validate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     validate();
                }
            });

        Intent intent = getIntent();
        elapsedMillis = intent.getLongExtra("jumble_elapsed_millis", 0);
        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        newGame();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (score == 5) {
                    elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedMillis);
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedMillis);
                    timer.setText("time : " + minutes + ":" + seconds);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("jumble_elapsed_millis", elapsedMillis);
                    setResult(RESULT_OK, resultIntent);
                    finish();



                }
            }
        });

        pauser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseChronometer();
            }
        });

        resumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChronometer();
            }
        });



        }



        private void validate() {
            String w = wordEnteredTv.getText().toString();

            if (wordToFind.equals(w)) {
                Toast.makeText(this, "Congratulations ! You found the word " + wordToFind, Toast.LENGTH_SHORT).show();

                score += 1;
                score_dis.setText("Score : " + score);
                newGame();


            } else {
                Toast.makeText(this, "Retry !", Toast.LENGTH_SHORT).show();
            }
        }

        private void newGame() {
            wordToFind = Jumble.randomWord();
            String wordShuffled = Jumble.shuffleWord(wordToFind);
            wordTv.setText(wordShuffled);
            wordEnteredTv.setText("");
            startChronometer();


        }
        private void reset(){

            score = 0;
            newGame();
            resetChronometer();

        }


    public void startChronometer() {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer() {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void resetChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }









}

