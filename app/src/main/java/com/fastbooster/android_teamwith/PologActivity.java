package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fastbooster.android_teamwith.task.PologTask;

//멤버아이디 받아서 fragment로 전달,폴로그로 전달
public class PologActivity extends BarActivity {
    private FrameLayout frame;
    private LayoutInflater inflater;
    private View profileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polog);

        final Button kbtnProfile = (Button) findViewById(R.id.k_btn_Profile);
        final Button kbtnPortfolio = (Button) findViewById(R.id.k_btn_Portfolio);
        frame = (FrameLayout) findViewById(R.id.k_fl_portfolioList);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        profileView = inflater.inflate(R.layout.profile_layout, frame, false);

        PologTask pt = new PologTask(PologActivity.this, profileView);

        pt.execute(memberId);//멤버아이디 전달 받아서 넣기
        TextView back = findViewById(R.id.backTv);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        kbtnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kbtnProfile.setTextColor(Color.parseColor("red"));
                kbtnPortfolio.setTextColor(Color.parseColor("black"));
                changeView(0);
            }
        });
        kbtnPortfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kbtnProfile.setTextColor(Color.parseColor("black"));
                kbtnPortfolio.setTextColor(Color.parseColor("red"));
                changeView(1);
            }
        });
    }

    private void changeView(int index) {


        if (frame.getChildCount() > 0) {
            frame.removeViewAt(0);
        }
//        View view=null;
        switch (index) {
            case 0:
//                view=inflater.inflate(R.layout.profile_layout,frame,false);
                if (profileView != null) {
                    frame.addView(profileView);
                } else {
                    profileView = inflater.inflate(R.layout.profile_layout, frame, false);

                    frame.addView(profileView);
                }
                break;
            case 1:
                //포트폴리오 레이아웃줄것
                Bundle bundle = new Bundle(1);
                bundle.putString("memberId", memberId);
                FragmentManager fm = getFragmentManager();

                PortfolioFragment pf = new PortfolioFragment();
                pf.setArguments(bundle);
                FragmentTransaction tr = fm.beginTransaction();
                tr.add(R.id.k_fl_portfolioList, pf);

                tr.commit();

                Toast.makeText(getApplicationContext(), "프래그먼트", Toast.LENGTH_SHORT).show();
                break;

        }

    }

}
