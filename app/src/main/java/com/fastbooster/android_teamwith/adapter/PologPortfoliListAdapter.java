package com.fastbooster.android_teamwith.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.PortfolioSimpleVO;
import com.fastbooster.android_teamwith.task.ImageTask;
import com.fastbooster.android_teamwith.share.ApplicationShare;
import com.fastbooster.android_teamwith.viewholder.PortfolioViewHolder;


import java.util.List;

public class PologPortfoliListAdapter extends BaseAdapter {
    Context context;
    List<PortfolioSimpleVO> data; //나중에 String 대신 model로
    LayoutInflater inflater;
    public PologPortfoliListAdapter(Context context, List<PortfolioSimpleVO> data){
        this.context=context;
        this.data=data;
        inflater=LayoutInflater.from(context);
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
        PortfolioViewHolder viewHolder;
        View itemLayout=view;


        if(itemLayout!=null){
            viewHolder=(PortfolioViewHolder)view.getTag();
        }else{
            view=inflater.inflate(R.layout.portfolioinfo_layout,null);

            viewHolder=new PortfolioViewHolder();
            viewHolder.portfolioMemberId=view.findViewById(R.id.k_tv_info_memberId);
            viewHolder.portfolioPic=view.findViewById(R.id.k_iv_info_portfolioPic);
            viewHolder.portfolioTitle=view.findViewById(R.id.k_tv_info_portfolioTitle);
            viewHolder.projectCategoryId=view.findViewById(R.id.k_tv_info_projectCategory);

            view.setTag(viewHolder);
        }
        viewHolder.portfolioMemberId.setText(data.get(i).getMemberId());
        viewHolder.portfolioPic.setTag(data.get(i).getPortfolioPic());
        viewHolder.portfolioTitle.setText(data.get(i).getPortfolioTitle());
        viewHolder.projectCategoryId.setText((String) ApplicationShare.categoryList.get(data.get(i).getProjectCategoryId()));

        ImageTask imageViewTask= new ImageTask(context);
        imageViewTask.execute(viewHolder.portfolioPic);

        return view;
    }
}
