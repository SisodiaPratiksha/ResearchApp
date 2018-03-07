/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.devrel.vrviewapp;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.devrel.vrviewapp.com.google.devrel.vrviewapp.ImageLoaderTask;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Fragment for handling the Welcome tab.
 */
public class WelcomeFragment extends Fragment {

    private VrPanoramaView panoWidgetView;
    private ImageLoaderTask backgroundImageLoaderTask;
    float Xstart, Xend, Ystart, Yend, Xswipe;
    String path;
    File filepath;
    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.welcome_fragment, container,false);
        panoWidgetView = (VrPanoramaView) v.findViewById(R.id.pano_view);
        touchListener(v);
        path = getContext().getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath();
        filepath = new File(path + "touch.csv");
        return v;
    }

    @Override
    public void onPause() {
        panoWidgetView.pauseRendering();
        super.onPause();
    }

    @Override
    public void onResume() {
        panoWidgetView.resumeRendering();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        // Destroy the widget and free memory.
        panoWidgetView.shutdown();
        super.onDestroy();
    }

    private synchronized void loadPanoImage() {
        ImageLoaderTask task = backgroundImageLoaderTask;
        if (task != null && !task.isCancelled()) {
            // Cancel any task from a previous loading.
            task.cancel(true);
        }

        // pass in the name of the image to load from assets.
        VrPanoramaView.Options viewOptions = new VrPanoramaView.Options();
        viewOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;

        // use the name of the image in the assets/ directory.
        String panoImageName = "sample_converted.jpg";

        // create the task passing the widget view and call execute to start.
        task = new ImageLoaderTask(panoWidgetView, viewOptions, panoImageName);
        task.execute(getActivity().getAssets());
        backgroundImageLoaderTask = task;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadPanoImage();
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
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    Toast.makeText(getActivity(),"Pressure:" +event.getPressure(),Toast.LENGTH_SHORT).show();
                }





                //Toast.makeText(getContext() ,"The x and Y are:"++" "+,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
