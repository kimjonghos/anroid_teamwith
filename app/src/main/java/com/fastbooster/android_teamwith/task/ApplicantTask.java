package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.fastbooster.android_teamwith.ApplicantActivity;
import com.fastbooster.android_teamwith.R;
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
            List<InterviewVO> interviewVOList=new ArrayList<InterviewVO>();
            for(int i=0;i<applicantList.length();i++){
                JSONObject applicant=applicantList.getJSONObject(i);
                ApplicantVO applicantVO=new ApplicantVO(applicant);
                applicantVOList.add(applicantVO);
                JSONObject interview=interviewMap.getJSONObject(applicantVO.getApplicationId());
                InterviewVO interviewVO=new InterviewVO(interview);
                interviewVOList.add(interviewVO);
            }
            if(context instanceof ApplicantActivity){
                ApplicantActivity view = (ApplicantActivity) context;
                ListView listView=(ListView)view.findViewById(R.id.applicantListView);
//                listView.setAdapter(Appli);
            }



        }
        catch(Exception e){
            e.printStackTrace();
        }
//        ListView listView =(ListView)findViewById(R.id.applicantListView);
//
//        listView.setAdapter(adapter);
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        String teamNum=teamId.substring(7);
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