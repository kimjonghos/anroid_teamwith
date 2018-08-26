package com.fastbooster.android_teamwith.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fastbooster.android_teamwith.PortfolioActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.TeamActivity;
import com.fastbooster.android_teamwith.TeamLeaderActivity;
import com.fastbooster.android_teamwith.model.TeamSimpleVO;
import com.fastbooster.android_teamwith.share.ApplicationShare;
import com.fastbooster.android_teamwith.task.ImageTask;
import com.fastbooster.android_teamwith.viewholder.TeamLayoutViewHolder;

import java.util.List;

public class TeamGridViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<TeamSimpleVO> data;

    public TeamGridViewAdapter(Context context, List<TeamSimpleVO> data){
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
        final int ii=i;
        if(itemLayout==null){
            itemLayout=layoutInflater.inflate(R.layout.team_info_layout,null);
            viewHolder=new TeamLayoutViewHolder();
            viewHolder.hkTeamLayoutProjectName=(TextView)itemLayout.findViewById(R.id.hkTeamLayoutProjectName);
            viewHolder.hktvTeamLayoutTeamName=(TextView)itemLayout.findViewById(R.id.hktvTeamLayoutTeamName);
            viewHolder.hktvTeamLayoutProejctCategory=(TextView)itemLayout.findViewById(R.id.hktvTeamLayoutProejctCategory);
            viewHolder.hkivTeamLayoutTeamPic=(ImageView) itemLayout.findViewById(R.id.hkivTeamLayoutTeamPic);
            viewHolder.hkTeamInfoLayOut=(LinearLayout) itemLayout.findViewById(R.id.hkTeamInfoLayOut);
            viewHolder.hkTeamInfoLayOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent=((Activity)context).getIntent();
                    String ownership=intent.getStringExtra("ownership");
                    SharedPreferences sp = context.getSharedPreferences("memberPref",Context.MODE_PRIVATE);
                    String memberId=sp.getString("memberId",null);
                    if(memberId!=null){
                        if(data.get(ii).getMemberId().equals(memberId)){
                            intent.setClass(context, TeamLeaderActivity.class);
                        }
                        else{
                            intent=new Intent(context, TeamActivity.class);
                        }
                    }
                    intent.putExtra("teamId",data.get(ii).getTeamId());
                    context.startActivity(intent);
                }
            });

            itemLayout.setTag(viewHolder);
        }
        else{
            viewHolder=(TeamLayoutViewHolder)itemLayout.getTag();



        }
        viewHolder.hkTeamLayoutProjectName.setText(data.get(i).getTeamProjectName());
        viewHolder.hktvTeamLayoutTeamName.setText(data.get(i).getTeamName());
        viewHolder.hktvTeamLayoutProejctCategory.setText(data.get(i).getProjectCategoryId());
        viewHolder.hkivTeamLayoutTeamPic.setTag(data.get(i).getTeamPic());
        ImageTask imageTask=new ImageTask(context);
        imageTask.execute(viewHolder.hkivTeamLayoutTeamPic);

        return itemLayout;
    }
}
