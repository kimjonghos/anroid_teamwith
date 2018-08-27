package com.fastbooster.android_teamwith.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fastbooster.android_teamwith.PologActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.TeamActivity;
import com.fastbooster.android_teamwith.model.MemberSearchVO;
import com.fastbooster.android_teamwith.share.ApplicationShare;
import com.fastbooster.android_teamwith.task.ImageTask;
import com.fastbooster.android_teamwith.task.MemberImageTask;
import com.fastbooster.android_teamwith.viewholder.MemberViewHolder;

import java.util.List;

public class MemberAdapter extends BaseAdapter {
    static final String TAG = "member data...";

    Context context;
    List<MemberSearchVO> data;
    LayoutInflater layoutInflater;
    int flag;

    public MemberAdapter(Context context, List<MemberSearchVO> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);


    }

    public MemberAdapter(Context context, List<MemberSearchVO> data, int flag) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
        this.flag = flag;

    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MemberViewHolder vh;
        final int fi = i;
        if (view != null) {
            vh = (MemberViewHolder) view.getTag();
        } else {
            view = layoutInflater.inflate(R.layout.member_info_layout, null);
            vh = new MemberViewHolder();
            vh.memberInfoLayout = view.findViewById(R.id.memberInfoLayout);
            vh.memberPic = view.findViewById(R.id.k_iv_memberPic);
            vh.memberName = view.findViewById(R.id.k_tv_memberName);
            vh.memberRole = view.findViewById(R.id.k_tv_memberRole);
            vh.praiseCnt = view.findViewById(R.id.k_tv_praiseCnt);
            view.setTag(vh);
        }

        if (flag != 0) {
            vh.memberRole.setText((String) ApplicationShare.roleList.get(data.get(i).getRoleId()));
            vh.praiseCnt.setText("칭찬 " + data.get(i).getTotalPraiseCnt() + "회");
        } else {
            //일반 회원 정보
            vh.memberRole.setTextColor(context.getColor(R.color.colorAccent));
            vh.memberRole.setText((String) ApplicationShare.roleList.get(data.get(i).getRoleId()));

            String region1 = (String) ApplicationShare.regionList.get(data.get(i).getRegionId1());
            String region2 = (String) ApplicationShare.regionList.get(data.get(i).getRegionId2());
            String region;
            if (region1 != null && region2 != null) {
                region = region1 + "," + region2;
            } else {
                region1 = "";
                region2 = "";
                region = region1 + region2;
            }
            vh.praiseCnt.setTextColor(context.getColor(R.color.colorPrimaryDark));
            vh.praiseCnt.setText(region);


        }
        vh.memberInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PologActivity.class);
                intent.putExtra("memberId", data.get(fi).getMemberId());
                context.startActivity(intent);
            }
        });
        vh.memberPic.setTag(data.get(i).getMemberPic());
        MemberImageTask imgTask = new MemberImageTask(context);
        imgTask.execute(vh.memberPic);

        vh.memberName.setText(data.get(i).getMemberName()+"님");


        return view;
    }


}