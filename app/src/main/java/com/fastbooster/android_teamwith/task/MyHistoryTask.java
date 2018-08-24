package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.MyHistoryActivity;
import com.fastbooster.android_teamwith.MyTeamActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.adapter.MyTeamAdapter;
import com.fastbooster.android_teamwith.api.MyTeamApi;
import com.fastbooster.android_teamwith.model.TeamSimpleVO;
import com.fastbooster.android_teamwith.util.Criteria;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyHistoryTask extends AsyncTask<Void, Void, JSONObject> {
    private final Context context;

    public MyHistoryTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            JSONArray myTeamList = json.getJSONArray("myTeamList");

            MyHistoryActivity myHistoryActivity = (MyHistoryActivity) context;

            switch(myTeamList.length()) {
                case 0:
                    break;
                case 2:
                    JSONObject myTeam2 = myTeamList.getJSONObject(1);
                    TextView ytv_my_project_name_2 = myHistoryActivity.findViewById(R.id.ytv_my_team_project_name_2);
                    TextView ytv_my_team_name_2 = myHistoryActivity.findViewById(R.id.ytv_my_team_name_2);
                    TextView ytv_my_team_project_2 = myHistoryActivity.findViewById(R.id.ytv_my_team_project_2);
                    ytv_my_project_name_2.setText(myTeam2.getString("teamProjectName").toString());
                    ytv_my_team_name_2.setText(myTeam2.getString("teamName").toString());
                    ytv_my_team_project_2.setText(myTeam2.getString("projectCategoryId").toString());
                case 1:
                    JSONObject myTeam1 = myTeamList.getJSONObject(0);
                    TextView ytv_my_project_name_1 = myHistoryActivity.findViewById(R.id.ytv_my_team_project_name_1);
                    TextView ytv_my_team_name_1 = myHistoryActivity.findViewById(R.id.ytv_my_team_name_1);
                    TextView ytv_my_team_project_1 = myHistoryActivity.findViewById(R.id.ytv_my_team_project_1);
                    ytv_my_project_name_1.setText(myTeam1.getString("teamProjectName").toString());
                    ytv_my_team_name_1.setText(myTeam1.getString("teamName").toString());
                    ytv_my_team_project_1.setText(myTeam1.getString("projectCategoryId").toString());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        JSONObject json = null;
        try {
            json = MyTeamApi.getMyTeam(new Criteria(1, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;

    }
}
