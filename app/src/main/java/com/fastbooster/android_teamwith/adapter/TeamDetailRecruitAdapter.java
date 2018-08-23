package com.fastbooster.android_teamwith.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.RecruitVO;
import com.fastbooster.android_teamwith.viewholder.RecruitViewHolder;

import java.util.List;

public class TeamDetailRecruitAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    List<RecruitVO> data;

    public TeamDetailRecruitAdapter(Context context,List<RecruitVO> data){
        this.context=context;
        this.data=data;
        layoutInflater=LayoutInflater.from(context);
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
        View itemLayout=view;
        RecruitViewHolder viewHolder=null;
        if(itemLayout==null){
            itemLayout=layoutInflater.inflate(R.layout.recruit_layout,null);
            viewHolder=new RecruitViewHolder();
            viewHolder.hktvRecruitRole=(TextView)itemLayout.findViewById(R.id.hktvRecruitRole);
            viewHolder.hktvRecruitPeopleNum=(TextView)itemLayout.findViewById(R.id.hktvRecruitPeopleNum);
            viewHolder.hktvRecruitExplain=(TextView)itemLayout.findViewById(R.id.hktvRecruitExplain);
            viewHolder.hktvRecruitPreference=(TextView)itemLayout.findViewById(R.id.hktvRecruitPreference);
            viewHolder.hktvRecruitSkill=(TextView)itemLayout.findViewById(R.id.hktvRequireSkill);
            itemLayout.setTag(viewHolder);
        }
        else{
            viewHolder=(RecruitViewHolder)itemLayout.getTag();
        }
        viewHolder.hktvRecruitRole.setText(data.get(i).getRoleId());
        viewHolder.hktvRecruitPeopleNum.setText(data.get(i).getRecruitPeopleNum());
        viewHolder.hktvRecruitExplain.setText(data.get(i).getRecruitExplain());
        viewHolder.hktvRecruitPreference.setText(data.get(i).getRecruitPreference());
        viewHolder.hktvRecruitSkill.setText("gg");

        return itemLayout;
    }
}
