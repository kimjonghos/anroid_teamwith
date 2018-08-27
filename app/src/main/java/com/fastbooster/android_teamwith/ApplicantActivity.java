package com.fastbooster.android_teamwith;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fastbooster.android_teamwith.task.ApplicantTask;

public class ApplicantActivity extends BarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant);
        Intent intent=getIntent();
        String teamId=intent.getStringExtra("teamId");
        ApplicantTask task=new ApplicantTask(this,teamId);
        task.execute();
    }
}
