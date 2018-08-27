package com.fastbooster.android_teamwith.api;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DecideApi {
    public static Boolean recruitDecide(String applicationId, String status) throws Exception {
        String key=applicationId.substring(12);
        URL url = new URL("http://192.168.30.16:8089/api/application/change/"+key+"");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        JSONObject json=new JSONObject();
        json.accumulate("status",status);

        conn.setDoOutput(true);
        conn.setDoInput(true);

        OutputStream os = conn.getOutputStream();
        os.write(json.toString().getBytes("UTF-8"));
        os.flush();

        conn.setReadTimeout(10000);
        int responseCode = conn.getResponseCode();
        StringBuilder sb = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                Log.d("@@@@@@@@@@@@",line);
                sb.append(line);
            }
            Boolean result=new Boolean(sb.toString());

            conn.disconnect();
            return result;
        }
        conn.disconnect();
        return false;
    }
}
