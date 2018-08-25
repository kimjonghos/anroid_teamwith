package com.fastbooster.android_teamwith.task;

import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fastbooster.android_teamwith.PologActivity;
import com.fastbooster.android_teamwith.PortfolioActivity;
import com.fastbooster.android_teamwith.PortfolioFragment;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.adapter.PologPortfoliListAdapter;
import com.fastbooster.android_teamwith.api.PortfolioApi;
import com.fastbooster.android_teamwith.model.PortfolioSimpleVO;

import org.json.JSONObject;

import java.util.List;

public class MyPortfolioTask extends AsyncTask<String,Void,List<PortfolioSimpleVO>> {
private final Context context;
private final GridView lv;
private List<PortfolioSimpleVO> list;
//private final FragmentManager fm;
public MyPortfolioTask(Context context, GridView lv) {
        super();
        this.context=context;
            this.lv=lv;
//        this.fm=fm;
        }

@Override
protected void onPreExecute() {
        super.onPreExecute();
        }

@Override
protected void onPostExecute(List<PortfolioSimpleVO> portfolioSimpleVOS) {

    try {

        PologPortfoliListAdapter ppl = new PologPortfoliListAdapter(context, portfolioSimpleVOS);
        lv.setAdapter(ppl);

    }
    catch(Exception e){
      e.printStackTrace();
    }
    }
    @Override
    protected List<PortfolioSimpleVO> doInBackground(String... strings) {
        try{

            List<PortfolioSimpleVO> o= PortfolioApi.getMemberPortfolioList(strings[0]);

            return o;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<PortfolioSimpleVO> getPoerfolioSimpleList(){
    return this.list;
    }
}
