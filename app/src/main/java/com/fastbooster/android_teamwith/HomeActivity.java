package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.fastbooster.android_teamwith.task.HomeTask;

public class HomeActivity extends Fragment {


    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//
//        HomeTask home=new HomeTask(HomeActivity.this);
//        home.execute();
//        ImageButton btnSearch = findViewById(R.id.y_btn_search);
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(), SearchSelectActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        Button btnLogout = findViewById(R.id.btn_test_logout);
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(), SettingActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_home,container,false);
        HomeTask home=new HomeTask(view.getContext());
        home.execute();
        ImageButton btnSearch = view.findViewById(R.id.y_btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), SearchSelectActivity.class);
                startActivity(intent);
            }
        });

        Button btnLogout = view.findViewById(R.id.btn_test_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }
}
