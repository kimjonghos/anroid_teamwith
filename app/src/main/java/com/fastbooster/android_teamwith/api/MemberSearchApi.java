package com.fastbooster.android_teamwith.api;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.fastbooster.android_teamwith.model.MemberSearchVO;
import com.fastbooster.android_teamwith.util.Criteria;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MemberSearchApi {
    static final String TAG = "member data...";
    public static String URL_STR = "http://192.168.30.16:8089/api/member";

    public static String makeQuery(Criteria cri, String[] region,
                                   String[] project, String[] role, String[] skill,
                                   String keyword) {

        Uri.Builder params = new Uri.Builder();
        if (cri != null) {
            params.appendQueryParameter("page", cri.getPage() + "");
            params.appendQueryParameter("perPageNum", cri.getPerPageNum() + "");
        }
        if (keyword != null) {
            params.appendQueryParameter("keyword", keyword);
        }
        if (region != null) {
            for (String r : region) {
                params.appendQueryParameter("region", r);
            }
        }
        if (project != null) {
            for (String p : project) {
                params.appendQueryParameter("project", p);
            }
        }
        if (role != null) {
            for (String r : role) {
                params.appendQueryParameter("role", r);
            }
        }
        if (skill != null) {
            for (String s : skill) {
                params.appendQueryParameter("skill", s);
            }
        }

        return params.toString();

    }

    public static List<MemberSearchVO> getMember(Context context, Criteria cri, String[] region,
                                                 String[] project, String[] role, String[] skill,
                                                 String keyword) {
        //  ApplicationShare app = (ApplicationShare) context.getApplicationContext();
        //  app.regionList.get("region-1");

        String query = makeQuery(cri, region, project, role, skill, keyword);


        try {
            URL url = new URL(URL_STR + query);
            Log.v(TAG,url.toString());
            HttpURLConnection conn = null;
            StringBuilder sb = new StringBuilder();

            conn = (HttpURLConnection) url.openConnection();
//connection.
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
                Log.d("Teamwith app error", "URL=" + URL_STR);
                return null;
            }
            //json

            JSONArray array = new JSONArray(sb.toString());

            //return WeatherForecast
            List<MemberSearchVO> result = new ArrayList<>();


            for (int i = 0; i < array.length(); i++) {
                JSONObject f = array.getJSONObject(i);
                boolean bb = f.isNull("roleId");
                Log.v(TAG, bb + "null");
                result.add(new MemberSearchVO(f));
            }
            return result;

        } catch (Exception e) {
            Log.d("Weather app error", e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
}
