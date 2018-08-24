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
    public static String URL_STR = "http://192.168.30.7:8089/api/member";

    public static String makeQuery(Criteria cri, List<String> region,
                                   List<String> project, List<String> role, List<String> skill,
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

    public static List<MemberSearchVO> getMember(Context context, Criteria cri, List<String> region,
                                                 List<String> project, List<String> role, List<String> skill,
                                                 String keyword) {
        //  ApplicationShare app = (ApplicationShare) context.getApplicationContext();
        //  app.regionList.get("region-1");

        String query = makeQuery(cri, region, project, role, skill, keyword);


        try {
            URL url = new URL(URL_STR + query);
            Log.v(TAG, url.toString());
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

            //return
            List<MemberSearchVO> result = new ArrayList<>();

            Log.v("len",""+array.length());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                result.add(new MemberSearchVO(obj));
            }
            return result;

        } catch (Exception e) {
            Log.d("Teamwith app error", e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
}
