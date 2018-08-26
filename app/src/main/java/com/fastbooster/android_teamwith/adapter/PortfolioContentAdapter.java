package com.fastbooster.android_teamwith.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.PortfolioContentVO;
import com.fastbooster.android_teamwith.task.ImageTask;
import com.fastbooster.android_teamwith.viewholder.PortfolioContentViewHolder;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class PortfolioContentAdapter extends BaseAdapter {
    private Context context;
    private List<PortfolioContentVO> data;
    private LayoutInflater inflater;
    public PortfolioContentAdapter(Context context, List<PortfolioContentVO> data){
        this.context=context;
        this.data=data;
        inflater= LayoutInflater.from(context);
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
//        PortfolioContentViewHolder viewHolder;
        View v=inflater.inflate(R.layout.portfolio_content_layout,null);
        LinearLayout ll=(LinearLayout)v.findViewById(R.id.k_ll);
        if(data.get(i).getPortfolioContentName().equals("image")){
            ImageView iv=new ImageView(context);
            iv.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            iv.setForegroundGravity(2);
            LinearLayout.LayoutParams params=(LinearLayout.LayoutParams)iv.getLayoutParams();
            iv.setMaxWidth(1000);
            iv.setTag(data.get(i).getPortfolioContentValue());
            ImageTask imageViewTask= new ImageTask(context);
            imageViewTask.execute(iv);
            ll.addView(iv);
            TextView tv=new TextView(context);
            tv.setText(data.get(i).getPortfolioContentIntro());
            tv.setTextSize(20);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ll.addView(tv);
        }
        return v;
    }
}
