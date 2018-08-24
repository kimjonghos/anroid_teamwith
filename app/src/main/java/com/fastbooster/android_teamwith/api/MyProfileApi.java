package com.fastbooster.android_teamwith.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.fastbooster.android_teamwith.model.MemberVO;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyProfileApi {
    static final String TAG = "member data...";
    public static String URL_STR = "http://192.168.30.64:8089/api/member/getEditInfo";


    public static MemberVO getMember(Context context) {

        try {
            URL url = new URL(URL_STR);
            Log.v(TAG, url.toString());
            HttpURLConnection conn = null;
            StringBuilder sb = new StringBuilder();
            SharedPreferences sp = context.getSharedPreferences("memberPref", Context.MODE_PRIVATE);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setConnectTimeout(1000);

            conn.setRequestProperty("Cookie", sp.getString("sessionId", ""));

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } else {
                Log.d("Teamwith app error", "URL=" + URL_STR);
                return null;
            }

            JSONObject object = new JSONObject(sb.toString());

            return new MemberVO(object);

        } catch (Exception e) {
            Log.d("Teamwith app error", e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
}
