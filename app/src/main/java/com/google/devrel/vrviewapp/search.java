package com.google.devrel.vrviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class search extends AppCompatActivity {

    com.google.devrel.vrviewapp.TouchImageView iv;
    EditText femaleCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        iv = (com.google.devrel.vrviewapp.TouchImageView) findViewById(R.id.img);

        //TouchImageView img = new TouchImageView(this);
        iv.setImageResource(R.drawable.searchfinal);
        iv.setMaxZoom(3f);

        femaleCount = (EditText) findViewById(R.id.number_input);

    }

    public void buttonClick(View v) {
        String femaleCountString = femaleCount.getText().toString();
        Toast.makeText(this, "You entered "+femaleCountString, Toast.LENGTH_SHORT).show();
    }
}
