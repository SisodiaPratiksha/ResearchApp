package com.google.devrel.vrviewapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class search extends AppCompatActivity {

    com.google.devrel.vrviewapp.TouchImageView iv;
    EditText femaleCount;
    String path;
    File filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        iv = (com.google.devrel.vrviewapp.TouchImageView) findViewById(R.id.img);

        //TouchImageView img = new TouchImageView(this);
        iv.setImageResource(R.drawable.searchrat);
        iv.setMaxZoom(5f);

        femaleCount = (EditText) findViewById(R.id.number_input);
        path = getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath();
        filepath = new File(path+"imagecounter.csv");

    }

    public void buttonClick(View v) {

        String counter = femaleCount.getText().toString();
        try {

            FileWriter fw = new FileWriter(filepath,true);
            fw.append(counter);
            fw.flush();
            fw.close();
            //Toast.makeText(getContext(),"Saved"+String.valueOf(counter),Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //String femaleCountString = femaleCount.getText().toString();
        //Toast.makeText(this, "You entered "+femaleCountString, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(search.this,expe.class);
        startActivity(i);
        finish();

    }
}
