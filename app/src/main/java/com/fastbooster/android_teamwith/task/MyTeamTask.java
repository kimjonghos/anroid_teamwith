package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import com.fastbooster.android_teamwith.MyTeamActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.adapter.TeamGridViewAdapter;
import com.fastbooster.android_teamwith.api.ApiUtil;
import com.fastbooster.android_teamwith.model.TeamSimpleVO;
import com.fastbooster.android_teamwith.util.Criteria;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyTeamTask extends AsyncTask<Void, Void, List<TeamSimpleVO>> {
    private final Context context;

    public MyTeamTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(List<TeamSimpleVO> data) {

        if (context instanceof MyTeamActivity) {
            MyTeamActivity myTeamActivity = (MyTeamActivity) context;
            GridView myTeamGridView = (GridView) myTeamActivity.findViewById(R.id.myTeamGridView);

            TeamGridViewAdapter adapter = new TeamGridViewAdapter(context, data);
            myTeamGridView.setAdapter(adapter);
        }


    }

    @Override
    protected List<TeamSimpleVO> doInBackground(Void... voids) {
        JSONObject json = null;
        try {
            Criteria cri = new Criteria(1, 100);
            json = ApiUtil.getJsonObject("/team/myTeam?page=" + cri.getPage() + "&perPageNum="
                    + cri.getPerPageNum());
            List<TeamSimpleVO> result = new ArrayList<TeamSimpleVO>();

            JSONArray myTeamList = json.getJSONArray("myTeamList");
            for (int i = 0; i < myTeamList.length(); i++) {
                TeamSimpleVO teamSimpleVO = new TeamSimpleVO(myTeamList.getJSONObject(i));
                result.add(teamSimpleVO);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
