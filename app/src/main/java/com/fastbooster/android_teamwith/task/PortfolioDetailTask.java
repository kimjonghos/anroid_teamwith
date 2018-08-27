package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.PortfolioActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.adapter.PortfolioContentAdapter;
import com.fastbooster.android_teamwith.api.PortfolioApi;
import com.fastbooster.android_teamwith.model.MemberVO;
import com.fastbooster.android_teamwith.model.PortfolioContentVO;
import com.fastbooster.android_teamwith.share.ApplicationShare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
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
            MemberVO member = new MemberVO(portfolioSimpleVOS.getJSONObject("member"));
            PortfolioActivity view = (PortfolioActivity) context;
            TextView tvTitle = view.findViewById(R.id.k_tv_title);
            TextView tvTitle2 = view.findViewById(R.id.k_tv_title2);
            TextView tvCategory = view.findViewById(R.id.k_tv_category);
            TextView tvIntro = view.findViewById(R.id.k_tv_intro);
            TextView tvPeopleNum = view.findViewById(R.id.k_tv_peopleNum);
            TextView tvTeamName = view.findViewById(R.id.k_tv_teamName);
            TextView tvStartDate = view.findViewById(R.id.k_tv_startDate);
            TextView tvEndDate = view.findViewById(R.id.k_tv_endDate);
            TextView tvRole = view.findViewById(R.id.k_tv_role);
            TextView tvWork = view.findViewById(R.id.k_tv_work);
            ImageView ivPortfolioPic = view.findViewById(R.id.k_ivPortfolioPic);
            //멤버
            TextView tvmemberName = view.findViewById(R.id.k_member_name);
            TextView tvmemberRole = view.findViewById(R.id.k_member_role);
            ImageView ivMemberPic = view.findViewById(R.id.k_member_pic);


            tvTitle.setText(ps.getString("portfolioTitle"));
            tvTitle2.setText(ps.getString("portfolioTitle"));
            tvCategory.setText(ApplicationShare.categoryList.get(ps.getString("projectCategoryId")));
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

            LinearLayout portfolioContent = ((PortfolioActivity) context).findViewById(R.id.k_portfolio_content);

            if (portfolioSimpleVOS.getJSONArray("portfolioContent") != null) {
                JSONArray pary = portfolioSimpleVOS.getJSONArray("portfolioContent");
                for (int i = 0; i < pary.length(); i++) {
                    JSONObject j = pary.getJSONObject(i);
                    PortfolioContentVO pc = new PortfolioContentVO(j);

                    //컨텐츠
                    // 이미지 추가
                    if (pc.getPortfolioContentName().equals("image")) {
                        // 이미지
                        ImageView iv = new ImageView(context);
                        iv.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, 750));
                        iv.setForegroundGravity(2);

                        iv.setScaleType(ImageView.ScaleType.FIT_XY);
                        iv.setMaxWidth(1000);
                        iv.setTag(pc.getPortfolioContentValue());
                        ImageTask imageViewTask = new ImageTask(context);
                        imageViewTask.execute(iv);
                        portfolioContent.addView(iv);

                        // 텍스트
                        TextView tv = new TextView(context);
                        tv.setPadding(0, 40, 0, 200);
                        tv.setText(pc.getPortfolioContentIntro());
                        tv.setTextSize(16);
                        tv.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/nanumsquareroundb.ttf");
                        tv.setTypeface(typeface);
                        portfolioContent.addView(tv);

                    // pdf 추가
                    } else if (pc.getPortfolioContentName().equals("ppt")) {
                        // 버튼
                        final String path = pc.getPortfolioContentValue();
                        Button btn = new Button(context);
                        btn.setText("PDF 보기");
                        btn.setTextColor(context.getResources().getColor(R.color.colorAccent));
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
                        params.gravity = Gravity.CENTER;
                        btn.setLayoutParams(params);

                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setAction(android.content.Intent.ACTION_VIEW);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.setDataAndType(Uri.parse("http://192.168.30.64:8089" + path), "application/pdf");
                                context.startActivity(intent);
                            }
                        });
                        btn.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button));
                        btn.setPadding(50,30,50,30);
                        portfolioContent.addView(btn);

                        // 텍스트
                        TextView tv = new TextView(context);
                        tv.setPadding(0, 40, 0, 200);
                        if (!pc.getPortfolioContentIntro().equals("null")) {
                            tv.setText(pc.getPortfolioContentIntro());
                        } else {
                            tv.setText("");
                        }
                        tv.setPadding(0, 40, 0, 200);
                        tv.setTextSize(16);
                        tv.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/nanumsquareroundb.ttf");
                        tv.setTypeface(typeface);
                        portfolioContent.addView(tv);

                        // 유튜브 추가
                    } else if (pc.getPortfolioContentName().equals("media")) {
                        // 동영상
                        WebView web = new WebView(context);
                        web.getSettings().setJavaScriptEnabled(true);
                        web.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT, 750));
                        web.loadUrl(pc.getPortfolioContentValue());
                        portfolioContent.addView(web);

                        // 텍스트
                        TextView tv = new TextView(context);
                        tv.setPadding(0, 40, 0, 200);
                        if (!pc.getPortfolioContentIntro().equals("null")) {
                            tv.setText(pc.getPortfolioContentIntro());
                        } else {
                            tv.setText(" ");
                        }
                        tv.setTextSize(16);
                        tv.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/nanumsquareroundb.ttf");
                        tv.setTypeface(typeface);
                        portfolioContent.addView(tv);
                    }


                }
            }

            //PortfolioContentAdapter pca = new PortfolioContentAdapter(context, portfolioContenList);
            //ListView g = (ListView) view.findViewById(R.id.k_portfolio_content);
            //g.setAdapter(pca);


            tvmemberName.setText(member.getMemberName());
            tvmemberRole.setText(ApplicationShare.roleList.get(member.getRoleId()));
            ivMemberPic.setTag(member.getMemberPic());
            MemberImageTask mit = new MemberImageTask(context);
            mit.execute(ivMemberPic);


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
