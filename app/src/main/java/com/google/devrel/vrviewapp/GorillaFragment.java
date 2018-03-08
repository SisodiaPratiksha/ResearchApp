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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

/**
 * Fragment for the Gorilla tab.
 */
public class GorillaFragment extends Fragment {

    int counter = 0;
    Button btn;
    String path;
    File filepath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gorilla_fragment, container,false);
        path = getContext().getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath();
        filepath = new File(path+"backpress.csv");
        final EditText editText = (EditText) view.findViewById(R.id.edtext);
        btn = (Button) view.findViewById(R.id.btnsubmit);

        editText.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {


            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
        /*This method is called to notify you that, within s, the count characters beginning at start are about to be replaced by new text with length after. It is an error to attempt to make changes to s from this callback.*/
            }
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                counter = counter + 1;

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {

                            FileWriter fw = new FileWriter(filepath);
                            fw.append(String.valueOf(counter));
                            fw.flush();
                            fw.close();
                            Toast.makeText(getContext(),"Saved"+String.valueOf(counter),Toast.LENGTH_SHORT).show();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
            }

        /*editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                        count = count + 1;                    //String c = String.valueOf(count);
                    Log.d(TAG,"value:"+count);
                    btn.setOnClickListener(new View.OnClickListener() {
                        //@Override
                        public void onClick(View v) {
                            try {

                                FileWriter fw = new FileWriter(filepath);
                                fw.append(String.valueOf(count));
                                fw.flush();
                                fw.close();
                                Toast.makeText(getContext(),"Saved"+String.valueOf(count),Toast.LENGTH_SHORT).show();
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    });



                }
                return true;*/


        });
        return view;
    }








}