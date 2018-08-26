package com.fastbooster.android_teamwith.api;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApplyApi {
    public static Boolean applyTeam(String teamId, List<String> interviewAnswer, List<String> interviewQuestionId, String freewriting,String roleId) throws Exception {
//        String key=teamId.substring(7);
        String key="1";
        URL url = new URL("http://192.168.30.27:8089/api/application/apply/"+key);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        for(String a : interviewAnswer){
            Log.d("@@@@@@@@@@@@@@@",a);
        }

        JSONObject json=new JSONObject();

        JSONArray jsonAryInterviewQuestionId=new JSONArray();
        JSONArray jsonAryInterviewAnswer=new JSONArray();
        for(int i=0;i<interviewQuestionId.size();i++){
            jsonAryInterviewQuestionId.put(i,interviewQuestionId.get(i));
            jsonAryInterviewAnswer.put(i,interviewAnswer.get(i));
        }
        json.accumulate("applicationFreewriting",freewriting);
        json.accumulate("roleId",roleId);
        json.accumulate("interviewQuestionId",jsonAryInterviewQuestionId);
        json.accumulate("interviewAnswer",jsonAryInterviewAnswer);
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Content-type", "application/json");
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
