package com.fastbooster.android_teamwith;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class PologActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polog);
        final Button kbtnProfile =(Button)findViewById(R.id.kbtnProfile);
        final Button kbtnPortfolio =(Button)findViewById(R.id.kbtnPortfolio);
        kbtnProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                kbtnProfile.setTextColor(Color.parseColor("red"));
                kbtnPortfolio.setTextColor(Color.parseColor("black"));
                changeView(0);
            }
        });
        kbtnPortfolio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                kbtnProfile.setTextColor(Color.parseColor("black"));
                kbtnPortfolio.setTextColor(Color.parseColor("red"));
                changeView(1);
            }
        });
    }

    private void changeView(int index){
        LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        FrameLayout frame=(FrameLayout)findViewById(R.id.kframe);
        if(frame.getChildCount()>0){
            frame.removeViewAt(0);
        }
        View view=null;
        switch(index){
            case 0:
                view=inflater.inflate(R.layout.profile_layout,frame,false);
                break;
            case 1:
                //포트폴리오 레이아웃줄것
                FragmentManager fm=getFragmentManager();
                Fragment fragment=fm.findFragmentById(R.id.kframe);
                if(fragment==null){


                }
                break;

        }
        if(view!=null){
            frame.addView(view);
        }
    }
}
