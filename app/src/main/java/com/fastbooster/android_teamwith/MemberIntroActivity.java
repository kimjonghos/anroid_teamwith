package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MemberIntroActivity extends Activity {
    EditText intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_intro);

        TextView back = findViewById(R.id.backTv);
        intro = findViewById(R.id.jmemberInrtroEt);
        intro.setText(getIntent().getStringExtra("memberIntro"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIntent().putExtra("memberIntro", intro.getText().toString());
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        getIntent().putExtra("memberIntro", intro.getText().toString());
        setResult(RESULT_OK, getIntent());
        super.onBackPressed();
    }
}
