package com.example.jeetmishra.androidquizapp;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeetmishra.androidquizapp.Common1.Common;
import com.example.jeetmishra.androidquizapp.Model.QuestionScore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Done extends AppCompatActivity {
    Button btnTryAgain;
    TextView txtResultScore,getTxtResultQuestion;
    ProgressBar progressBar;

    FirebaseDatabase database;
    DatabaseReference question_score;
    static int totalscore;
    int score;

    public void fireBasePick(DatabaseReference question_score){
        Bundle check = getIntent().getExtras();
        if(check.getString("Online").equals("OnlinePlaying")){
            question_score.child(String.format("%s_%s", Common.currentUser.getUserName(),
                    "02")).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String current_score = dataSnapshot.child("score").getValue(String.class).toString();
                    totalscore = Integer.parseInt(current_score);
                    //score = score +totalscore;
                    //  Toast.makeText(Done.this, "Score On DataChange  Prev: "+String.format("%d",totalscore), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else {
            question_score.child(String.format("%s_%s", Common.currentUser.getUserName(),
                    "01")).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String current_score = dataSnapshot.child("score").getValue(String.class).toString();
                    totalscore = Integer.parseInt(current_score);
                    //score = score +totalscore;
                    //  Toast.makeText(Done.this, "Score On DataChange  Prev: "+String.format("%d",totalscore), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        database = FirebaseDatabase.getInstance();
        question_score= database.getReference("Question_Score");


        txtResultScore = (TextView) findViewById(R.id.txtTotalScore);
        getTxtResultQuestion = (TextView) findViewById(R.id.txtTotalQuestion);
        progressBar = (ProgressBar) findViewById(R.id.doneProgressBar);



        /*question_score.child(String.format("%s_%s", Common.currentUser.getUserName(),
                Common.gameModesNo)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String current_score = dataSnapshot.child("score").getValue(String.class).toString();
                totalscore = Integer.parseInt(current_score);
                //score = score +totalscore;
                Toast.makeText(Done.this, "Score On DataChange : "+String.format("%d",totalscore), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        Bundle extra = getIntent().getExtras();
        if(extra!=null)
        {
           /* question_score.child(String.format("%s_%s", Common.currentUser.getUserName(),
                Common.gameModesNo)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String current_score = dataSnapshot.child("score").getValue(String.class).toString();
                totalscore = Integer.parseInt(current_score);
                //score = score +totalscore;
                Toast.makeText(Done.this, "Score On DataChange : "+String.format("%d",totalscore), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
            fireBasePick(question_score);
            score = extra.getInt("SCORE");
           // Toast.makeText(this, String.valueOf(score), Toast.LENGTH_SHORT).show();
            int totalQuestion = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");
            int fireBaseScore = extra.getInt("FirebaseScore");
            String ifOnline = extra.getString("Online");


            txtResultScore.setText(String.format("SCORE : %d",score));
            getTxtResultQuestion.setText(String.format("PASSED : %d/%d ",correctAnswer,totalQuestion));

            progressBar.setMax(totalQuestion);
            progressBar.setProgress(correctAnswer);
           // Toast.makeText(this, "TotalScore On Firebase Prev: "+String.valueOf(totalscore), Toast.LENGTH_SHORT).show();
            score = score +totalscore;
           // Toast.makeText(this, "Score On Firebase : "+String.valueOf(score), Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, String.valueOf(score), Toast.LENGTH_SHORT).show();
            Log.i("info",String.valueOf(totalscore));


            question_score.child(String.format("%s_%s", Common.currentUser.getUserName(),
                    Common.gameModesNo))
                    .setValue(new QuestionScore(String.format("%s_%s", Common.currentUser.getUserName(),
                            Common.gameModesNo),
                            Common.currentUser.getUserName(),
                            String.valueOf(score),Common.gameModesNo,Common.gameModesName));


        }

        btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Done.this, Home.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
