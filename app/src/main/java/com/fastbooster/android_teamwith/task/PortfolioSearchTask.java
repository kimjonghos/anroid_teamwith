package com.fastbooster.android_teamwith.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.SearchActivity;
import com.fastbooster.android_teamwith.adapter.PologPortfoliListAdapter;
import com.fastbooster.android_teamwith.api.ApiUtil;
import com.fastbooster.android_teamwith.model.PortfolioSimpleVO;
import com.fastbooster.android_teamwith.util.Criteria;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PortfolioSearchTask extends AsyncTask<Object, Void, List<PortfolioSimpleVO>> {

    static final String TAG = "team data...";
    private final Context context;
    private ProgressDialog loading;

    public PortfolioSearchTask(Context context) {
        this.context = context;
        loading = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.setMessage("포트폴리오 정보를 불러오는 중입니다...");
        loading.show();
        super.onPreExecute();
    }

    @Override
    protected List<PortfolioSimpleVO> doInBackground(Object... condition) {

        //http url connection
        try {
            Log.v("cnt", condition.length + "");
            Uri.Builder params = new Uri.Builder();
            String keyword = (String) condition[2];
            List<String> project = (List<String>) condition[1];
            Criteria cri = (Criteria) condition[0];
            if ((keyword == null || keyword.trim().equals("")) && project == null) {
                params.appendPath("recent");
            } else {
                if (keyword != null) {
                    params.appendQueryParameter("keyword", keyword);
                }
                if (project != null) {
                    for (String p : project) {
                        params.appendQueryParameter("project", p);
                    }
                }
            }
            if (cri != null) {
                params.appendQueryParameter("page", cri.getPage() + "");
                params.appendQueryParameter("perPageNum", cri.getPerPageNum() + "");
            }
            List<PortfolioSimpleVO> list = new ArrayList<>();
            JSONObject obj = ApiUtil.getJsonObject("/portfolioSearch" + params.toString());
            JSONArray array = obj.getJSONArray("portfolioList");
            PortfolioSimpleVO portfolioSimpleVO = null;
            for (int i = 0; i < array.length(); i++) {
                list.add(new PortfolioSimpleVO(array.getJSONObject(i)));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            Log.v(TAG, "포트폴리오 서치 태스크 getPortfolio 오류");
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<PortfolioSimpleVO> pfData) {
        loading.dismiss();

        PologPortfoliListAdapter adapter = new PologPortfoliListAdapter(context, pfData);
        if (context instanceof SearchActivity) {
            SearchActivity view = (SearchActivity) context;
            GridView result = view.findViewById(R.id.resultView);
            result.setAdapter(adapter);
        }

    }


}
