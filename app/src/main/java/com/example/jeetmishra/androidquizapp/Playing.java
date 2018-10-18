package com.example.jeetmishra.androidquizapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeetmishra.androidquizapp.Common1.Common;

import java.util.Calendar;

public class Playing extends AppCompatActivity implements View.OnClickListener{

    final static long INTERVAL=1000;
    final static long TIMEOUT=7000;
    int progressValue=0;

    CountDownTimer mCountDown;
    Calendar calendar;

    int index=0,score=0,thisQuestion=0,totalQuestion,Answer;

    ProgressBar progressBar;
    Button btnA,btnB,btnC,btnD,done;
    TextView txtScore,txtQuestionNum,question_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);


        txtScore=(TextView)findViewById(R.id.txtScore);
        txtQuestionNum=(TextView)findViewById(R.id.txtTotalQuestion);
        question_text=(TextView) findViewById(R.id.question_text);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        btnA=(Button)findViewById(R.id.btnAnswerA);
        btnB=(Button)findViewById(R.id.btnAnswerB);
        btnC=(Button)findViewById(R.id.btnAnswerC);
        btnD=(Button)findViewById(R.id.btnAnswerD);
        calendar =  Calendar.getInstance();
        done=(Button ) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent done = new Intent(Playing.this,Done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE",score);
                dataSend.putInt("TOTAL",totalQuestion);
                dataSend.putInt("CORRECT",Answer);
                done.putExtras(dataSend);
                startActivity(done);
                finish();

            }
        });
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        mCountDown.cancel();
        if (index<totalQuestion) {
            Button clickedButton = (Button) view;

            if (clickedButton.getText().equals(Common.questionList.get(index).getAnswer()))
            {
                score+=10;
                Answer++;

                showQuestion(++index);
            }
            else {
                Toast.makeText(Playing.this, "Keep Playing", Toast.LENGTH_SHORT).show();
                showQuestion(++index);
            }

            txtScore.setText(String.format("%d",score));
        }
    }

    private void showQuestion(int index) {
        if(index<totalQuestion)
        {
            thisQuestion++;
            txtQuestionNum.setText(String.format("%d /%d",thisQuestion,totalQuestion));
            progressBar.setProgress(0);
            progressValue=0;
            question_text.setText(Common.questionList.get(index).getQuestion());
            btnA.setText(Common.questionList.get(index).getQption1());
            btnB.setText(Common.questionList.get(index).getOption2());
            btnC.setText(Common.questionList.get(index).getOption3());
            btnD.setText(Common.questionList.get(index).getOption4());

            mCountDown.start();

        }
        else {
            Intent intent = new Intent(Playing.this,Done.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE",score);
            dataSend.putInt("TOTAL",totalQuestion);
            dataSend.putInt("CORRECT",Answer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }
           }

    @Override
    protected void onResume() {
        super.onResume();
        totalQuestion=10;
        mCountDown = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long minisec) {
                progressBar.setProgress(progressValue);
                progressValue++;

            }

            @Override
            public void onFinish() {
            mCountDown.cancel();


            showQuestion(++index);
            }

        };
        showQuestion(index);
    }
}
