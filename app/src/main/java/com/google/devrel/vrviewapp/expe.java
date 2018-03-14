package com.google.devrel.vrviewapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
    float Xstart, Xend, Ystart, Yend, Xswipe;
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

                String expe = "Day Experience: " + taskexpe + " \n Task Experience: " + dayexpe + "\n";

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

    private void touchListener(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    Xstart  = event.getX();
                    Ystart = event.getY();
                    Xswipe = Xstart;
                    String adown = "Xstart:"+String.valueOf(Xstart)+ ", Ystart: "+ String.valueOf(Ystart)+ ", Pressure: "+ event.getPressure()+"\n";

                    try {

                        FileWriter fw = new FileWriter(filepath,true);
                        fw.append(adown);
                        fw.flush();
                        fw.close();
                        //Toast.makeText(getActivity(),"Ystart: "+Ystart,Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    //Toast.makeText(getActivity(), "start"+String.valueOf(Xstart)+"  "+String.valueOf(Ystart), Toast.LENGTH_SHORT).show();
                }
                if(event.getActionMasked() == MotionEvent.ACTION_UP){
                    Xend = event.getX();
                    Yend = event.getY();
                    Xswipe = Xend;

                    String aup = "Xend"+String.valueOf(Xend)+ ", Yend: "+ String.valueOf(Yend)+ ", Pressure: "+ event.getPressure()+ "\n";

                    try {

                        FileWriter fw = new FileWriter(filepath,true);
                        fw.append(aup);
                        fw.flush();
                        fw.close();
                        //Toast.makeText(getActivity(),"X end: "+Xend,Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    // Toast.makeText(getActivity(),"Pressure:" +event.getPressure(),Toast.LENGTH_SHORT).show();
                }





                //Toast.makeText(getContext() ,"The x and Y are:"++" "+,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

}
