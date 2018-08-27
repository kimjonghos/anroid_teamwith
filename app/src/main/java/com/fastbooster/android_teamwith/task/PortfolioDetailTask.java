package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.PortfolioActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.adapter.PortfolioContentAdapter;
import com.fastbooster.android_teamwith.api.PortfolioApi;
import com.fastbooster.android_teamwith.model.PortfolioContentVO;
import com.fastbooster.android_teamwith.share.ApplicationShare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class PortfolioDetailTask extends AsyncTask<String, Void, JSONObject> {
    private final Context context;

    public PortfolioDetailTask(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(JSONObject portfolioSimpleVOS) {

        try {
            JSONObject ps = portfolioSimpleVOS.getJSONObject("portfolio");
            String r = ps.getString("portfolioId");

            PortfolioActivity view = (PortfolioActivity) context;
            TextView tvTitle = (TextView) view.findViewById(R.id.k_tv_title);
            TextView tvCategory = (TextView) view.findViewById(R.id.k_tv_category);
            TextView tvIntro = (TextView) view.findViewById(R.id.k_tv_intro);
            TextView tvPeopleNum = (TextView) view.findViewById(R.id.k_tv_peopleNum);
            TextView tvTeamName = (TextView) view.findViewById(R.id.k_tv_teamName);
            TextView tvStartDate = (TextView) view.findViewById(R.id.k_tv_startDate);
            TextView tvEndDate = (TextView) view.findViewById(R.id.k_tv_endDate);
            TextView tvRole = (TextView) view.findViewById(R.id.k_tv_role);
            TextView tvWork = (TextView) view.findViewById(R.id.k_tv_work);
            ImageView ivPortfolioPic = (ImageView) view.findViewById(R.id.k_ivPortfolioPic);
            tvTitle.setText(ps.getString("portfolioTitle"));
            tvCategory.setText((String) ApplicationShare.categoryList.get(ps.getString("projectCategoryId")));
            tvIntro.setText(ps.getString("portfolioIntro"));
            tvPeopleNum.setText(ps.getString("portfolioPeopleNum"));
            tvTeamName.setText(ps.getString("portfolioTeamName"));
            tvStartDate.setText(ps.getString("portfolioStartDate"));
            tvEndDate.setText(ps.getString("portfolioEndDate"));
            tvRole.setText(ps.getString("portfolioRole"));
            tvWork.setText(ps.getString("portfolioWork"));
            ivPortfolioPic.setTag(ps.get("portfolioPic"));
            ImageTask imgTask = new ImageTask(context);
            imgTask.execute(ivPortfolioPic);
            if (portfolioSimpleVOS.getJSONArray("portfolioContent") != null) {
                JSONArray pary = portfolioSimpleVOS.getJSONArray("portfolioContent");
                List<PortfolioContentVO> portfolioContenList = new ArrayList<>();
                for (int i = 0; i < pary.length(); i++) {
                    JSONObject j = pary.getJSONObject(i);
                    portfolioContenList.add(new PortfolioContentVO(j));
                }
                PortfolioContentAdapter pca = new PortfolioContentAdapter(context, portfolioContenList);
                GridView g = (GridView) view.findViewById(R.id.k_portfolioContent);
                g.setAdapter(pca);
            }
//            task(view,portfolioContenList);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected JSONObject doInBackground(String... strings) {

        try {
            Log.e("PortfolioDetailTask - onProgressUpdate call...", strings[0]);
            JSONObject o = PortfolioApi.getPortfolioDetail(strings[0]);
//            JSONObject ps=o.getJSONObject("portfolio");


            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


