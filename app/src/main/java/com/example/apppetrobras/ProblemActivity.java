package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class ProblemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        String title = getIntent().getStringExtra("title");
        TextView textView = findViewById(R.id.textProblema);

        Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();

    }
}