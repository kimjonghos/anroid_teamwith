package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.fastbooster.android_teamwith.TeamLeaderActivity;
import com.fastbooster.android_teamwith.api.ApiUtil;

import org.json.JSONObject;

public class TeamCloseTask extends AsyncTask<String,Void,JSONObject> {
    private final Context context;

    public TeamCloseTask(Context context){
        this.context=context;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        if(context instanceof TeamLeaderActivity){
            TeamLeaderActivity view = (TeamLeaderActivity) context;
            Intent intent=view.getIntent();
            intent.setClass(context,TeamLeaderActivity.class);
            view.startActivity(intent);
        }
    }

    @Override
    protected JSONObject doInBackground(String... teamId) {
        String teamNum=teamId[0].substring(5);
        JSONObject obj=null;
        try {
            obj=ApiUtil.getMyJsonObject(context,"/team/close/"+teamNum);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
