package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.fastbooster.android_teamwith.HomeActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.TeamActivity;
import com.fastbooster.android_teamwith.api.LoginApi;
import com.fastbooster.android_teamwith.api.TeamDetailApi;
import com.fastbooster.android_teamwith.model.MemberSimpleVO;
import com.fastbooster.android_teamwith.model.TeamDetailVO;
import com.fastbooster.android_teamwith.share.ApplicationShare;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginTask extends AsyncTask<String, Void, JSONObject> {
    private final Context context;

    public LoginTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        String result = null;
        try {
            result = jsonObject.getString("result");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result.equals("true")) {
            try {
                JSONObject member = jsonObject.getJSONObject("memberSimpleVO");
                MemberSimpleVO memberSimpleVO = new MemberSimpleVO(member);
                Toast.makeText(context, memberSimpleVO.getMemberName() + "님 환영합니다!", Toast.LENGTH_SHORT).show();
                ApplicationShare applicationShare = new ApplicationShare();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, "로그인 정보가 올바르지 않습니다", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected JSONObject doInBackground(String... accountInfos) {
        JSONObject json = null;
        try {
            json = LoginApi.login(accountInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
