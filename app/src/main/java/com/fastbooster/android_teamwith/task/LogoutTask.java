package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.fastbooster.android_teamwith.LoginActivity;

import java.net.HttpURLConnection;
import java.net.URL;

public class LogoutTask extends AsyncTask<Void, Void, Void> {
    private final Context context;

    public LogoutTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(Void avoid) {
        SharedPreferences sp = context.getSharedPreferences("memberPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();

        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        SharedPreferences sp = context.getSharedPreferences("memberPref", Context.MODE_PRIVATE);
        String urlStr = "http://192.168.30.64:8089/api/logout";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Cookie", sp.getString("sessionId", ""));
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setDoInput(true);
            conn.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
