package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fastbooster.android_teamwith.task.LoginTask;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("memberPref", MODE_PRIVATE);
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), LoginActivity.class);
                if (sharedPreferences.getString("isAutoLogin", "-1").equals("true")) {
                    if (!sharedPreferences.getString("memberId", "-1").equals("-1")) {
                        intent.putExtra("status", 0);
                    } else {
                        intent.putExtra("status", 1);
                    }
                } else {
                    intent.putExtra("status", 1);
                }
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
