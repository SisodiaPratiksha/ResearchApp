package com.google.devrel.vrviewapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class expe extends AppCompatActivity {

    EditText ed1,ed2;
    Button btn;
    String path;
    File filepath;
    private static final String TAG = "touch";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expe);

        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editexpe);
        btn = (Button) findViewById(R.id.btnsub);

        path = getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath();
        filepath = new File(path + "experience.csv");
        Log.d(TAG, "onCreate: Initializing touch services");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskexpe = ed1.getText().toString();
                String dayexpe = ed2.getText().toString();

                String expe = "Task Experience: " + taskexpe + " \n Day Experience: " + dayexpe + "\n";

                try {

                    FileWriter fw = new FileWriter(filepath,true);
                    fw.append(expe);
                    fw.flush();
                    fw.close();
                   // Toast.makeText(getActivity(),"Count of objects "+value,Toast.LENGTH_SHORT).show();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                Intent i = new Intent(com.google.devrel.vrviewapp.expe.this,lastact.class);
                startActivity(i);
                finish();


            }
        });

    }
}
