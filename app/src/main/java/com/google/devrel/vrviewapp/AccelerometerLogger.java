package com.google.devrel.vrviewapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by DELL on 06-03-2018.
 */

public class AccelerometerLogger extends Service implements SensorEventListener {

    private static final String TAG = "accelerometerLogger";
    private long lastUpdate = 0;
    FileWriter writer;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    FileOutputStream fileOutputStream;
    String path;
    File filepath;


    private SensorManager sensorManager = null;
    private Sensor sensor = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        path = getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath();
        filepath = new File(path + "ace.csv");
        Log.d(TAG, "onCreate: Initializing sensor services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // grab the values and timestamp
        float X = event.values[0];
        float Y = event.values[1];
        float Z = event.values[2];
        //long time = event.timestamp;
        long curTime = System.currentTimeMillis();

        if ((curTime - lastUpdate) > 300000) {
            long diffTime = (curTime - lastUpdate);

            Date currentTime = Calendar.getInstance().getTime();

            last_x = X;
            last_y = Y;
            last_z = Z;


            //String file = "acell.csv";
            String entry = String.valueOf(X) + "," + String.valueOf(Y) + "," + String.valueOf(Z) + "," + String.valueOf(currentTime) + "," + "\n";

            try {

                FileWriter fw = new FileWriter(filepath, true);
                fw.append(entry);
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            lastUpdate = System.currentTimeMillis();
        }

    }
}


