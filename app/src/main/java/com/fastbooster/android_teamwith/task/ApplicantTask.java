package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.fastbooster.android_teamwith.ApplicantActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.adapter.ApplicantAdapter;
import com.fastbooster.android_teamwith.api.ApiUtil;
import com.fastbooster.android_teamwith.model.ApplicantVO;
import com.fastbooster.android_teamwith.model.InterviewVO;
import com.fastbooster.android_teamwith.model.TeamSimpleVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApplicantTask extends AsyncTask<Void,Void,JSONObject> {
    private final Context context;
    private String teamId;

    public ApplicantTask(Context context,String teamId){
        this.context=context;
        this.teamId=teamId;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            TeamSimpleVO teamInfo = new TeamSimpleVO(json.getJSONObject("teamInfo"));
            JSONArray applicantList=json.getJSONArray("applicantList");
            JSONObject interviewMap=json.getJSONObject("interviewMap");
            List<ApplicantVO> applicantVOList=new ArrayList<ApplicantVO>();
            List<List<InterviewVO>> interviewList=new ArrayList<List<InterviewVO>>();

            for(int i=0;i<applicantList.length();i++){
                JSONObject applicant=applicantList.getJSONObject(i);
                ApplicantVO applicantVO=new ApplicantVO(applicant);
                applicantVOList.add(applicantVO);
                JSONArray interview=interviewMap.getJSONArray(applicantVO.getApplicationId());
                List<InterviewVO> interviewVOList=new ArrayList<InterviewVO>();
                for(int j=0;j<interview.length();j++){
                    JSONObject interviewJSON=interview.getJSONObject(j);
                    InterviewVO interviewVO=new InterviewVO(interviewJSON);
                    interviewVOList.add(interviewVO);
                }
                interviewList.add(interviewVOList);
            }
            if(context instanceof ApplicantActivity){
                ApplicantActivity view = (ApplicantActivity) context;
                ListView listView=(ListView)view.findViewById(R.id.applicantListView);
                ApplicantAdapter adapter=new ApplicantAdapter(context,applicantVOList,interviewList);
                listView.setAdapter(adapter);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        String teamNum=teamId.substring(5);
        JSONObject obj=null;
        try {
            obj= ApiUtil.getMyJsonObject(context,"/teamInfo/applicant/"+teamNum);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}