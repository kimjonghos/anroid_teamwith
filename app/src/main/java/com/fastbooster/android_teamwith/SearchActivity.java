package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button tsearch = findViewById(R.id.ktSearchBtn);
        Button msearch = findViewById(R.id.kmSearchBtn);

        Button psearch = findViewById(R.id.kpSearchBtn);

        tsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,
                        TeamSearchActivity.class);
                startActivity(intent);
            }
        });

    }
}
