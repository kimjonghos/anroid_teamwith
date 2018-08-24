package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fastbooster.android_teamwith.MyPologActivity;
import com.fastbooster.android_teamwith.PortfolioActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.adapter.PologPortfoliListAdapter;
import com.fastbooster.android_teamwith.api.PortfolioApi;
import com.fastbooster.android_teamwith.model.PortfolioSimpleVO;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class PortfolioDetailTask  extends AsyncTask<String,Void,JSONObject> {
    private final Context context;
    public PortfolioDetailTask(Context context) {
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
            JSONObject o=PortfolioApi.getPortfolioDetail(strings[0]);
            JSONObject ps=o.getJSONObject("portfolio");

            return ps;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

class ImageViewTask extends AsyncTask<ImageView, Void, Bitmap> {

    private final Context context;
    private ImageView image;

    public ImageViewTask(Context context) {
        this.context = context;

    }

    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {
        image = imageViews[0];
        String urlStr = "http://192.168.30.64:8089" + (String) image.getTag();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setDoInput(true);
            conn.connect();

            return BitmapFactory.decodeStream(conn.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) {
            image.setImageBitmap(bitmap);
        }

    }
}