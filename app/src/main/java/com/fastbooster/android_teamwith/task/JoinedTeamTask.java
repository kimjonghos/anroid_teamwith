package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import com.fastbooster.android_teamwith.JoinedTeamActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.adapter.TeamGridViewAdapter;
import com.fastbooster.android_teamwith.api.JoinedTeamApi;
import com.fastbooster.android_teamwith.model.TeamSimpleVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JoinedTeamTask extends AsyncTask<Void,Void,JSONObject> {
    private final Context context;
    public JoinedTeamTask(Context context){
        this.context=context;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        List<TeamSimpleVO> data=new ArrayList<TeamSimpleVO>();
        try{
            JSONArray joinedTeamList=json.getJSONArray("joinedTeamList");
            for(int i=0;i<joinedTeamList.length();i++){
                TeamSimpleVO teamSimpleVO=new TeamSimpleVO(joinedTeamList.getJSONObject(i));
                data.add(teamSimpleVO);
            }

            if(context instanceof JoinedTeamActivity){
                JoinedTeamActivity joinedTeamActivity=(JoinedTeamActivity) context;
                GridView joinedTeamGridView=(GridView) joinedTeamActivity.findViewById(R.id.hkJoinedTeamGridView);

                TeamGridViewAdapter adapter=new TeamGridViewAdapter(context,data);
                joinedTeamGridView.setAdapter(adapter);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        super.onPostExecute(json);

    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        JSONObject json=null;
        try {
            json = JoinedTeamApi.getJoinedTeam();
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }
}
