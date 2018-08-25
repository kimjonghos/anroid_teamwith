package com.fastbooster.android_teamwith.api;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PologApi {

    public static final String URL_STR = "http://192.168.30.64:8089/api/polog/";

    public static JSONObject getPologInfo(String memberId) throws Exception {
        JSONObject json = null;
        HttpURLConnection conn = null;

        URL url = new URL(URL_STR + memberId);
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(1000);
            conn.setDoInput(true);


            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-type", "application/json");
            int responseCode = conn.getResponseCode();

            StringBuilder sb = new StringBuilder();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                //JSON object 받아오기
                br.close();
                json = new JSONObject(sb.toString());
                conn.disconnect();
                return json;
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();

        }

        return null;
    }
}
