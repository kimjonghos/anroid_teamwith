package com.fastbooster.android_teamwith.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.FaqVO;
import com.fastbooster.android_teamwith.model.RecruitVO;
import com.fastbooster.android_teamwith.viewholder.FaqViewHolder;
import com.fastbooster.android_teamwith.viewholder.RecruitViewHolder;

import java.util.List;

public class TeamDetailFaqAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    List<FaqVO> data;

    public TeamDetailFaqAdapter(Context context, List<FaqVO> data){
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
        FaqViewHolder viewHolder=null;
        if(itemLayout==null){
            itemLayout=layoutInflater.inflate(R.layout.faq_layout,null);
            viewHolder=new FaqViewHolder();
            viewHolder.hktvFaqQuestion=(TextView)itemLayout.findViewById(R.id.hktvFaqQuestion);
            viewHolder.hktvFaqAnswer=(TextView)itemLayout.findViewById(R.id.hktvFaqAnswer);
            itemLayout.setTag(viewHolder);
        }
        else{
            viewHolder=(FaqViewHolder)itemLayout.getTag();
        }
        viewHolder.hktvFaqQuestion.setText(data.get(i).getFaqQuestion());
        viewHolder.hktvFaqAnswer.setText(data.get(i).getFaqAnswer());
        return itemLayout;
    }
}
