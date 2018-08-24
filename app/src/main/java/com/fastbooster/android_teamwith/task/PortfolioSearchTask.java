package com.fastbooster.android_teamwith.task;

import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.PologActivity;
import com.fastbooster.android_teamwith.PortfolioActivity;
import com.fastbooster.android_teamwith.PortfolioFragment;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.adapter.PologPortfoliListAdapter;
import com.fastbooster.android_teamwith.api.PortfolioApi;
import com.fastbooster.android_teamwith.model.PortfolioSimpleVO;

import org.json.JSONObject;

import java.util.List;

public class PortfolioSearchTask extends AsyncTask<String,Void,List<PortfolioSimpleVO>> {
private final Context context;
private final FragmentManager fm;
public PortfolioSearchTask(Context context,FragmentManager fm) {
        super();
        this.context=context;
        this.fm=fm;
        }

@Override
protected void onPreExecute() {
        super.onPreExecute();
        }

@Override
protected void onPostExecute(List<PortfolioSimpleVO> portfolioSimpleVOS) {

    try {

        PologPortfoliListAdapter ppl = new PologPortfoliListAdapter(context, portfolioSimpleVOS);
        PortfolioFragment d=new PortfolioFragment();
        ListView l=(ListView)d.getActivity().findViewById(R.id.k_lv_polport);
        l.setAdapter(ppl);
//        ListView listview=(ListView)view.findViewById(R.id.k_lv_polport);


//                Fragment fragment=fm.findFragmentById(R.id.kframe);

        FragmentTransaction tr=fm.beginTransaction();

        tr.replace(R.id.k_fl_portfolioList,d);
        //앞 요소에 뒤에걸
//                    tr.add(R.id.kframe,pf,"portfolio");
        tr.commit();

//        ImageViewTask imgTask=new ImageViewTask(context);
//        imgTask.execute(ivPortfolioPic);


    }
    catch(Exception e){
      e.printStackTrace();
    }
    }
    @Override
    protected List<PortfolioSimpleVO> doInBackground(String... strings) {
        try{
            Log.e("PortfolioSearchTask - onProgressUpdate call...",strings[0]);
            List<PortfolioSimpleVO> o= PortfolioApi.getPortfolioList();
            return o;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
