package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.fastbooster.android_teamwith.api.PortfolioApi;
import com.fastbooster.android_teamwith.model.PortfolioSimpleVO;

import org.json.JSONObject;

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
        super.onPostExecute(portfolioSimpleVOS);
        try {
            String r = portfolioSimpleVOS.getString("portfolioId");
            Toast.makeText(context,r,Toast.LENGTH_SHORT).show();
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
            return o;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
