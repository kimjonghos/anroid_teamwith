package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.fastbooster.android_teamwith.task.TeamDetailTask;

import java.net.HttpURLConnection;
import java.net.URL;

public class TeamActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        Intent intent=getIntent();
        String teamId=intent.getStringExtra("teamId");
        TeamDetailTask task = new TeamDetailTask(this,teamId);
        task.execute();
    }
}