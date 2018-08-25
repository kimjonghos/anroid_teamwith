package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fastbooster.android_teamwith.task.LogoutTask;

public class SettingActivity extends Activity {

    TextView back;

    Button csBtn;
    Button verBtn;
    Button serviceBtn;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        back = findViewById(R.id.jsettingBack);
        csBtn = findViewById(R.id.jcsBtn);
        verBtn = findViewById(R.id.jverBtn);
        serviceBtn = findViewById(R.id.jServiceBtn);
        logout = findViewById(R.id.logoutBtn);

        //뒤로가기 버튼
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, MyPologActivity.class);
                startActivity(intent);
            }
        });

        //고객센터
        csBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                try {
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"fastbooater15@gmail.com"});

                    emailIntent.setType("text/html");
                    emailIntent.setPackage("com.google.android.gm");
                    if (emailIntent.resolveActivity(getPackageManager()) != null)
                        startActivity(emailIntent);

                    startActivity(emailIntent);
                } catch (Exception e) {
                    e.printStackTrace();

                    emailIntent.setType("text/html");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"fastbooater15@gmail.com"});

                    startActivity(Intent.createChooser(emailIntent, "Send Email"));
                }
            }
        });

        //버전 정보
        verBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SettingActivity.this, R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setMessage("현재 버전은 1.0.1.2 입니다.");
                dialog.show();

            }
        });

        //서비스 소개
        serviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                startActivity(intent);
            }
        });

        //로그아웃 버튼
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogoutTask logoutTask = new LogoutTask(getApplicationContext());
                logoutTask.execute();
            }
        });


    }
}
