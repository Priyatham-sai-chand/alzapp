package com.example.alzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuizStartingPage extends AppCompatActivity {


    private Button quizStart;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question_page);

        quizStart = (Button) findViewById(R.id.start_quiz);
        quizStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizStartingPage.this, QuizQuestionPage.class);
                startActivity(intent);
            }
        });
    }


}
