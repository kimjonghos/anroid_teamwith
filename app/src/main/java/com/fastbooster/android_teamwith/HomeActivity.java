package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.fastbooster.android_teamwith.task.HomeTask;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeTask home=new HomeTask(HomeActivity.this);
        home.execute();
//        ImageButton btnSearch = findViewById(R.id.y_btn_search);
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(), SearchSelectActivity.class);
//                startActivity(intent);
//            }
//        });

        Button btnLogout = findViewById(R.id.btn_test_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
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
