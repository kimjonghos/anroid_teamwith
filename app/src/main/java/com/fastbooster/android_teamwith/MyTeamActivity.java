package com.fastbooster.android_teamwith;


import android.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.fastbooster.android_teamwith.task.MyTeamTask;

import java.util.zip.Inflater;


public class MyTeamActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);
        MyTeamTask task=new MyTeamTask(this);
        task.execute();
    }
}
