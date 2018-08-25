package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.fastbooster.android_teamwith.task.HomeTask;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeTask home=new HomeTask(HomeActivity.this);

        ImageButton btnSearch = findViewById(R.id.y_btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SearchSelectActivity.class);
                startActivity(intent);
            }
        });
    }
}
