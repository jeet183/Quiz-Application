package com.example.jeetmishra.androidquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jeetmishra.androidquizapp.Model.Question;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Questions extends AppCompatActivity {
    EditText Question;
    EditText Option1;
    EditText Option2;
    EditText Option3;
    EditText Option4;
    EditText Answer;
    EditText QuestionNo;
    EditText GameModeNo;
    Button AddQuestion,AdminLogout;

    DatabaseReference databaseQuestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        databaseQuestions= FirebaseDatabase.getInstance().getReference("Questions");

        Question= (EditText) findViewById(R.id.QuestionEditText);
        Option1=(EditText) findViewById(R.id.Option1EditText);
        Option2=(EditText) findViewById(R.id.Option2EditText);
        Option3=(EditText) findViewById(R.id.Option3EditText);
        Option4=(EditText) findViewById(R.id.Option4EditText);
        Answer=(EditText) findViewById(R.id.AnswerEditText);
        QuestionNo=(EditText) findViewById(R.id.QuestionNoEditText);
        GameModeNo=(EditText) findViewById(R.id.GameModeNoEditText);
        AddQuestion=(Button) findViewById(R.id.AddQuestion);
        AdminLogout=(Button)findViewById(R.id.adminlogout);
        AdminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Questions.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });
        AddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestion();
            }
        });
    }
    private void addQuestion(){
        String option1= Option1.getText().toString();
        String option2=Option2.getText().toString();
        String option3=Option3.getText().toString();
        String option4=Option4.getText().toString();
        String question=Question.getText().toString();
        String answer=Answer.getText().toString();
        String id=QuestionNo.getText().toString();
        String gamemodeno=GameModeNo.getText().toString();


        if (!TextUtils.isEmpty(question) &&
                !TextUtils.isEmpty(option1) &&
                !TextUtils.isEmpty(option2) &&
                !TextUtils.isEmpty(option3) &&
                !TextUtils.isEmpty(option4)&&
                !TextUtils.isEmpty(question)&&
                !TextUtils.isEmpty(id)&&
                !TextUtils.isEmpty(answer)&&

                !TextUtils.isEmpty(gamemodeno)
                ){



             com.example.jeetmishra.androidquizapp.Model.Question q= new Question(question,option1,option2,option3,option4,answer,id,gamemodeno);


                    databaseQuestions.child(id).setValue(q);

                    Toast.makeText(Questions.this,"Question added successfully!",Toast.LENGTH_LONG).show();
                    Question.getText().clear();
                    Option1.getText().clear();
                    Option2.getText().clear();
                    Option3.getText().clear();
                    Option4.getText().clear();
                    Answer.getText().clear();
                    QuestionNo.getText().clear();
                    GameModeNo.getText().clear();


                }


        else{
            Toast.makeText(this,"One of the textfield is empty!",Toast.LENGTH_LONG).show();
        }
    }
    }

