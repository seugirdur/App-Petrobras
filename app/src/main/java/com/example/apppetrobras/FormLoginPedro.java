package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ms.square.android.expandabletextview.ExpandableTextView;
//import android.widget.ImageView;


public class FormLoginPedro extends AppCompatActivity {

    //ImageView logo,appName,splashImg;
    //LottieAnimationView LottieAnimationView;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login_pedro);

        ExpandableTextView textView=findViewById(R.id.expand_text_view);
        textView.setText(getString(R.string.dummyText));

        ///logo = findViewById(R.id.logo);
        //logo = findViewById(R.id.logo);
        //logo = findViewById(R.id.logo);

    }
}
