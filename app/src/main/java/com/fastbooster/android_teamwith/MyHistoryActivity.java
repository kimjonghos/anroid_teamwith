package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fastbooster.android_teamwith.task.MyHistoryTask;

public class MyHistoryActivity extends Activity {
    String memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_history);
        SharedPreferences sp = getSharedPreferences("memberPref", MODE_PRIVATE);
        memberId = sp.getString("memberId", "");

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

    public void bottomOnClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_home:
                Intent intent1 = new Intent(this, HomeActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.main_btn_search:
                Intent intent2 = new Intent(this, SearchSelectActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.main_btn_history:
                Intent intent3 = new Intent(this, MyHistoryActivity.class);
                intent3.putExtra("memberId", memberId);
                startActivity(intent3);
                finish();
                break;
            case R.id.main_btn_polog:
                Intent intent4 = new Intent(this, PologActivity.class);
                //intent4에 멤버 아이디 줘서 넘길것
                intent4.putExtra("memberId", memberId);
                startActivity(intent4);
                finish();

                break;

        }
    }
}
