package com.fastbooster.android_teamwith.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.PortfolioActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.PortfolioSimpleVO;
import com.fastbooster.android_teamwith.task.ImageTask;
import com.fastbooster.android_teamwith.share.ApplicationShare;
import com.fastbooster.android_teamwith.viewholder.PortfolioViewHolder;


import java.util.List;

public class PologPortfoliListAdapter extends BaseAdapter {
    Context context;
    List<PortfolioSimpleVO> data;
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

        final int ii=i;
        if(itemLayout!=null){
            viewHolder=(PortfolioViewHolder)view.getTag();
        }else{
            view=inflater.inflate(R.layout.portfolioinfo_layout,null);

            viewHolder=new PortfolioViewHolder();
            viewHolder.portfolioMemberName=view.findViewById(R.id.k_tv_info_memberId);
            viewHolder.portfolioPic=view.findViewById(R.id.k_iv_info_portfolioPic);
            viewHolder.portfolioTitle=view.findViewById(R.id.k_tv_info_portfolioTitle);
            viewHolder.projectCategoryId=view.findViewById(R.id.k_tv_info_projectCategory);
            viewHolder.portfolioLayout=view.findViewById(R.id.k_ll_portfolio);
            viewHolder.portfolioLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, PortfolioActivity.class);
                    intent.putExtra("portfolioId",data.get(ii).getPortfolioId());
                    context.startActivity(intent);
                }
            });
            view.setTag(viewHolder);
        }
        viewHolder.portfolioMemberName.setText(data.get(i).getMemberName()+"님");
        viewHolder.portfolioPic.setTag(data.get(i).getPortfolioPic());
        viewHolder.portfolioTitle.setText(data.get(i).getPortfolioTitle());
        viewHolder.projectCategoryId.setText((String) ApplicationShare.categoryList.get(data.get(i).getProjectCategoryId()));

        ImageTask imageViewTask= new ImageTask(context);
        imageViewTask.execute(viewHolder.portfolioPic);

        return view;
    }
}
