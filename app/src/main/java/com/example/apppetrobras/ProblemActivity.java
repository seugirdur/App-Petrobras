package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class ProblemActivity extends AppCompatActivity {
    int tipoProblema, idProblema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        tipoProblema = getIntent().getIntExtra("TIPO",0);
        idProblema = getIntent().getIntExtra("ID",0);

        Toast toast = Toast.makeText(getApplicationContext(), "Tipo: " + tipoProblema + "   id: " + idProblema, Toast.LENGTH_SHORT);
        toast.show();

    };

}
