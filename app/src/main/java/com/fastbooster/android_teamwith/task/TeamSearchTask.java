package com.fastbooster.android_teamwith.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.SearchActivity;
import com.fastbooster.android_teamwith.adapter.TeamAdapter;
import com.fastbooster.android_teamwith.api.TeamSearchApi;
import com.fastbooster.android_teamwith.model.TeamSimpleVO;
import com.fastbooster.android_teamwith.util.Criteria;

import java.util.List;

public class TeamSearchTask extends AsyncTask<Object, Void, List<TeamSimpleVO>> {

    static final String TAG="team data...";
    private final Context context;
    private ProgressDialog loading;

    public TeamSearchTask(Context context) {
        this.context = context;
        loading = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.setMessage("팀 정보를 불러오는 중입니다...");
        loading.show();
        super.onPreExecute();
    }

    @Override
    protected List<TeamSimpleVO> doInBackground(Object... condition) {

        //http url connection
        try {
            Log.v("cnt",condition.length+"");
            return TeamSearchApi.getTeam(context, (Criteria)condition[0], (List<String>)condition[1],
                    (List<String>)condition[2],(List<String>)condition[3],(List<String>)condition[4],
                    (String)condition[5]);
        } catch (Exception e) {
            e.printStackTrace();
            Log.v(TAG,"팀 서치 태스크 getteam 오류");
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<TeamSimpleVO> teamData) {
        loading.dismiss();

        TeamAdapter adapter = new TeamAdapter(context, teamData);
     //   Log.v(TAG,"member search task 53라인 어댑터 설정 후 데이터 사이즈"+","+memberData.size());
        if (context instanceof SearchActivity) {
            SearchActivity view = (SearchActivity) context;
            GridView result = view.findViewById(R.id.jresultView);
            result.setAdapter(adapter);
        }

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
