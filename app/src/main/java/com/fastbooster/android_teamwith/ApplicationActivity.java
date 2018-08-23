package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ApplicationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
    }

    public static class PologActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_polog);
        }
    }
}