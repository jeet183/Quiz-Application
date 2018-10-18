package com.example.jeetmishra.androidquizapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    long endtime;

    long currenttime,time;
    FirebaseDatabase database;
    DatabaseReference questions,Time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Questions");

        onlinePlay = (Button) findViewById(R.id.OnlinePlay);
        calendar = Calendar.getInstance();
        loadQuestion(Common.gameModesNo);
        Time = database.getReference("Time").child("Online Time");
        Time.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String stringtime = dataSnapshot.getValue(String.class);
                time = Long.parseLong(stringtime);
                Log.i("Time in log",String.valueOf(time));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
     // DatabaseReference time_id = Time.child("Time").child("Online Time");

          //9:10pm
      // time = (Integer.parseInt(stringtime))




        onlinePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Time in log",String.valueOf(time));
                endtime = time + 180000;
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

