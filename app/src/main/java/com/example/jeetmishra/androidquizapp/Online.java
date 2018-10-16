package com.example.jeetmishra.androidquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class Online extends AppCompatActivity {
    Button onlinePlay;
    Calendar calendar;
    long time;
    long endtime;
    long currenttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        onlinePlay = (Button) findViewById(R.id.OnlinePlay);
        calendar = Calendar.getInstance();
        time = 1539618000000L; //9:10pm
        endtime = time + 600000;
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
}
