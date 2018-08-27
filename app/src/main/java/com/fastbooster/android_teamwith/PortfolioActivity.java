package com.fastbooster.android_teamwith;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fastbooster.android_teamwith.task.PortfolioDetailTask;

public class PortfolioActivity extends BarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
//        setContentView(R.layout.portfoliotestlayout);
        PortfolioDetailTask pd=new PortfolioDetailTask(PortfolioActivity.this);
        TextView te=(TextView)findViewById(R.id.te);
        Intent intent=getIntent();
        String portfolioId=intent.getStringExtra("portfolioId");
        pd.execute(portfolioId);
    }

    @Override
    public void bottomOnClick(View v) {
        super.bottomOnClick(v);
    }

}
