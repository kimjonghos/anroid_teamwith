package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.fastbooster.android_teamwith.HomeActivity;
import com.fastbooster.android_teamwith.LoginActivity;
import com.fastbooster.android_teamwith.api.LoginApi;

public class LoginTask extends AsyncTask<String, Void, String[]> {
    private final Context context;

    public LoginTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(String[] result) {
        LoginActivity loginContext = (LoginActivity) context;

        SharedPreferences memberPref = context.getSharedPreferences("memberPref", Context.MODE_PRIVATE);

        if (result != null) {
            try {
                Toast.makeText(context, "환영합니다!", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = memberPref.edit();
                editor.putString("sessionId", result[0]);
                editor.putString("memberId", result[1]);
                editor.putString("memberPassword", result[2]);
                editor.putString("isAutoLogin", result[3]);
                editor.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, "로그인 정보가 올바르지 않습니다", Toast.LENGTH_SHORT).show();
        }

        if(result != null) {
            Intent intent = new Intent();
            intent.setClass(loginContext, HomeActivity.class);
            loginContext.startActivity(intent);
        }
    }

    @Override
    protected String[] doInBackground(String... accountInfos) {
        String[] result = null;
        try {
            result = LoginApi.login(accountInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
