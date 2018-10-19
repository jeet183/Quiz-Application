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

public class OnlinePlaying extends AppCompatActivity implements View.OnClickListener {
    final static long INTERVAL=1000;
    final static long TIMEOUT=7000;
    int progressValue=0;

    CountDownTimer mCountDown;

    int index=0,score=0,thisQuestion=0,totalQuestion,Answer;

    ProgressBar progressBar;
    Button btnA,btnB,btnC,btnD;
    TextView txtScore,txtQuestionNum,question_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_playing);


        txtScore=(TextView)findViewById(R.id.onlinetxtScore);
        txtQuestionNum=(TextView)findViewById(R.id.onlinetxtTotalQuestion);
        question_text=(TextView) findViewById(R.id.onlinequestion_text);

        progressBar=(ProgressBar)findViewById(R.id.onlineprogressBar);
        btnA=(Button)findViewById(R.id.onlinebtnAnswerA);
        btnB=(Button)findViewById(R.id.onlinebtnAnswerB);
        btnC=(Button)findViewById(R.id.onlinebtnAnswerC);
        btnD=(Button)findViewById(R.id.onlinebtnAnswerD);
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
               Toast.makeText(OnlinePlaying.this, "Wrong Answer : You Are Eliminated!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OnlinePlaying.this,Done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE",score);
                dataSend.putInt("TOTAL",totalQuestion);
                dataSend.putString("Online","OnlinePlaying");
                dataSend.putInt("CORRECT",Answer);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
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
            Intent intent = new Intent(OnlinePlaying.this,Done.class);
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
        totalQuestion= 10;
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

