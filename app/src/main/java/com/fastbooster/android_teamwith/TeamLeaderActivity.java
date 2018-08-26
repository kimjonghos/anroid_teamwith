package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fastbooster.android_teamwith.task.TeamDetailTask;

public class TeamLeaderActivity extends BarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamleader);
        Intent intent=getIntent();
        String teamId=intent.getStringExtra("teamId");
        TeamDetailTask task = new TeamDetailTask(this,teamId);
        task.execute();
    }
    public void bottomOnClick(View v){
        switch (v.getId()){
            case R.id.main_btn_home:
                Intent intent1=new Intent(this,HomeActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.main_btn_search:
                Intent intent2=new Intent(this,SearchSelectActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.main_btn_history:
                Intent intent3=new Intent(this,MyHistoryActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.main_btn_polog:
                Intent intent4=new Intent(this,PologActivity.class);
                //intent4에 멤버 아이디 줘서 넘길것
                intent4.putExtra("memberId","kim");
                startActivity(intent4);
                finish();

                break;

        }
    }
}