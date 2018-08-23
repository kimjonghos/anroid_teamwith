package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends Activity {
    private EditText editTextId;
    private EditText editTextPwd;
    private CheckBox checkBoxAutoLogin;
    private Button btnFindAccount;
    private Button btnLogin;
    private Button btnRegister;
    private Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private void init() {
        editTextId = this.findViewById(R.id.y_edit_text_id);
        editTextPwd = this.findViewById(R.id.y_edit_text_pwd);
        checkBoxAutoLogin = this.findViewById(R.id.y_check_box_auto_login);
        btnFindAccount = this.findViewById(R.id.y_btn_find_account);
        btnLogin = this.findViewById(R.id.y_btn_login);
        btnRegister = this.findViewById(R.id.y_btn_register);
        btnEnter = this.findViewById(R.id.y_btn_enter);
    }

    public void register(View view) {
        Intent intent = new Intent();
       // intent.setClass(getApplicationContext(), WebActivity.class);
        startActivity(intent);
    }

}
