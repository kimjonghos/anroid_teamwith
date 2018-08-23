package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.TeamActivity;
import com.fastbooster.android_teamwith.api.TeamDetailApi;
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
            TeamDetailVO teamInfo = new TeamDetailVO(jsonObject.getJSONObject("teamInfo"));
            if (context instanceof TeamActivity) {
                TeamActivity view = (TeamActivity) context;
                TextView tvTeamProjectName = view.findViewById(R.id.htvTeamProjectName);
                tvTeamProjectName.setText(teamInfo.getTeamProjectName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected JSONObject doInBackground(String... teamId) {
        JSONObject json=null;
        try {
            json = TeamDetailApi.getTeamDetail(teamId[0]);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }
}
