package com.example.alzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuickQuizStart extends AppCompatActivity {

    private Button quiz_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_quiz_start);

        quiz_start = findViewById(R.id.start_quiz);
        quiz_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuickQuizStart.this,QuizQuestionPage.class);
                startActivity(intent);
            }
        });

    }
}
