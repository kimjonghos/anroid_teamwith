package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.fastbooster.android_teamwith.task.JoinedTeamTask;

public class JoinedTeamActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joined_team);

        JoinedTeamTask joinedTeamTask=new JoinedTeamTask(this);
        joinedTeamTask.execute();
    }
}
