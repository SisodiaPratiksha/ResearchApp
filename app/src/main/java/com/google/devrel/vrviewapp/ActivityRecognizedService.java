package com.google.devrel.vrviewapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import com.google.devrel.vrviewapp.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


 // Created by DELL on 11-03-2018.


public class ActivityRecognizedService extends IntentService {

    public ActivityRecognizedService() {
        super("ActivityRecognizedService");
    }

    public ActivityRecognizedService(String name) {
        super(name);
    }

    String path;
    File filepath;
    private static final String TAG = "activity";
    @Override
    protected void onHandleIntent(Intent intent) {

        if(ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities( result.getProbableActivities() );
        }
    }

    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        for( DetectedActivity activity : probableActivities ) {
            path = getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath();
            filepath = new File(path + "activity.csv");
            Log.d(TAG, "onCreate: Initializing touch services");
            switch( activity.getType() ) {
                case DetectedActivity.IN_VEHICLE: {
                    String vehicle = Integer.toString(activity.getConfidence());
                    try {

                        FileWriter fw = new FileWriter(filepath,true);
                        fw.append("In Vehicle: "+vehicle+"\n");
                        fw.flush();
                        fw.close();
                        //Toast.makeText(getActivity(),"Count of objects "+value,Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    //Log.e( "ActivityRecogition", "In Vehicle: " + activity.getConfidence() );
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    String bicycle = Integer.toString(activity.getConfidence());
                    //Log.e( "ActivityRecogition", "On Bicycle: " + activity.getConfidence() );
                    try {

                        FileWriter fw = new FileWriter(filepath,true);
                        fw.append("On Bicycle: "+bicycle+"\n");
                        fw.flush();
                        fw.close();
                        //Toast.makeText(getActivity(),"Count of objects "+value,Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                }
                case DetectedActivity.ON_FOOT: {
                    String foot = Integer.toString(activity.getConfidence());
                    try {

                        FileWriter fw = new FileWriter(filepath,true);
                        fw.append("On Foot: "+foot+"\n");
                        fw.flush();
                        fw.close();
                        //Toast.makeText(getActivity(),"Count of objects "+value,Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                    //Log.e( "ActivityRecogition", "On Foot: " + activity.getConfidence() );
                }
                case DetectedActivity.RUNNING: {
                    String running = Integer.toString(activity.getConfidence());
                    try {

                        FileWriter fw = new FileWriter(filepath,true);
                        fw.append("Running: "+running+"\n");
                        fw.flush();
                        fw.close();
                        //Toast.makeText(getActivity(),"Count of objects "+value,Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                    //Log.e( "ActivityRecogition", "Running: " + activity.getConfidence() );

                }
                case DetectedActivity.STILL: {
                    String still = Integer.toString(activity.getConfidence());
                    try {

                        FileWriter fw = new FileWriter(filepath,true);
                        fw.append("Still: "+still+"\n");
                        fw.flush();
                        fw.close();
                        //Toast.makeText(getActivity(),"Count of objects "+value,Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                    //Log.e( "ActivityRecogition", "Still: " + activity.getConfidence() );

                }
                case DetectedActivity.TILTING: {
                    String tilting = Integer.toString(activity.getConfidence());
                    try {

                        FileWriter fw = new FileWriter(filepath,true);
                        fw.append("Tilting: "+tilting+"\n");
                        fw.flush();
                        fw.close();
                        //Toast.makeText(getActivity(),"Count of objects "+value,Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                    //Log.e( "ActivityRecogition", "Tilting: " + activity.getConfidence() );

                }
                case DetectedActivity.WALKING: {
                    String walking = Integer.toString(activity.getConfidence());
                    try {

                        FileWriter fw = new FileWriter(filepath,true);
                        fw.append("Walking: "+walking+"\n");
                        fw.flush();
                        fw.close();
                        //Toast.makeText(getActivity(),"Count of objects "+value,Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    //Log.e( "ActivityRecogition", "Walking: " + activity.getConfidence() );
//                    if( activity.getConfidence() >= 75 ) {
//                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//                        builder.setContentText( "Are you walking?" );
//                        builder.setSmallIcon( R.mipmap.ic_launcher );
//                        builder.setContentTitle( getString( R.string.app_name ) );
//                        NotificationManagerCompat.from(this).notify(0, builder.build());
//                    }
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    String unknown = Integer.toString(activity.getConfidence());
                    //Log.e( "ActivityRecogition", "Unknown: " + activity.getConfidence() );
                    break;
                }
            }
        }
    }
}
