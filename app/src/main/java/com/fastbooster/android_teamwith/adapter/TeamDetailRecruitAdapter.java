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
            viewHolder.htvRecruitRole=(TextView)itemLayout.findViewById(R.id.htvRecruitRole);
            viewHolder.htvRecruitPeopleNum=(TextView)itemLayout.findViewById(R.id.htvRecruitPeopleNum);
            viewHolder.htvRecruitExplain=(TextView)itemLayout.findViewById(R.id.htvRecruitExplain);
            viewHolder.htvRecruitPreference=(TextView)itemLayout.findViewById(R.id.htvRecruitPreference);
            viewHolder.htvRecruitSkill=(TextView)itemLayout.findViewById(R.id.htvRecruitSkill);
            itemLayout.setTag(viewHolder);
        }
        else{
            viewHolder=(RecruitViewHolder)itemLayout.getTag();
        }
        viewHolder.htvRecruitRole.setText(data.get(i).getRoleId());
        viewHolder.htvRecruitPeopleNum.setText(data.get(i).getRecruitPeopleNum());
        viewHolder.htvRecruitExplain.setText(data.get(i).getRecruitExplain());
        viewHolder.htvRecruitPreference.setText(data.get(i).getRecruitPreference());
        viewHolder.htvRecruitSkill.setText("gg");

        return itemLayout;
    }
}
