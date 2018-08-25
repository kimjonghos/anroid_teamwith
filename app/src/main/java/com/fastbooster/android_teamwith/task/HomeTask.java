package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import com.fastbooster.android_teamwith.HomeActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.adapter.MemberAdapter;
import com.fastbooster.android_teamwith.adapter.PologPortfoliListAdapter;
import com.fastbooster.android_teamwith.adapter.TeamAdapter;
import com.fastbooster.android_teamwith.api.MemberSearchApi;

import com.fastbooster.android_teamwith.api.PortfolioApi;
import com.fastbooster.android_teamwith.api.TeamSearchApi;
import com.fastbooster.android_teamwith.model.MemberSearchVO;
import com.fastbooster.android_teamwith.model.MemberSimpleVO;
import com.fastbooster.android_teamwith.model.PortfolioSimpleVO;
import com.fastbooster.android_teamwith.model.TeamSimpleVO;
import com.fastbooster.android_teamwith.util.Criteria;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeTask extends AsyncTask<String,Void,Map<String,Object>> {
    private Context context;

    public HomeTask(Context context) {
        this.context=context;
    }
    @Override
    protected Map<String,Object> doInBackground(String... strings) {
        Map<String,Object> param=new HashMap<>();
        Criteria cri=new Criteria(1,4);
        try {
            List<TeamSimpleVO> team= TeamSearchApi.getTeam(context,cri,null,null,null,null,null);
            param.put("team",team);
            List<MemberSearchVO> member= MemberSearchApi.getBestMember(context,cri,null,null,null,null,null);
            param.put("member",member);
            List<PortfolioSimpleVO> portfolio= PortfolioApi.getPortfolioList(cri,null,null);
            param.put("portfolio",portfolio);


        } catch (Exception e) {
            e.printStackTrace();
        }
        List<MemberSimpleVO> member;
        return param;
    }





    //        List<PortfolioSimpleVO> portfolio;
//    List<MemberSimpleVO> member;
    @Override
    protected void onPostExecute(Map<String,Object> param) {

        HomeActivity view=(HomeActivity)context;


        try {
            List<TeamSimpleVO> team=(List<TeamSimpleVO>)param.get("team");
            TeamAdapter teamAdapter=new TeamAdapter(context,team);
            GridView gvTeam=(GridView)view.findViewById(R.id.k_gv_team);
            gvTeam.setAdapter(teamAdapter);

            List<MemberSearchVO> member=(List<MemberSearchVO>)param.get("member");
            MemberAdapter memberAdapter=new MemberAdapter(context,member,1);
            GridView gvMember=(GridView)view.findViewById(R.id.k_gv_member);
            gvMember.setAdapter(memberAdapter);

            List<PortfolioSimpleVO> portfolio=(List<PortfolioSimpleVO>)param.get("portfolio");
            PologPortfoliListAdapter portfolioAdapter=new PologPortfoliListAdapter(context,portfolio);
            GridView gvPortfolio=(GridView)view.findViewById(R.id.k_gv_portfolio);
            gvPortfolio.setAdapter(portfolioAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
