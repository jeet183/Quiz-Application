package com.example.jeetmishra.androidquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NextScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_screen);

    }
    public void gotoHome(View view)
    {
        Intent intent = new Intent(NextScreen.this,Home.class);
        startActivity(intent);
        finish();
    }

}
