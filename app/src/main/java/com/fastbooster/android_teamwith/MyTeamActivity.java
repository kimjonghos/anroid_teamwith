package com.fastbooster.android_teamwith;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import com.fastbooster.android_teamwith.task.MyTeamTask;


public class MyTeamActivity extends BarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);
        MyTeamTask task = new MyTeamTask(this);
        task.execute();
        TextView back = findViewById(R.id.backTv);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
