package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fastbooster.android_teamwith.task.TeamDetailTask;

public class TeamLeaderActivity extends BarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamleader);
        Intent intent=getIntent();
        String teamId=intent.getStringExtra("teamId");
        TeamDetailTask task = new TeamDetailTask(this,teamId);
        task.execute();
    }
}