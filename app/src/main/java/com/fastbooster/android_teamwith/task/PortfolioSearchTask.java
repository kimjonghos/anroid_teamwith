package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.PortfolioActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.api.PortfolioApi;

import org.json.JSONObject;

public class PortfolioSearchTask extends AsyncTask<String,Void,JSONObject> {
private final Context context;
public PortfolioSearchTask(Context context) {
        super();
        this.context=context;
        }

@Override
protected void onPreExecute() {
        super.onPreExecute();
        }

@Override
protected void onPostExecute(JSONObject portfolioSimpleVOS) {

    try {
        String r = portfolioSimpleVOS.getString("portfolioId");

        PortfolioActivity view=(PortfolioActivity) context;
        TextView tvTitle=(TextView)view.findViewById(R.id.k_tv_title);
        TextView tvCategory=(TextView)view.findViewById(R.id.k_tv_category);
        TextView tvIntro=(TextView)view.findViewById(R.id.k_tv_intro);
        TextView tvPeopleNum=(TextView)view.findViewById(R.id.k_tv_peopleNum);
        TextView tvTeamName=(TextView)view.findViewById(R.id.k_tv_teamName);
        TextView tvStartDate=(TextView)view.findViewById(R.id.k_tv_startDate);
        TextView tvEndDate=(TextView)view.findViewById(R.id.k_tv_endDate);
        TextView tvRole=(TextView)view.findViewById(R.id.k_tv_role);
        TextView tvWork=(TextView)view.findViewById(R.id.k_tv_work);
        ImageView ivPortfolioPic =(ImageView)view.findViewById(R.id.k_ivPortfolioPic);
        tvTitle.setText(portfolioSimpleVOS.getString("portfolioTitle"));
        tvCategory.setText(portfolioSimpleVOS.getString("projectCategoryId"));
        tvIntro.setText(portfolioSimpleVOS.getString("portfolioIntro"));
        tvPeopleNum.setText(portfolioSimpleVOS.getString("portfolioPeopleNum"));
        tvTeamName.setText(portfolioSimpleVOS.getString("portfolioTeamName"));
        tvStartDate.setText(portfolioSimpleVOS.getString("portfolioStartDate"));
        tvEndDate.setText(portfolioSimpleVOS.getString("portfolioEndDate"));
        tvRole.setText(portfolioSimpleVOS.getString("portfolioRole"));
        tvWork.setText(portfolioSimpleVOS.getString("portfolioWork"));
        ivPortfolioPic.setTag(portfolioSimpleVOS.get("portfolioPic"));
        ImageViewTask imgTask=new ImageViewTask(context);
        imgTask.execute(ivPortfolioPic);


    }
    catch(Exception e){
      e.printStackTrace();
    }
    }
    @Override
    protected JSONObject doInBackground(String... strings) {
        try{
            Log.e("PortfolioDetailTask - onProgressUpdate call...",strings[0]);
            JSONObject o= PortfolioApi.getPortfolioDetail(strings[0]);
            JSONObject ps=o.getJSONObject("portfolio");

            return ps;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
