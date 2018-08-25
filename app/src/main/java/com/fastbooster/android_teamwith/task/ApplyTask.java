package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;

import com.fastbooster.android_teamwith.api.ApplyApi;
import com.fastbooster.android_teamwith.api.JoinedTeamApi;

import org.json.JSONObject;

import java.util.List;

public class ApplyTask extends AsyncTask<Void,Void,Void> {
    private final Context context;
    String teamId;
    List<String> interviewAnswer;
    List<String> interviewQuestionId;
    String applicationFreewriting;
    String roleId;

    public ApplyTask(Context context,String teamId, List<String> interviewAnswer,List<String> interviewQuestionId, String applicationFreewriting, String roleId){
        this.context=context;
        this.teamId=teamId;
        this.interviewAnswer=interviewAnswer;
        this.interviewQuestionId=interviewQuestionId;
        this.applicationFreewriting=applicationFreewriting;
        this.roleId=roleId;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Boolean result=ApplyApi.applyTeam(teamId, interviewAnswer, interviewQuestionId, applicationFreewriting, roleId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
