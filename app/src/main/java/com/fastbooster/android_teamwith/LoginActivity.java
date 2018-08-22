package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        EditText editText = findViewById(R.id.editText);
        TextView textView = findViewById(R.id.textView11);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.font_bmjua);
        editText.setTypeface(typeface);
        textView.setTypeface(typeface);
    }
}
