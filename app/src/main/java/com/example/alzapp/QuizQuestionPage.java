package com.example.alzapp;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

public class QuizQuestionPage extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount ;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;


    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question_page);

        textViewQuestion = findViewById(R.id.quiz_question);
        textViewScore = findViewById(R.id.quiz_score);
        textViewQuestionCount = findViewById(R.id.quiz_question_counter);
        textViewCountDown = findViewById(R.id.quiz_Timer);
        rbGroup = findViewById(R.id.quiz_radio_group);
        rb1 = findViewById(R.id.radioButton30);
        rb2 = findViewById(R.id.radioButton31);
        rb3 = findViewById(R.id.radioButton32);
        buttonConfirmNext = findViewById(R.id.quiz_confirm_next);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

    }

    private void showNextQuestion(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

            if ( questionCounter < questionCountTotal){
                currentQuestion = questionList.get(questionCounter);

                textViewQuestion.setText(currentQuestion.getQuestion());
                rb1.setText(currentQuestion.getOption1());
                rb2.setText(currentQuestion.getOption2());
                rb3.setText(currentQuestion.getOption3());

                questionCounter++;
                textViewQuestionCount.setText("Question: "+ questionCounter + "/" + questionCountTotal);
                answered = false;
                buttonConfirmNext.setText("Confirm");
            }

            else {
             finish();
            }
    }
}