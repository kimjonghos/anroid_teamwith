package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.fastbooster.android_teamwith.task.PortfolioDetailTask;
import com.fastbooster.android_teamwith.task.PortfolioSearchTask;
//멤버아이디 받아서 fragment로 전달,폴로그로 전달
public class PologActivity extends Activity {
    FrameLayout frame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polog);
        final Button kbtnProfile =(Button)findViewById(R.id.k_btn_Profile);
        final Button kbtnPortfolio =(Button)findViewById(R.id.k_btn_Portfolio);
        frame=(FrameLayout)findViewById(R.id.k_fl_portfolioList);
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

                PortfolioFragment pf=new PortfolioFragment();
             FragmentTransaction tr=fm.beginTransaction();
             tr.add(R.id.k_fl_portfolioList,pf);

                tr.commit();

                Toast.makeText(getApplicationContext(),"프래그먼트",Toast.LENGTH_SHORT).show();
                break;

        }
        if(view!=null){
            frame.addView(view);
        }
    }
}
