package com.fastbooster.android_teamwith;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.fastbooster.android_teamwith.share.ApplicationShare;
import com.fastbooster.android_teamwith.task.PologTask;
import com.fastbooster.android_teamwith.task.PraiseCheckTask;

import java.util.List;

//멤버아이디 받아서 fragment로 전달,폴로그로 전달
public class PologActivity extends BarActivity {

    String targetId;

    String[] praiseKeyList;

    String[] praiseList;

    boolean[] praiseChecked;

    private FrameLayout frame;
    private LayoutInflater inflater;
    private View profileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polog);
        targetId = getIntent().getStringExtra("memberId");
        praiseList = new String[ApplicationShare.praiseList.size()];
        praiseKeyList = new String[ApplicationShare.praiseList.size()];
        praiseChecked = new boolean[praiseList.length];
        int i = 0;
        for (Object s : ApplicationShare.praiseList.keySet()) {
            String k = (String) s;
            praiseList[i] = (String) ApplicationShare.praiseList.get(k);
            praiseKeyList[i++] = k;
        }

        final Button kbtnProfile = (Button) findViewById(R.id.k_btn_Profile);
        final Button kbtnPortfolio = (Button) findViewById(R.id.k_btn_Portfolio);
        Button praiseBtn = findViewById(R.id.praiseBtn);

        frame = (FrameLayout) findViewById(R.id.k_fl_portfolioList);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        profileView = inflater.inflate(R.layout.profile_layout, frame, false);




        PologTask pt = new PologTask(PologActivity.this, profileView);
        pt.execute(targetId);//멤버아이디 전달 받아서 넣기

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

        praiseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> checkedPraiseId = null;
                AlertDialog.Builder dialog = null;
                PraiseCheckTask ptask = new PraiseCheckTask(PologActivity.this, dialog,
                        targetId, PologTask.prTv);
                ptask.execute();

            }
        });
        changeView(0);
    }

    private void changeView(int index) {


        if (frame.getChildCount() > 0) {
            frame.removeViewAt(0);
        }

        switch (index) {
            case 0:

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

                break;

        }

    }

}
