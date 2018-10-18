package com.example.jeetmishra.androidquizapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class ChangeTime extends AppCompatActivity {
    Button timechanger,back,LogOut;
    MaterialEditText onlinetime;
    String time;
    DatabaseReference databaseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_time);
        databaseTime= FirebaseDatabase.getInstance().getReference("Time");
        timechanger =(Button) findViewById(R.id.time_changer);
        back =(Button) findViewById(R.id.back3);
        LogOut =(Button) findViewById(R.id.logout2);
        onlinetime = (MaterialEditText) findViewById(R.id.change_time_of_quiz);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog dialog;
                dialog = new ProgressDialog(ChangeTime.this);
                dialog.setTitle("User");
                dialog.setMessage("Please Wait");
                dialog.show();

                Intent intent = new Intent(ChangeTime.this,SignUp.class);
                startActivity(intent);
                finish();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangeTime.this,Questions.class);
                startActivity(intent);
                finish();
            }
        });
        timechanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = onlinetime.getText().toString();
                databaseTime.child("Online Time").setValue(time);


                onlinetime.getText().clear();
            }
        });
    }
}
