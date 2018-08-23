package com.fastbooster.android_teamwith.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.TeamSimpleVO;
import com.fastbooster.android_teamwith.viewholder.TeamLayoutViewHolder;

import java.util.List;

public class MyTeamAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<TeamSimpleVO> data;

    public MyTeamAdapter(Context context, List<TeamSimpleVO> data){
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
        TeamLayoutViewHolder viewHolder=null;
        if(itemLayout==null){
            itemLayout=layoutInflater.inflate(R.layout.team_info_layout,null);
            viewHolder=new TeamLayoutViewHolder();
            viewHolder.hkTeamLayoutProjectName=(TextView)itemLayout.findViewById(R.id.hkTeamLayoutProjectName);
            viewHolder.hktvTeamLayoutTeamName=(TextView)itemLayout.findViewById(R.id.hktvTeamLayoutTeamName);
            viewHolder.hktvTeamLayoutProejctCategory=(TextView)itemLayout.findViewById(R.id.hktvTeamLayoutProejctCategory);
            viewHolder.hkivTeamLayoutTeamPic=(ImageView) itemLayout.findViewById(R.id.hkivTeamLayoutTeamPic);
            itemLayout.setTag(viewHolder);
        }
        else{
            viewHolder=(TeamLayoutViewHolder)itemLayout.getTag();
        }
        viewHolder.hkTeamLayoutProjectName.setText(data.get(i).getTeamProjectName());
        viewHolder.hktvTeamLayoutTeamName.setText(data.get(i).getTeamName());
        viewHolder.hktvTeamLayoutProejctCategory.setText(data.get(i).getProjectCategoryId());
        //viewHolder.hkivTeamLayoutTeamPic.setText(data.get(i).getRecruitPreference()); 이미지 태스크

        return itemLayout;
    }
}
