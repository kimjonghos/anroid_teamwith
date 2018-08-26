package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.os.Bundle;

import com.fastbooster.android_teamwith.task.MyApplicationTask;

public class ApplicationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        MyApplicationTask maTask = new MyApplicationTask(ApplicationActivity.this);
        maTask.execute();
    }
}