package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.fastbooster.android_teamwith.share.ApplicationShare;
import com.fastbooster.android_teamwith.task.LoginTask;

public class LoginActivity extends Activity {
    private Context context = this;
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
                LoginTask loginTask = new LoginTask(context);
                String[] accountInfos = new String[]{editTextId.getText().toString(), editTextPwd.getText().toString()};
                loginTask.execute(accountInfos);
            }
        });

    }

    private void init() {
        editTextId = this.findViewById(R.id.yet_id);
        editTextPwd = this.findViewById(R.id.yet_pwd);
        checkBoxAutoLogin = this.findViewById(R.id.ycb_auto_login);
        btnFindAccount = this.findViewById(R.id.yb_find_account);
        btnLogin = this.findViewById(R.id.yb_login);
        btnRegister = this.findViewById(R.id.yb_login);
        btnEnter = this.findViewById(R.id.yb_enter);
    }


}

