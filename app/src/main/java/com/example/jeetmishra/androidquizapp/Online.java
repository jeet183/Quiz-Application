package com.example.jeetmishra.androidquizapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jeetmishra.androidquizapp.Common1.Common;
import com.example.jeetmishra.androidquizapp.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Collections;

public class Online extends AppCompatActivity {
    Button onlinePlay;
    Calendar calendar;
    long time;
    long endtime;
    long currenttime;
    FirebaseDatabase database;
    DatabaseReference questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Questions");
        onlinePlay = (Button) findViewById(R.id.OnlinePlay);
        calendar = Calendar.getInstance();
        loadQuestion(Common.gameModesNo);
        time = 1539703980000L; //9:10pm
        endtime = time + 180000;
        onlinePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currenttime = calendar.getTimeInMillis();
                if(currenttime>=time&&currenttime<=endtime)
                {

                    Intent intent = new Intent(Online.this,OnlinePlaying.class);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    Toast.makeText(Online.this,"Please wait!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loadQuestion(String gameModesNo) {
        if (Common.questionList.size()>0)
            Common.questionList.clear();
        questions.orderByChild("gameModesNo").equalTo(gameModesNo).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Question ques = postSnapshot.getValue(Question.class);
                    Common.questionList.add(ques);

                }
                Collections.shuffle(Common.questionList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    }

