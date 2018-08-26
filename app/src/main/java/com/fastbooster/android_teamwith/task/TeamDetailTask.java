package com.fastbooster.android_teamwith.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.ApplicantActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.TeamActivity;
import com.fastbooster.android_teamwith.TeamLeaderActivity;
import com.fastbooster.android_teamwith.adapter.TeamDetailFaqAdapter;
import com.fastbooster.android_teamwith.adapter.TeamDetailRecruitAdapter;
import com.fastbooster.android_teamwith.api.ApiUtil;
import com.fastbooster.android_teamwith.model.FaqVO;
import com.fastbooster.android_teamwith.model.InterviewQuestionDTO;
import com.fastbooster.android_teamwith.model.RecruitVO;
import com.fastbooster.android_teamwith.model.RequireSkillVO;
import com.fastbooster.android_teamwith.model.TeamDetailVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeamDetailTask extends AsyncTask<Void, Void, Object[]> {
    private final Context context;
    private final String teamId;

    public TeamDetailTask(Context context, String teamId) {
        this.context = context;
        this.teamId = teamId;
    }

    @Override
    protected Object[] doInBackground(Void... voids) {
        JSONObject jsonObject = null;
        try {
            Log.v("team", "/teamSearch/" + teamId.substring(5));
            jsonObject = ApiUtil.getJsonObject("/teamSearch/" + teamId.substring(5));

            //팀 정보 파싱
            TeamDetailVO teamInfo = new TeamDetailVO(jsonObject.getJSONObject("teamInfo"));
            //지원 가능 여부 파싱
            String canApply = jsonObject.getString("canApply");
            //d-day 파싱
            int dDay = jsonObject.getInt("dDay");

            //모집 정보 파싱
            List<RecruitVO> recruitList = new ArrayList<RecruitVO>();
            JSONArray recruitInfo = jsonObject.getJSONArray("recruitInfo");
            for (int i = 0; i < recruitInfo.length(); i++) {
                Log.d("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@", recruitInfo.toString());
                RecruitVO recruit = new RecruitVO(recruitInfo.getJSONObject(i));
                recruitList.add(recruit);
            }
            //간단 면접 정보 파싱
            List<InterviewQuestionDTO> interviewList = new ArrayList<InterviewQuestionDTO>();
            JSONArray interviewInfo = jsonObject.getJSONArray("interviewInfo");
            for (int i = 0; i < interviewInfo.length(); i++) {
                InterviewQuestionDTO interview = new InterviewQuestionDTO(interviewInfo.getJSONObject(i));
                interviewList.add(interview);
            }

            //FAQ 정보 파싱
            List<FaqVO> faqList = new ArrayList<FaqVO>();
            JSONArray faqInfo = jsonObject.getJSONArray("faqInfo");
            for (int i = 0; i < faqInfo.length(); i++) {
                FaqVO faq = new FaqVO(faqInfo.getJSONObject(i));
                faqList.add(faq);
            }

            //요구기술 정보 파싱
            List<RequireSkillVO> requireSkillList = new ArrayList<RequireSkillVO>();
            JSONArray requireSkillInfo = jsonObject.getJSONArray("requireSkills");
            String before = null;
            for (int i = 0; i < requireSkillInfo.length(); i++) {
                RequireSkillVO requireSkill = new RequireSkillVO(requireSkillInfo.getJSONObject(i));
                requireSkillList.add(requireSkill);
            }
            return new Object[]{teamInfo, canApply, dDay, recruitList, interviewList, faqList,
                    requireSkillList};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(Object[] data) {
        try {
            TeamDetailVO teamInfo = (TeamDetailVO) data[0];
            String canApply = (String) data[1];
            int dDay = (int) data[2];
            List<RecruitVO> recruitList = (List<RecruitVO>) data[3];
            List<InterviewQuestionDTO> interviewList = (List<InterviewQuestionDTO>) data[4];
            List<FaqVO> faqList = (List<FaqVO>) data[5];
            List<RequireSkillVO> requireSkillList = (List<RequireSkillVO>) data[6];

            Activity view = null;
            if (context instanceof TeamActivity) {
                view = (TeamActivity) context;
            } else if (context instanceof TeamLeaderActivity) {
                view = (TeamLeaderActivity) context;
                Button btnClose = (Button) view.findViewById(R.id.btnClose);
                if (dDay < 0 || !teamInfo.getTeamStatus().equals("0")) {
                    btnClose.setVisibility(View.GONE);
                } else {
                    btnClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TeamCloseTask teamCloseTask = new TeamCloseTask(context);
                            teamCloseTask.execute(teamId);
                            Intent intent=((TeamLeaderActivity) context).getIntent();
                            ((TeamLeaderActivity) context).finish();
                            ((TeamLeaderActivity) context).startActivity(intent);
                        }
                    });
                }

                Button btnApplicant = (Button) view.findViewById(R.id.btnApplicant);
                btnApplicant.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=((TeamLeaderActivity) context).getIntent();
                        intent.setClass(context, ApplicantActivity.class);
                        context.startActivity(intent);
                    }
                });
            }
            TextView tvTeamProjectName = view.findViewById(R.id.hktvProjectName);
            tvTeamProjectName.setText(teamInfo.getTeamProjectName());
            TextView tvTeamProjectCategory = view.findViewById(R.id.hktvProjectCategory);
            tvTeamProjectCategory.setText(teamInfo.getProjectCategoryId());
            TextView tvTeamName = view.findViewById(R.id.hktvTeamName);
            tvTeamName.setText(teamInfo.getTeamName());
            TextView tvRegion = view.findViewById(R.id.hktvRegion);
            tvRegion.setText(teamInfo.getRegionId());
            TextView tvTeamEndDate = view.findViewById(R.id.hktvTeamEndDate);
            tvTeamEndDate.setText("D" + dDay);
            TextView tvLeaderName = view.findViewById(R.id.hktvLeaderName);
            tvLeaderName.setText(teamInfo.getMemberName());
            TextView tvLeaderRole = view.findViewById(R.id.hktvLeaderRole);
            tvLeaderRole.setText(teamInfo.getRoleId());
            TextView tvTeamSummary = view.findViewById(R.id.hktvTeamSummary);
            tvTeamSummary.setText(teamInfo.getTeamSummary());
            TextView tvTeamContent = view.findViewById(R.id.hktvTeamContent);
            tvTeamContent.setText(teamInfo.getTeamContent());
            TextView tvTeamContest = view.findViewById(R.id.hktvTeamContest);
            tvTeamContest.setText(teamInfo.getTeamContestName());
            ListView recruitListView = view.findViewById(R.id.hkRecruitListView);
            ImageView ivTeamPic = view.findViewById(R.id.hkivTeamPic);
            ivTeamPic.setTag(teamInfo.getTeamPic());
            ImageView ivLeaderPic = view.findViewById(R.id.hkivLeaderPic);
            ivLeaderPic.setTag(teamInfo.getMemberPic());
            ImageTask teamPicImageTask = new ImageTask(context);
            teamPicImageTask.execute(ivTeamPic);
            MemberImageTask leaderPicImageTask = new MemberImageTask(context);
            leaderPicImageTask.execute(ivLeaderPic);

            TeamDetailRecruitAdapter recruitAdapter = new TeamDetailRecruitAdapter(context, teamId, recruitList, interviewList, requireSkillList);
            recruitListView.setAdapter(recruitAdapter);
            setListViewHeightBasedOnChildren(recruitListView);
            ListView faqListView = view.findViewById(R.id.hkFaqListView);
            TeamDetailFaqAdapter faqAdapter = new TeamDetailFaqAdapter(context, faqList);
            faqListView.setAdapter(faqAdapter);
            setListViewHeightBasedOnChildren(faqListView);


        } catch (Exception e) {
            e.printStackTrace();
        }
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
