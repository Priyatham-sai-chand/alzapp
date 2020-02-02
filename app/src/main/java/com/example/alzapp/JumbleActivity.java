package com.example.alzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;
import java.util.zip.Inflater.*;

/*******
 Created on: 21/01/2020

 By: B.Priyatham sai chand


 ********/


public class JumbleActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView wordTv;
    private EditText wordEnteredTv;
    private Button validate, newGame;
    private String wordToFind;
    private int score = 0;
    private TextView score_dis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumble);



            score_dis = (TextView) findViewById(R.id.score);
            wordTv = (TextView) findViewById(R.id.wordTv);
            wordEnteredTv = (EditText) findViewById(R.id.wordEnteredTv);
            validate = (Button) findViewById(R.id.validate);
            validate.setOnClickListener(this);
            newGame = (Button) findViewById(R.id.newGame);
            newGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reset();
                    score_dis.setText("Score : " + score);
                }
            });

            newGame();

            Button back = (Button) findViewById(R.id.back);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });


        }

        @Override
        public void onClick(View view) {
            if (view == validate) {

                validate();
            } else if (view == newGame) {
                score = 0;
                newGame();


            }
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



        }
        private void reset(){

            score = 0;
            newGame();

        }






    }

