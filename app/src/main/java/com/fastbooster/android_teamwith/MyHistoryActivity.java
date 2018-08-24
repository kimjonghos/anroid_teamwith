package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fastbooster.android_teamwith.task.MyHistoryTask;

public class MyHistoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_history);

        loadMyTeam();

        Button btnApplication = findViewById(R.id.y_btn_application);

        btnApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "?", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ApplicationActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loadMyTeam() {
        MyHistoryTask myHistoryTask = new MyHistoryTask(this);
        myHistoryTask.execute();
    }
}
