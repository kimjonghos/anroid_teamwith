package com.fastbooster.android_teamwith.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.SearchActivity;
import com.fastbooster.android_teamwith.adapter.PologPortfoliListAdapter;
import com.fastbooster.android_teamwith.api.PortfolioApi;
import com.fastbooster.android_teamwith.model.PortfolioSimpleVO;
import com.fastbooster.android_teamwith.util.Criteria;

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
            return PortfolioApi.getPortfolioList((Criteria) condition[0], (List<String>) condition[1],
                    (String) condition[2]);
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
