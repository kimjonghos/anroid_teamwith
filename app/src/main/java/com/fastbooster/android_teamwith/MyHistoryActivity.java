package com.fastbooster.android_teamwith;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fastbooster.android_teamwith.task.MyHistoryTask;

public class MyHistoryActivity extends BarActivity {

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

        Button myTeamMore = findViewById(R.id.yb_my_team_more);
        myTeamMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MyHistoryActivity.this, MyTeamActivity.class);
                startActivity(in);
            }
        });

        Button joinedMore = findViewById(R.id.joinedTeamMore);
        joinedMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MyHistoryActivity.this, JoinedTeamActivity.class);
                startActivity(in);
            }
        });
    }

    public void loadMyTeam() {
        MyHistoryTask myHistoryTask = new MyHistoryTask(this);
        myHistoryTask.execute();
    }

}
