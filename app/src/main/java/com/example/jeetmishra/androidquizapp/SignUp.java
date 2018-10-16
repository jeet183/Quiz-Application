package com.example.jeetmishra.androidquizapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jeetmishra.androidquizapp.Common1.Common;
import com.example.jeetmishra.androidquizapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {
    MaterialEditText edtNewUser, edtNewPassword, edtNewEmail;
    MaterialEditText edtUser , edtPassword;
    Button btnSignUp, btnSignIn,btnBack;
    FirebaseDatabase database;
    DatabaseReference users;
    String adminusername = "ajeru@183";
    String adminpassword = "ajeru1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        edtUser =(MaterialEditText) findViewById(R.id.edtUser);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);


        btnSignIn =(Button) findViewById(R.id.btn_sign_in);
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        btnBack= (Button) findViewById(R.id.back2) ;

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                showSignUpDialog();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(edtUser.getText().toString(),edtPassword.getText().toString());
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoprevious();
            }
        });
    }

    private void gotoprevious() {
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signIn(final String user, final String pwd) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(user).exists())
                {
                    if(!user.isEmpty())
                    {
                        if(user.equals(adminusername)&&pwd.equals(adminpassword))
                        {
                            Intent intent = new Intent(SignUp.this,Questions.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            User login = dataSnapshot.child(user).getValue(User.class);

                            if (login.getPassword().equals(pwd)) {
                                Toast.makeText(SignUp.this, "Login successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this, NextScreen.class);
                                Common.currentUser = login;
                                Common.count++;
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignUp.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else {
                        Toast.makeText(SignUp.this,"Please enter your user name",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(SignUp.this,"User does not exists, Please sign up first",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showSignUpDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SignUp.this);
        alertDialog.setTitle("Sign Up");
        alertDialog.setMessage("Please fill all the details mentioned");

        LayoutInflater inflater = this.getLayoutInflater();
        View sign_up_layout = inflater.inflate(R.layout.sign_up_layout,null);
        edtNewUser =(MaterialEditText) sign_up_layout.findViewById(R.id.edtNewUserName);
        edtNewPassword=(MaterialEditText) sign_up_layout.findViewById(R.id.edtNewPassword);
        edtNewEmail=(MaterialEditText) sign_up_layout.findViewById(R.id.edtNewEmail);
        alertDialog.setView(sign_up_layout);
        alertDialog.setIcon(R.drawable.ic_account_circle_black_24dp);

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                final User user = new User(edtNewUser.getText().toString(),edtNewPassword.getText().toString(), edtNewEmail.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getUserName()).exists())
                        {
                            Toast.makeText(SignUp.this, "User already exists",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            users.child(user.getUserName()).setValue(user);

                            Toast.makeText(SignUp.this, "User registration successful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                dialogInterface.dismiss();
            }

        });
        alertDialog.show();

    }

}
