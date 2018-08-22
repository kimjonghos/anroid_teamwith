package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ProfileEditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        LinearLayout introLayout = findViewById(R.id.memberIntroLayout);

        introLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileEditActivity.this,MemberIntroActivity.class);
                startActivity(intent);
            }
        });
    }

}
