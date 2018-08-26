package com.fastbooster.android_teamwith;

import android.os.Bundle;

import com.fastbooster.android_teamwith.task.HomeTask;

public class HomeActivity extends BarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HomeTask home = new HomeTask(HomeActivity.this);
        home.execute();
    }

}
