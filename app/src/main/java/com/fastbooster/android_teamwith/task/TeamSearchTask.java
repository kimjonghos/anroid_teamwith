package com.fastbooster.android_teamwith.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.SearchActivity;
import com.fastbooster.android_teamwith.adapter.TeamAdapter;
import com.fastbooster.android_teamwith.adapter.TeamGridViewAdapter;
import com.fastbooster.android_teamwith.api.ApiUtil;
import com.fastbooster.android_teamwith.model.TeamSimpleVO;
import com.fastbooster.android_teamwith.util.Criteria;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeamSearchTask extends AsyncTask<Object, Void, List<TeamSimpleVO>> {
    public static String URL_STR = "/teamSearch";
    public String query;
    static final String TAG = "team data...";
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
        query = makeQuery((Criteria) condition[0], (List<String>) condition[1],
                (List<String>) condition[2], (List<String>) condition[3], (List<String>) condition[4],
                (String) condition[5]);
        //http url connection
        try {
            JSONArray array = ApiUtil.getJsonArray(URL_STR + query);

            //return
            List<TeamSimpleVO> result = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                result.add(new TeamSimpleVO(obj));
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            Log.v(TAG, "팀 서치 태스크 getteam 오류");
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<TeamSimpleVO> teamData) {
        loading.dismiss();

        TeamGridViewAdapter adapter = new TeamGridViewAdapter(context, teamData);
        if (context instanceof SearchActivity) {
            SearchActivity view = (SearchActivity) context;
            GridView result = view.findViewById(R.id.resultView);
            result.setAdapter(adapter);
        }

    }

    public static String makeQuery(Criteria cri, List<String> region,
                                   List<String> project, List<String> role, List<String> skill,
                                   String keyword) {

        Uri.Builder params = new Uri.Builder();
        if (keyword == null && region == null && project == null && role == null && skill == null) {
            params.appendPath("recent");
        } else {
            if (keyword != null) {
                params.appendQueryParameter("keyword", keyword);
            }
            if (region != null) {
                for (String r : region) {
                    params.appendQueryParameter("region", r);
                }
            }
            if (project != null) {
                for (String p : project) {
                    params.appendQueryParameter("project", p);
                }
            }
            if (role != null) {
                for (String r : role) {
                    params.appendQueryParameter("role", r);
                }
            }
            if (skill != null) {
                for (String s : skill) {
                    params.appendQueryParameter("skill", s);
                }
            }
        }

        if (cri != null) {
            params.appendQueryParameter("page", cri.getPage() + "");
            params.appendQueryParameter("perPageNum", cri.getPerPageNum() + "");
        }

        return params.toString();

    }
}
