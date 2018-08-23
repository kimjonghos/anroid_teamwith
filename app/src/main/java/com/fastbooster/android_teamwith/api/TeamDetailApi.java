package com.fastbooster.android_teamwith.api;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class TeamDetailApi {
    public static JSONObject getTeamDetail(String teamId) throws Exception{
        JSONObject json=null;
        URL url = new URL("http://127.0.0.1:8089/api/teamSearch/1");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setReadTimeout(1000);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        int responseCode = conn.getResponseCode();
        StringBuilder sb = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            //JSON object 받아오기
            json = new JSONObject(sb.toString());
            conn.disconnect();
            return json;
        }
        conn.disconnect();
        return null;
    }
}
