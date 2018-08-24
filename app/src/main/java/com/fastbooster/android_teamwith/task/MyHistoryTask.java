package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.MyHistoryActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.api.MyTeamApi;
import com.fastbooster.android_teamwith.share.ApplicationShare;
import com.fastbooster.android_teamwith.util.Criteria;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyHistoryTask extends AsyncTask<Void, Void, JSONObject> {
    private final Context context;

    public MyHistoryTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            JSONArray myTeamList = json.getJSONArray("myTeamList");
            JSONArray myJoinedTeam = json.getJSONArray("myJoinedTeamList");
            JSONArray myApplicationList = json.getJSONArray("myApplicationList");

            MyHistoryActivity myHistoryActivity = (MyHistoryActivity) context;

            switch (myTeamList.length()) {
                case 0:
                    break;
                case 2:
                    JSONObject myTeam2 = myTeamList.getJSONObject(1);
                    TextView ytv_my_project_name_2 = myHistoryActivity.findViewById(R.id.ytv_my_team_project_name_2);
                    TextView ytv_my_team_name_2 = myHistoryActivity.findViewById(R.id.ytv_my_team_name_2);
                    TextView ytv_my_team_project_2 = myHistoryActivity.findViewById(R.id.ytv_my_team_project_2);
                    ImageView yiv_my_team_pic_2 = myHistoryActivity.findViewById(R.id.yiv_team_pic_2);
                    ImageViewTask imageViewTask2 = new ImageViewTask(context);
                    yiv_my_team_pic_2.setTag(myTeam2.getString("teamPic").toString());
                    imageViewTask2.execute(yiv_my_team_pic_2);
                    ytv_my_project_name_2.setText(myTeam2.getString("teamProjectName").toString());
                    ytv_my_team_name_2.setText(myTeam2.getString("teamName").toString());
                    ytv_my_team_project_2.setText((String) ApplicationShare.categoryList.get(myTeam2.getString("projectCategoryId").toString()));
                case 1:
                    JSONObject myTeam1 = myTeamList.getJSONObject(0);
                    TextView ytv_my_project_name_1 = myHistoryActivity.findViewById(R.id.ytv_my_team_project_name_1);
                    TextView ytv_my_team_name_1 = myHistoryActivity.findViewById(R.id.ytv_my_team_name_1);
                    TextView ytv_my_team_project_1 = myHistoryActivity.findViewById(R.id.ytv_my_team_project_1);
                    ImageView yiv_my_team_pic_1 = myHistoryActivity.findViewById(R.id.yiv_team_pic_1);
                    ImageViewTask imageViewTask1 = new ImageViewTask(context);
                    yiv_my_team_pic_1.setTag(myTeam1.getString("teamPic").toString());
                    imageViewTask1.execute(yiv_my_team_pic_1);
                    ytv_my_project_name_1.setText(myTeam1.getString("teamProjectName").toString());
                    ytv_my_team_name_1.setText(myTeam1.getString("teamName").toString());
                    ytv_my_team_project_1.setText((String) ApplicationShare.categoryList.get(myTeam1.getString("projectCategoryId").toString()));
                    break;
            }

            if (myJoinedTeam.length() == 1) {
                JSONObject joinedTeam1 = myJoinedTeam.getJSONObject(0);
                TextView ytv_joined_project_name_1 = myHistoryActivity.findViewById(R.id.ytv_joined_team_project_name_1);
                TextView ytv_joined_team_name_1 = myHistoryActivity.findViewById(R.id.ytv_joined_team_name_1);
                TextView ytv_joined_team_project_1 = myHistoryActivity.findViewById(R.id.ytv_joined_team_project_1);
                ImageView yiv_joined_team_pic_1 = myHistoryActivity.findViewById(R.id.yiv_joined_team_pic_1);
                ImageViewTask imageViewTask3 = new ImageViewTask(context);
                yiv_joined_team_pic_1.setTag(joinedTeam1.getString("teamPic").toString());
                imageViewTask3.execute(yiv_joined_team_pic_1);
                ytv_joined_project_name_1.setText(joinedTeam1.getString("teamProjectName").toString());
                ytv_joined_team_name_1.setText(joinedTeam1.getString("teamName").toString());
                ytv_joined_team_project_1.setText((String) ApplicationShare.categoryList.get(joinedTeam1.getString("projectCategoryId").toString()));
            } else if (myJoinedTeam.length() == 2) {
                JSONObject joinedTeam1 = myJoinedTeam.getJSONObject(0);
                TextView ytv_joined_project_name_1 = myHistoryActivity.findViewById(R.id.ytv_joined_team_project_name_1);
                TextView ytv_joined_team_name_1 = myHistoryActivity.findViewById(R.id.ytv_joined_team_name_1);
                TextView ytv_joined_team_project_1 = myHistoryActivity.findViewById(R.id.ytv_joined_team_project_1);
                ImageView yiv_joined_team_pic_1 = myHistoryActivity.findViewById(R.id.yiv_joined_team_pic_1);
                ImageViewTask imageViewTask3 = new ImageViewTask(context);
                yiv_joined_team_pic_1.setTag(joinedTeam1.getString("teamPic").toString());
                imageViewTask3.execute(yiv_joined_team_pic_1);
                ytv_joined_project_name_1.setText(joinedTeam1.getString("teamProjectName").toString());
                ytv_joined_team_name_1.setText(joinedTeam1.getString("teamName").toString());
                ytv_joined_team_project_1.setText((String) ApplicationShare.categoryList.get(joinedTeam1.getString("projectCategoryId").toString()));

                JSONObject joinedTeam2 = myJoinedTeam.getJSONObject(1);
                TextView ytv_joined_project_name_2 = myHistoryActivity.findViewById(R.id.ytv_joined_team_project_name_2);
                TextView ytv_joined_team_name_2 = myHistoryActivity.findViewById(R.id.ytv_joined_team_name_2);
                TextView ytv_joined_team_project_2 = myHistoryActivity.findViewById(R.id.ytv_joined_team_project_2);
                ImageView yiv_joined_team_pic_2 = myHistoryActivity.findViewById(R.id.yiv_joined_team_pic_2);
                ImageViewTask imageViewTask4 = new ImageViewTask(context);
                yiv_joined_team_pic_2.setTag(joinedTeam2.getString("teamPic").toString());
                imageViewTask4.execute(yiv_joined_team_pic_2);
                ytv_joined_project_name_2.setText(joinedTeam2.getString("teamProjectName").toString());
                ytv_joined_team_name_2.setText(joinedTeam2.getString("teamName").toString());
                ytv_joined_team_project_2.setText((String) ApplicationShare.categoryList.get(joinedTeam2.getString("projectCategoryId").toString()));
            }

            if (myApplicationList.length() == 1) {
                JSONObject myApplication1 = myApplicationList.getJSONObject(0);
                TextView ytv_my_application_team_name_1 = myHistoryActivity.findViewById(R.id.ytv_my_application_team_name_1);
                TextView ytv_my_application_role_1 = myHistoryActivity.findViewById(R.id.ytv_my_application_role_1);
                TextView ytv_my_application_status_1 = myHistoryActivity.findViewById(R.id.ytv_my_application_status_1);
                ytv_my_application_team_name_1.setText(myApplication1.getString("teamName").toString());
                ytv_my_application_role_1.setText((String) ApplicationShare.roleList.get(myApplication1.getString("roleId").toString()));
                ytv_my_application_status_1.setText((String) ApplicationShare.applicationStatus.get(myApplication1.getString("applicationStatus").toString()));
            } else if (myApplicationList.length() == 2) {
                JSONObject myApplication1 = myApplicationList.getJSONObject(0);
                TextView ytv_my_application_team_name_1 = myHistoryActivity.findViewById(R.id.ytv_my_application_team_name_1);
                TextView ytv_my_application_role_1 = myHistoryActivity.findViewById(R.id.ytv_my_application_role_1);
                TextView ytv_my_application_status_1 = myHistoryActivity.findViewById(R.id.ytv_my_application_status_1);
                ytv_my_application_team_name_1.setText(myApplication1.getString("teamName").toString());
                ytv_my_application_role_1.setText((String) ApplicationShare.roleList.get(myApplication1.getString("roleId").toString()));
                ytv_my_application_status_1.setText((String) ApplicationShare.applicationStatus.get(myApplication1.getString("applicationStatus").toString()));

                JSONObject myApplication2 = myApplicationList.getJSONObject(1);
                TextView ytv_my_application_team_name_2 = myHistoryActivity.findViewById(R.id.ytv_my_application_team_name_2);
                TextView ytv_my_application_role_2 = myHistoryActivity.findViewById(R.id.ytv_my_application_role_2);
                TextView ytv_my_application_status_2 = myHistoryActivity.findViewById(R.id.ytv_my_application_status_2);
                ytv_my_application_team_name_2.setText(myApplication2.getString("teamName").toString());
                ytv_my_application_role_2.setText((String) ApplicationShare.roleList.get(myApplication2.getString("roleId").toString()));
                ytv_my_application_status_2.setText((String) ApplicationShare.applicationStatus.get(myApplication2.getString("applicationStatus").toString()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        JSONObject json = null;
        try {
            json = MyTeamApi.getMyHistory(new Criteria(1, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;

    }
}
