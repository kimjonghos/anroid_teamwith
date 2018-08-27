package com.fastbooster.android_teamwith.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiUtil {
    private static final String URL_STR = "http://192.168.30.64:8089/api";

    public static JSONObject getJsonObject(String urlStr) {
        try {
            return new JSONObject(getData(null, urlStr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getMyJsonObject(Context context, String urlStr) {
        try {
            return new JSONObject(getData(context, urlStr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray getJsonArray(String urlStr) {
        try {
            return new JSONArray(getData(null, urlStr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray getMyJsonArray(Context context, String urlStr) {
        try {
            return new JSONArray(getData(context, urlStr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getData(Context context, String urlStr) throws Exception {
        HttpURLConnection conn = null;
        URL url = new URL(URL_STR + urlStr);
        Log.v("request URL", URL_STR + urlStr);
        StringBuilder sb = new StringBuilder();

        conn = (HttpURLConnection) url.openConnection();

        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences("memberPref", Context.MODE_PRIVATE);
            conn.setRequestProperty("Cookie", sp.getString("sessionId", ""));
        }

        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.setConnectTimeout(1000);


        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } else {
            Log.d("Teamwith app error", "URL=" + urlStr);
        }

        conn.disconnect();

        return sb.toString();
    }
}
