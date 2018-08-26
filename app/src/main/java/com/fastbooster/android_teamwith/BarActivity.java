package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class BarActivity extends Activity {

    public String memberId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("memberPref", MODE_PRIVATE);
        memberId = sp.getString("memberId", "");
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
                Intent intent4 = new Intent(this, MyPologActivity.class);

                intent4.putExtra("memberId", memberId);
                startActivity(intent4);
                finish();
                break;

        }
    }
}
