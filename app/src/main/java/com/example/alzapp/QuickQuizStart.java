package com.example.alzapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QuickQuizStart extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";

    private TextView textViewHighScore;
    private int highScore;

    private Button quiz_start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_quiz_start);

        textViewHighScore = findViewById(R.id.quiz_highScore);
        loadHighScore();

        quiz_start = findViewById(R.id.start_quiz);
        quiz_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuickQuizStart.this,QuizQuestionPage.class);
                startActivityForResult(intent,REQUEST_CODE_QUIZ);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_QUIZ){
           if(resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizQuestionPage.EXTRA_SCORE,0);
                if(score > highScore){
                    updateHighScore(score);
                }
           }
        }

    }

    private void updateHighScore(int highScoreNew){
        highScore = highScoreNew;
        textViewHighScore.setText("Highscore: "+ highScore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highScore);
        editor.apply();
    }


    private void loadHighScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGHSCORE,0);
        textViewHighScore.setText("Highscore: "+ highScore);

    }

}
