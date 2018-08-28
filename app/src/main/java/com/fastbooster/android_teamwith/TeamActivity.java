package com.fastbooster.android_teamwith;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fastbooster.android_teamwith.task.TeamDetailTask;

public class TeamActivity extends BarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        Intent intent = getIntent();
        String teamId = intent.getStringExtra("teamId");
        TeamDetailTask task = new TeamDetailTask(this, teamId);
        task.execute();
    }
}