package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fastbooster.android_teamwith.task.MyApplicationTask;

public class ApplicationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        MyApplicationTask maTask = new MyApplicationTask(ApplicationActivity.this);
        maTask.execute();
        TextView back = findViewById(R.id.backTv);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}