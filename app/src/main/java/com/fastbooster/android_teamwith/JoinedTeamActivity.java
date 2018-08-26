package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.task.JoinedTeamTask;

public class JoinedTeamActivity extends BarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joined_team);

        JoinedTeamTask joinedTeamTask=new JoinedTeamTask(this);
        joinedTeamTask.execute();
        TextView back = findViewById(R.id.backTv);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
