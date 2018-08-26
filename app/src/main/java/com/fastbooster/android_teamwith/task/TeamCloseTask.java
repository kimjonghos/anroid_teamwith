package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;

import com.fastbooster.android_teamwith.api.ApplyApi;

import java.util.List;

public class TeamCloseTask extends AsyncTask<String,Void,Void> {
    private final Context context;

    public TeamCloseTask(Context context){
        this.context=context;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(String... teamId) {
        try {
//            Boolean result= ApplyApi.applyTeam(teamId, interviewAnswer, interviewQuestionId, applicationFreewriting, roleId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
