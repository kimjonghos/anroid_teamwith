package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchSelectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_select);

        Button tsearch = findViewById(R.id.ktSearchBtn);
        Button msearch = findViewById(R.id.y_btn_application);

        Button psearch = findViewById(R.id.kpSearchBtn);

        final Intent intent = new Intent(SearchSelectActivity.this,
                SearchActivity.class);

        tsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
intent.putExtra("kind","team");
                startActivity(intent);
            }
        });
        msearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("kind","member");
                startActivity(intent);
            }
        });
    }
}
