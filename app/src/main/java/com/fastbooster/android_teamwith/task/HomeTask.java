package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;

import com.fastbooster.android_teamwith.api.TeamSearchApi;
import com.fastbooster.android_teamwith.model.MemberSimpleVO;
import com.fastbooster.android_teamwith.model.PortfolioSimpleVO;
import com.fastbooster.android_teamwith.model.TeamSimpleVO;
import com.fastbooster.android_teamwith.util.Criteria;


import org.json.JSONObject;

import java.util.List;

public class HomeTask extends AsyncTask<String,Void,JSONObject> {
    private Context context;

    public HomeTask(Context context){
        this.context=context;
    }
    @Override
    protected JSONObject doInBackground(String... strings) {
        return null;
    }
        Criteria cri=new Criteria(1,4);
        List<TeamSimpleVO> tema= TeamSearchApi.getTeam(context,cri,null,null,null,null,null);
        List<PortfolioSimpleVO> portfolio;
        List<MemberSimpleVO> member;
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }
}
