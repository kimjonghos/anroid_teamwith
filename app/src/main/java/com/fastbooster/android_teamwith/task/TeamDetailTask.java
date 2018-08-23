package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.TeamActivity;
import com.fastbooster.android_teamwith.model.TeamDetailVO;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TeamDetailTask extends AsyncTask<String, Void, JSONObject> {
    private final Context context;

    public TeamDetailTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        try {
            TeamDetailVO teamInfo = new TeamDetailVO(jsonObject);
            if(context instanceof TeamActivity){
                TeamActivity view=(TeamActivity)context;
                TextView tvTeamProjectName=view.findViewById(R.id.htvTeamProjectName);
                tvTeamProjectName.setText(teamInfo.getTeamProjectName());
            }

        }catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    protected JSONObject doInBackground(String... teamId) {
       // String serverName = "http://localhost:8089/api/";
        HttpURLConnection conn = null;
        JSONObject json =null;
        try {
            URL url = new URL("http://localhost:8089/api/teamSearch/1");
            //URL url = new URL(serverName + "teamSearch/" + teamId[0]);
            conn = (HttpURLConnection) url.openConnection();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return json;
    }
}
