package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import com.fastbooster.android_teamwith.JoinedTeamActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.adapter.TeamGridViewAdapter;
import com.fastbooster.android_teamwith.api.ApiUtil;
import com.fastbooster.android_teamwith.model.TeamSimpleVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JoinedTeamTask extends AsyncTask<Void, Void, JSONObject> {
    private static final String URL_STR = "http://192.168.30.64:8089/api/teamInfo/joinedTeam";
    private final Context context;

    public JoinedTeamTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        List<TeamSimpleVO> data = new ArrayList<TeamSimpleVO>();
        try {
            JSONArray joinedTeamList = json.getJSONArray("joinedTeamList");
            for (int i = 0; i < joinedTeamList.length(); i++) {
                TeamSimpleVO teamSimpleVO = new TeamSimpleVO(joinedTeamList.getJSONObject(i));
                data.add(teamSimpleVO);
            }

            if (context instanceof JoinedTeamActivity) {
                JoinedTeamActivity joinedTeamActivity = (JoinedTeamActivity) context;
                GridView joinedTeamGridView = (GridView) joinedTeamActivity.findViewById(R.id.hkJoinedTeamGridView);

                TeamGridViewAdapter adapter = new TeamGridViewAdapter(context, data);
                joinedTeamGridView.setAdapter(adapter);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onPostExecute(json);

    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        try {
            return ApiUtil.getJsonObject(URL_STR);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
