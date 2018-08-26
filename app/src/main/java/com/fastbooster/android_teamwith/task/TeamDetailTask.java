package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.TeamActivity;
import com.fastbooster.android_teamwith.adapter.TeamDetailFaqAdapter;
import com.fastbooster.android_teamwith.adapter.TeamDetailRecruitAdapter;
import com.fastbooster.android_teamwith.api.TeamDetailApi;
import com.fastbooster.android_teamwith.model.FaqVO;
import com.fastbooster.android_teamwith.model.InterviewQuestionDTO;
import com.fastbooster.android_teamwith.model.RecruitVO;
import com.fastbooster.android_teamwith.model.RequireSkillVO;
import com.fastbooster.android_teamwith.model.TeamDetailVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeamDetailTask extends AsyncTask<Void, Void, JSONObject> {
    private final Context context;
    private final String teamId;

    public TeamDetailTask(Context context,String teamId) {
        this.context = context;
        this.teamId=teamId;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        try {
            //팀 정보 파싱
            TeamDetailVO teamInfo = new TeamDetailVO(jsonObject.getJSONObject("teamInfo"));
            //지원 가능 여부 파싱
            String canApply=jsonObject.getString("canApply");
            //d-day 파싱
            int dDay=jsonObject.getInt("dDay");

            //모집 정보 파싱
            List<RecruitVO> recruitList=new ArrayList<RecruitVO>();
            JSONArray recruitInfo=jsonObject.getJSONArray("recruitInfo");
            for(int i=0;i<recruitInfo.length();i++){
                Log.d("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@",recruitInfo.toString());
                RecruitVO recruit=new RecruitVO(recruitInfo.getJSONObject(i));
                recruitList.add(recruit);
            }
            //간단 면접 정보 파싱
            List<InterviewQuestionDTO> interviewList=new ArrayList<InterviewQuestionDTO>();
            JSONArray interviewInfo=jsonObject.getJSONArray("interviewInfo");
            for(int i=0;i<interviewInfo.length();i++) {
                InterviewQuestionDTO interview = new InterviewQuestionDTO(interviewInfo.getJSONObject(i));
                interviewList.add(interview);
            }

            //FAQ 정보 파싱
            List<FaqVO> faqList=new ArrayList<FaqVO>();
            JSONArray faqInfo=jsonObject.getJSONArray("faqInfo");
            for(int i=0;i<faqInfo.length();i++){
                FaqVO faq=new FaqVO(faqInfo.getJSONObject(i));
                faqList.add(faq);
            }

            //요구기술 정보 파싱
            List<RequireSkillVO> requireSkillList=new ArrayList<RequireSkillVO>();
            JSONArray requireSkillInfo=jsonObject.getJSONArray("requireSkills");
            String before=null;
            for(int i=0;i<requireSkillInfo.length();i++){
                RequireSkillVO requireSkill=new RequireSkillVO(requireSkillInfo.getJSONObject(i));
                requireSkillList.add(requireSkill);
            }

            if (context instanceof TeamActivity) {
                TeamActivity view = (TeamActivity) context;
                TextView tvTeamProjectName = view.findViewById(R.id.hktvProjectName);
                tvTeamProjectName.setText(teamInfo.getTeamProjectName());
                TextView tvTeamProjectCategory=view.findViewById(R.id.hktvProjectCategory);
                tvTeamProjectCategory.setText(teamInfo.getProjectCategoryId());
                TextView tvTeamName=view.findViewById(R.id.hktvTeamName);
                tvTeamName.setText(teamInfo.getTeamName());
                TextView tvRegion=view.findViewById(R.id.hktvRegion);
                tvRegion.setText(teamInfo.getRegionId());
                TextView tvTeamEndDate=view.findViewById(R.id.hktvTeamEndDate);
                tvTeamEndDate.setText("D"+dDay);
                TextView tvLeaderName=view.findViewById(R.id.hktvLeaderName);
                tvLeaderName.setText(teamInfo.getMemberName());
                TextView tvLeaderRole=view.findViewById(R.id.hktvLeaderRole);
                tvLeaderRole.setText(teamInfo.getRoleId());
                TextView tvTeamSummary=view.findViewById(R.id.hktvTeamSummary);
                tvTeamSummary.setText(teamInfo.getTeamSummary());
                TextView tvTeamContent=view.findViewById(R.id.hktvTeamContent);
                tvTeamContent.setText(teamInfo.getTeamContent());
                TextView tvTeamContest=view.findViewById(R.id.hktvTeamContest);
                tvTeamContest.setText(teamInfo.getTeamContestName());
                ListView recruitListView=view.findViewById(R.id.hkRecruitListView);
                TeamDetailRecruitAdapter recruitAdapter=new TeamDetailRecruitAdapter(context,teamId,recruitList,interviewList,requireSkillList);
                recruitListView.setAdapter(recruitAdapter);
                setListViewHeightBasedOnChildren(recruitListView);
                ListView faqListView=view.findViewById(R.id.hkFaqListView);
                TeamDetailFaqAdapter faqAdapter=new TeamDetailFaqAdapter(context,faqList);
                faqListView.setAdapter(faqAdapter);
                setListViewHeightBasedOnChildren(faqListView);




            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected JSONObject doInBackground(Void... voids) {
        JSONObject json=null;
        try {
            json = TeamDetailApi.getTeamDetail(teamId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
