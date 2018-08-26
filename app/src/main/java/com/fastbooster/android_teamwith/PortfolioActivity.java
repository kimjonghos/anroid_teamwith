package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fastbooster.android_teamwith.task.PortfolioDetailTask;

import org.json.JSONObject;

public class PortfolioActivity extends BarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
//        setContentView(R.layout.portfoliotestlayout);
        PortfolioDetailTask pd=new PortfolioDetailTask(PortfolioActivity.this);
        TextView te=(TextView)findViewById(R.id.te);
//        Intent intent=getIntent();
//        String portfolioId=intent.getStringExtra("portfolioId");
        pd.execute("portfolio-102");

    }
}
