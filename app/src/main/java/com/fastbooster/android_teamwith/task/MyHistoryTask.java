package com.fastbooster.android_teamwith.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.MyHistoryActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.TeamActivity;
import com.fastbooster.android_teamwith.TeamLeaderActivity;
import com.fastbooster.android_teamwith.api.ApiUtil;
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
    protected JSONObject doInBackground(Void... voids) {
        try {
            Criteria cri = new Criteria();
            return ApiUtil.getMyJsonObject(context,
                    "/team/myHistory");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            JSONArray myTeamList = json.getJSONArray("myTeamList");
            JSONArray myJoinedTeam = json.getJSONArray("myJoinedTeamList");
            JSONArray myApplicationList = json.getJSONArray("myApplicationList");

            MyHistoryActivity myHistoryActivity = (MyHistoryActivity) context;

            int cnt = myTeamList.length();

            if(cnt>=2){
                cnt=2;
            }

            switch (cnt) {
                case 0:
                    break;
                case 2:
                    final JSONObject myTeam2 = myTeamList.getJSONObject(1);
                    LinearLayout myTeamLayout2 = myHistoryActivity.findViewById(R.id.team2Layout);
                    TextView ytv_my_project_name_2 = myHistoryActivity.findViewById(R.id.ytv_my_team_project_name_2);
                    TextView ytv_my_team_name_2 = myHistoryActivity.findViewById(R.id.ytv_my_team_name_2);
                    TextView ytv_my_team_project_2 = myHistoryActivity.findViewById(R.id.ytv_my_team_project_2);
                    ImageView yiv_my_team_pic_2 = myHistoryActivity.findViewById(R.id.yiv_team_pic_2);
                    ImageTask imageTask2 = new ImageTask(context);
                    yiv_my_team_pic_2.setTag(myTeam2.getString("teamPic").toString());
                    imageTask2.execute(yiv_my_team_pic_2);
                    ytv_my_project_name_2.setText(myTeam2.getString("teamProjectName").toString());
                    ytv_my_team_name_2.setText(myTeam2.getString("teamName").toString());
                    ytv_my_team_project_2.setText((String) ApplicationShare.categoryList.get(myTeam2.getString("projectCategoryId").toString()));
                    myTeamLayout2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = ((Activity) context).getIntent();
                            String teamId = null;
                            String teamLeader=null;
                            SharedPreferences sp = context.getSharedPreferences("memberPref", Context.MODE_PRIVATE);
                            String memberId = sp.getString("memberId", null);
                            try {
                                teamId = myTeam2.getString("teamId");
                                teamLeader=myTeam2.getString("memberId");

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            intent.putExtra("teamId", teamId);
                            if(memberId.equals(teamLeader)){
                                intent.setClass(context, TeamLeaderActivity.class);
                            }
                            else{
                                intent=new Intent(context, TeamActivity.class);
                            }
                            context.startActivity(intent);

                        }
                    });

                case 1:
                    final JSONObject myTeam1 = myTeamList.getJSONObject(0);
                    LinearLayout myTeamLayout1 = myHistoryActivity.findViewById(R.id.team1Layout);
                    TextView ytv_my_project_name_1 = myHistoryActivity.findViewById(R.id.ytv_my_team_project_name_1);
                    TextView ytv_my_team_name_1 = myHistoryActivity.findViewById(R.id.ytv_my_team_name_1);
                    TextView ytv_my_team_project_1 = myHistoryActivity.findViewById(R.id.ytv_my_team_project_1);
                    ImageView yiv_my_team_pic_1 = myHistoryActivity.findViewById(R.id.yiv_team_pic_1);
                    ImageTask imageTask1 = new ImageTask(context);
                    yiv_my_team_pic_1.setTag(myTeam1.getString("teamPic").toString());
                    imageTask1.execute(yiv_my_team_pic_1);
                    ytv_my_project_name_1.setText(myTeam1.getString("teamProjectName").toString());
                    ytv_my_team_name_1.setText(myTeam1.getString("teamName").toString());
                    ytv_my_team_project_1.setText(ApplicationShare.categoryList.get(myTeam1.getString("projectCategoryId").toString()));
                    myTeamLayout1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = ((Activity) context).getIntent();
                            String teamId = null;
                            String teamLeader=null;
                            SharedPreferences sp = context.getSharedPreferences("memberPref", Context.MODE_PRIVATE);
                            String memberId = sp.getString("memberId", null);
                            try {
                                teamId = myTeam1.getString("teamId");
                                teamLeader=myTeam1.getString("memberId");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            intent.putExtra("teamId", teamId);
                            if(memberId.equals(teamLeader)){
                                intent.setClass(context, TeamLeaderActivity.class);
                            }
                            else{
                                intent=new Intent(context, TeamActivity.class);
                            }
                            context.startActivity(intent);

                        }
                    });
                    break;
            }

            if (myJoinedTeam.length() == 1) {
                final JSONObject joinedTeam1 = myJoinedTeam.getJSONObject(0);
                LinearLayout joinedTeamLayout1=myHistoryActivity.findViewById(R.id.jTeam1Layout);
                TextView ytv_joined_project_name_1 = myHistoryActivity.findViewById(R.id.ytv_joined_team_project_name_1);
                TextView ytv_joined_team_name_1 = myHistoryActivity.findViewById(R.id.ytv_joined_team_name_1);
                TextView ytv_joined_team_project_1 = myHistoryActivity.findViewById(R.id.ytv_joined_team_project_1);
                ImageView yiv_joined_team_pic_1 = myHistoryActivity.findViewById(R.id.yiv_joined_team_pic_1);
                ImageTask imageTask3 = new ImageTask(context);
                yiv_joined_team_pic_1.setTag(joinedTeam1.getString("teamPic").toString());
                imageTask3.execute(yiv_joined_team_pic_1);
                ytv_joined_project_name_1.setText(joinedTeam1.getString("teamProjectName").toString());
                ytv_joined_team_name_1.setText(joinedTeam1.getString("teamName").toString());
                ytv_joined_team_project_1.setText(ApplicationShare.categoryList.get(joinedTeam1.getString("projectCategoryId").toString()));
                joinedTeamLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent intent = ((Activity) context).getIntent();
                        Intent intent=new Intent();
                        String teamId = null;

                        String teamLeader=null;
                        SharedPreferences sp = context.getSharedPreferences("memberPref", Context.MODE_PRIVATE);
                        String memberId = sp.getString("memberId", null);
                        try {
                            teamId = joinedTeam1.getString("teamId");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        intent.putExtra("teamId", teamId);
                        if(memberId.equals(teamLeader)){
                            intent.setClass(context, TeamLeaderActivity.class);
                        }
                        else{
                            intent.setClass(context, TeamActivity.class);
                        }
                        context.startActivity(intent);
                    }
                });
            } else if (myJoinedTeam.length() >= 2) {
                final JSONObject joinedTeam1 = myJoinedTeam.getJSONObject(0);
                LinearLayout joinedTeamLayout1=myHistoryActivity.findViewById(R.id.jTeam1Layout);
                TextView ytv_joined_project_name_1 = myHistoryActivity.findViewById(R.id.ytv_joined_team_project_name_1);
                TextView ytv_joined_team_name_1 = myHistoryActivity.findViewById(R.id.ytv_joined_team_name_1);
                TextView ytv_joined_team_project_1 = myHistoryActivity.findViewById(R.id.ytv_joined_team_project_1);
                ImageView yiv_joined_team_pic_1 = myHistoryActivity.findViewById(R.id.yiv_joined_team_pic_1);
                ImageTask imageTask3 = new ImageTask(context);
                yiv_joined_team_pic_1.setTag(joinedTeam1.getString("teamPic").toString());
                imageTask3.execute(yiv_joined_team_pic_1);
                ytv_joined_project_name_1.setText(joinedTeam1.getString("teamProjectName").toString());
                ytv_joined_team_name_1.setText(joinedTeam1.getString("teamName").toString());
                ytv_joined_team_project_1.setText(ApplicationShare.categoryList.get(joinedTeam1.getString("projectCategoryId").toString()));
                joinedTeamLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = ((Activity) context).getIntent();
                        String teamId = null;
                        String teamLeader=null;
                        SharedPreferences sp = context.getSharedPreferences("memberPref", Context.MODE_PRIVATE);
                        String memberId = sp.getString("memberId", null);
                        try {
                            teamId = joinedTeam1.getString("teamId");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        intent.putExtra("teamId", teamId);
                        if(memberId.equals(teamLeader)){
                            intent.setClass(context, TeamLeaderActivity.class);
                        }
                        else{
                            intent=new Intent(context, TeamActivity.class);
                        }
                        context.startActivity(intent);

                    }
                });

                final JSONObject joinedTeam2 = myJoinedTeam.getJSONObject(1);
                LinearLayout joinedTeamLayout2=myHistoryActivity.findViewById(R.id.jTeam2Layout);
                TextView ytv_joined_project_name_2 = myHistoryActivity.findViewById(R.id.ytv_joined_team_project_name_2);
                TextView ytv_joined_team_name_2 = myHistoryActivity.findViewById(R.id.ytv_joined_team_name_2);
                TextView ytv_joined_team_project_2 = myHistoryActivity.findViewById(R.id.ytv_joined_team_project_2);
                ImageView yiv_joined_team_pic_2 = myHistoryActivity.findViewById(R.id.yiv_joined_team_pic_2);
                ImageTask imageTask4 = new ImageTask(context);
                yiv_joined_team_pic_2.setTag(joinedTeam2.getString("teamPic").toString());
                imageTask4.execute(yiv_joined_team_pic_2);
                ytv_joined_project_name_2.setText(joinedTeam2.getString("teamProjectName").toString());
                ytv_joined_team_name_2.setText(joinedTeam2.getString("teamName").toString());
                ytv_joined_team_project_2.setText(ApplicationShare.categoryList.get(joinedTeam2.getString("projectCategoryId").toString()));
                joinedTeamLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = ((Activity) context).getIntent();
                        String teamId = null;
                        String teamLeader=null;
                        SharedPreferences sp = context.getSharedPreferences("memberPref", Context.MODE_PRIVATE);
                        String memberId = sp.getString("memberId", null);
                        try {
                            teamId = joinedTeam2.getString("teamId");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        intent.putExtra("teamId", teamId);
                        if(memberId.equals(teamLeader)){
                            intent.setClass(context, TeamLeaderActivity.class);
                        }
                        else{
                            intent=new Intent(context, TeamActivity.class);
                        }
                        context.startActivity(intent);
                    }
                });
            }

            if (myApplicationList.length() == 1) {
                JSONObject myApplication1 = myApplicationList.getJSONObject(0);
                TextView ytv_my_application_team_name_1 = myHistoryActivity.findViewById(R.id.ytv_my_application_team_name_1);
                TextView ytv_my_application_role_1 = myHistoryActivity.findViewById(R.id.ytv_my_application_role_1);
                TextView ytv_my_application_status_1 = myHistoryActivity.findViewById(R.id.ytv_my_application_status_1);
                ytv_my_application_team_name_1.setText(myApplication1.getString("teamName").toString());
                ytv_my_application_role_1.setText(ApplicationShare.roleList.get(myApplication1.getString("roleId").toString()));
                ytv_my_application_status_1.setText(ApplicationShare.applicationStatus.get(myApplication1.getString("applicationStatus").toString()));
            } else if (myApplicationList.length() == 2) {
                JSONObject myApplication1 = myApplicationList.getJSONObject(0);
                TextView ytv_my_application_team_name_1 = myHistoryActivity.findViewById(R.id.ytv_my_application_team_name_1);
                TextView ytv_my_application_role_1 = myHistoryActivity.findViewById(R.id.ytv_my_application_role_1);
                TextView ytv_my_application_status_1 = myHistoryActivity.findViewById(R.id.ytv_my_application_status_1);
                ytv_my_application_team_name_1.setText(myApplication1.getString("teamName").toString());
                ytv_my_application_role_1.setText(ApplicationShare.roleList.get(myApplication1.getString("roleId").toString()));
                ytv_my_application_status_1.setText(ApplicationShare.applicationStatus.get(myApplication1.getString("applicationStatus").toString()));

                JSONObject myApplication2 = myApplicationList.getJSONObject(1);
                TextView ytv_my_application_team_name_2 = myHistoryActivity.findViewById(R.id.ytv_my_application_team_name_2);
                TextView ytv_my_application_role_2 = myHistoryActivity.findViewById(R.id.ytv_my_application_role_2);
                TextView ytv_my_application_status_2 = myHistoryActivity.findViewById(R.id.ytv_my_application_status_2);
                ytv_my_application_team_name_2.setText(myApplication2.getString("teamName").toString());
                ytv_my_application_role_2.setText(ApplicationShare.roleList.get(myApplication2.getString("roleId").toString()));
                ytv_my_application_status_2.setText(ApplicationShare.applicationStatus.get(myApplication2.getString("applicationStatus").toString()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
