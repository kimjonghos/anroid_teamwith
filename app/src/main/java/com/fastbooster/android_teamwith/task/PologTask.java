package com.fastbooster.android_teamwith.task;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.fastbooster.android_teamwith.MyPologActivity;
import com.fastbooster.android_teamwith.PologActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.api.PologApi;
import com.fastbooster.android_teamwith.model.MemberPraiseCntVO;
import com.fastbooster.android_teamwith.model.MemberProjectCategoryVO;
import com.fastbooster.android_teamwith.model.MemberSkillVO;
import com.fastbooster.android_teamwith.model.MemberVO;
import com.fastbooster.android_teamwith.model.PologVO;
import com.fastbooster.android_teamwith.model.PraiseVO;
import com.fastbooster.android_teamwith.model.TendencyVO;
import com.fastbooster.android_teamwith.share.ApplicationShare;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PologTask extends AsyncTask<String, Void, JSONObject> {
    private final Context context;
    private final View profileView;
    LayoutInflater inflater;
    public static TextView[] prTv;

    public PologTask(Context context, View profileView) {
        super();
        this.context = context;
        this.profileView = profileView;
        inflater = LayoutInflater.from(context);

    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONObject o = null;
        try {

            o = PologApi.getPologInfo(strings[0]);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

        try {
            MemberVO member = new MemberVO(jsonObject.getJSONObject("member"));
            PologVO polog = new PologVO(jsonObject.getJSONObject("polog"));
            MemberProjectCategoryVO category = new MemberProjectCategoryVO(member.getMemberId(), jsonObject.getJSONArray("category"));
            List<MemberPraiseCntVO> memberPraiseList = new ArrayList<MemberPraiseCntVO>();
            PraiseVO praise = new PraiseVO(jsonObject.getJSONArray("praise"));
            MemberSkillVO memberSkill = new MemberSkillVO(jsonObject.getJSONObject("skills"));
            TendencyVO tendency = new TendencyVO(jsonObject.getJSONObject("tendency"));

            Activity view;
            if (context instanceof MyPologActivity) {
                view = (MyPologActivity) context;
            } else {
                view = (PologActivity) context;
            }

            TextView pologTitle = (TextView) view.findViewById(R.id.k_polog_title);
            TextView pologMemberName = (TextView) view.findViewById(R.id.k_polog_memberName);
            TextView pologRole = (TextView) view.findViewById(R.id.k_polog_role);
            TextView pologRegion1 = (TextView) view.findViewById(R.id.k_polog_region1);
            TextView pologRegion2 = (TextView) view.findViewById(R.id.k_polog_region2);
            ImageView memberPic = (ImageView) view.findViewById(R.id.k_polog_pic);
            pologTitle.setText(polog.getPologTitle());
            pologMemberName.setText(member.getMemberName());

            pologRole.setText((String) ApplicationShare.roleList.get(member.getRoleId()));
            pologRegion1.setText((String) ApplicationShare.regionList.get(member.getRegionId1()));
            pologRegion2.setText((String) ApplicationShare.regionList.get(member.getRegionId2()));
            memberPic.setTag(member.getMemberPic());
            MemberImageTask imgTask = new MemberImageTask(context);
            imgTask.execute(memberPic);

            TextView pologEmail = profileView.findViewById(R.id.k_polog_email);
            TextView pologIntro = profileView.findViewById(R.id.k_polog_Intro);
            TextView pologCategory = profileView.findViewById(R.id.k_polog_category);
            TextView pologSkill = profileView.findViewById(R.id.k_polog_skill);
            TextView pologPraise1 = profileView.findViewById(R.id.k_polog_praise1);
            TextView pologPraise2 = profileView.findViewById(R.id.k_polog_praise2);
            TextView pologPraise3 = profileView.findViewById(R.id.k_polog_praise3);
            TextView pologPraise4 = profileView.findViewById(R.id.k_polog_praise4);
            TextView pologPraise5 = profileView.findViewById(R.id.k_polog_praise5);
            prTv = new TextView[]{
                    pologPraise1, pologPraise2, pologPraise3, pologPraise4, pologPraise5
            };
            SeekBar tendency1 = profileView.findViewById(R.id.k_polog_tendency1);
            SeekBar tendency2 = profileView.findViewById(R.id.k_polog_tendency2);
            SeekBar tendency3 = profileView.findViewById(R.id.k_polog_tendency3);
            SeekBar tendency4 = profileView.findViewById(R.id.k_polog_tendency4);
            SeekBar tendency5 = profileView.findViewById(R.id.k_polog_tendency5);
            pologEmail.setText("Email " + member.getMemberEmail());
            pologIntro.setText("Intro " + member.getMemberIntro());

            String[] categoryId = category.getProjectCategoryId();
            pologCategory.setText("");
            for (int i = 0; i < categoryId.length; i++) {
                if (i == 0) {
                    pologCategory.append(categoryId[i]);
                } else {
                    pologCategory.append("\n" + categoryId[i]);
                }

            }
            if (memberSkill != null) {
                String[] skillName = memberSkill.getSkillName();
                if (skillName != null) {
                    pologSkill.setText("");
                    for (int i = 0; i < skillName.length; i++) {
                        if (i == 0) {
                            pologSkill.append(skillName[i]);
                        } else {
                            pologSkill.append("\n" + skillName[i]);
                        }
                    }
                }
            }
            String[] praiseCnt = praise.getPraiseCnt();
            String[] praiseName = praise.getPraiseName();
            TextView[] tvPraise = {pologPraise1, pologPraise2, pologPraise3, pologPraise4, pologPraise5};
            for (int i = 0; i < praiseCnt.length; i++) {
                tvPraise[i].setText(praiseCnt[i] + "회");
            }
            tendency1.setProgress(Integer.parseInt(tendency.getTendency1()));
            tendency1.willNotDraw();
            tendency2.setProgress(Integer.parseInt(tendency.getTendency2()));
            tendency3.setProgress(Integer.parseInt(tendency.getTendency3()));
            tendency4.setProgress(Integer.parseInt(tendency.getTendency4()));
            tendency5.setProgress(Integer.parseInt(tendency.getTendency5()));


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
