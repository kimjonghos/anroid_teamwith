package com.fastbooster.android_teamwith.api;

import com.fastbooster.android_teamwith.util.Criteria;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyTeamApi {

    public static JSONObject getMyTeam(Criteria cri) throws Exception {
        JSONObject json = null;
        URL url = new URL("http://192.168.30.64:8089/api/team/myTeam?page=" + cri.getPage() + "&perPageNum=" + cri.getPerPageNum());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setReadTimeout(10000);
        conn.setDoInput(true);
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

    public static JSONObject getMyHistory(Criteria cri) throws Exception {
        JSONObject json = null;
        URL url = new URL("http://192.168.30.64:8089/api/team/myHistory?page=" + cri.getPage() + "&perPageNum=" + cri.getPerPageNum());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setReadTimeout(10000);
        conn.setDoInput(true);
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
