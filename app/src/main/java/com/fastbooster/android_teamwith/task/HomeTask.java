package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import com.fastbooster.android_teamwith.HomeActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.adapter.MemberAdapter;
import com.fastbooster.android_teamwith.adapter.PologPortfoliListAdapter;
import com.fastbooster.android_teamwith.adapter.TeamAdapter;
import com.fastbooster.android_teamwith.adapter.TeamGridViewAdapter;
import com.fastbooster.android_teamwith.api.ApiUtil;
import com.fastbooster.android_teamwith.model.MemberSearchVO;
import com.fastbooster.android_teamwith.model.PortfolioSimpleVO;
import com.fastbooster.android_teamwith.model.TeamSimpleVO;
import com.fastbooster.android_teamwith.util.Criteria;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeTask extends AsyncTask<String, Void, Map<String, Object>> {
    private Context context;
    private static final String MEMBER_URL_STR = "/member/bestMember";
    private static final String PORTFOLIO_URL_STR = "/portfolioSearch/recent";
    private static final String TEAM_URL_STR = "/teamSearch";

    public HomeTask(Context context) {
        this.context = context;
    }

    @Override
    protected Map<String, Object> doInBackground(String... strings) {
        Map<String, Object> param = new HashMap<>();
        Criteria cri = new Criteria(1, 4);
        try {
            Uri.Builder params = new Uri.Builder();

            params.appendQueryParameter("page", cri.getPage() + "");
            params.appendQueryParameter("perPageNum", cri.getPerPageNum() + "");

            JSONArray array = ApiUtil.getJsonArray(MEMBER_URL_STR + params.toString());
            List<MemberSearchVO> member = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                member.add(new MemberSearchVO(array.getJSONObject(i)));
            }
            param.put("member", member);

            JSONArray array2 = ApiUtil.getJsonArray(TEAM_URL_STR + params.toString());
            List<TeamSimpleVO> team = new ArrayList<>();
            for (int i = 0; i < array2.length(); i++) {
                team.add(new TeamSimpleVO(array2.getJSONObject(i)));
            }
            param.put("team", team);

            List<PortfolioSimpleVO> list = new ArrayList<>();
            JSONObject obj = ApiUtil.getJsonObject(PORTFOLIO_URL_STR + params.toString());
            JSONArray array3 = obj.getJSONArray("portfolioList");
            List<PortfolioSimpleVO> portfolio = new ArrayList<>();
            for (int i = 0; i < array3.length(); i++) {
                portfolio.add(new PortfolioSimpleVO(array3.getJSONObject(i)));
            }
            param.put("portfolio", portfolio);
//            List<TeamSimpleVO> team= TeamSearchApi.getTeam(context,cri,null,null,null,null,null);
//            param.put("team",team);
//            List<MemberSearchVO> member= MemberSearchApi.getBestMember(context,cri,null,null,null,null,null);
//            param.put("member",member);
//            List<PortfolioSimpleVO> portfolio= PortfolioApi.getPortfolioList(cri,null,null);
//            param.put("portfolio",portfolio);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return param;
    }


    //        List<PortfolioSimpleVO> portfolio;
//    List<MemberSimpleVO> member;
    @Override
    protected void onPostExecute(Map<String, Object> param) {
        HomeActivity view = (HomeActivity) context;
        try {
            List<TeamSimpleVO> team = (List<TeamSimpleVO>) param.get("team");
            TeamGridViewAdapter teamAdapter = new TeamGridViewAdapter(context, team);
            GridView gvTeam = (GridView) view.findViewById(R.id.k_gv_team);
            gvTeam.setAdapter(teamAdapter);

            List<MemberSearchVO> member = (List<MemberSearchVO>) param.get("member");
            MemberAdapter memberAdapter = new MemberAdapter(context, member, 1);
            GridView gvMember = (GridView) view.findViewById(R.id.k_gv_member);
            gvMember.setAdapter(memberAdapter);

            List<PortfolioSimpleVO> portfolio = (List<PortfolioSimpleVO>) param.get("portfolio");
            PologPortfoliListAdapter portfolioAdapter = new PologPortfoliListAdapter(context, portfolio);
            GridView gvPortfolio = (GridView) view.findViewById(R.id.k_gv_portfolio);
            gvPortfolio.setAdapter(portfolioAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
