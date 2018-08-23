package com.fastbooster.android_teamwith.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fastbooster.android_teamwith.R;

import java.util.List;

public class PologPortfoliListAdapter extends BaseAdapter {
    Context context;
    List<String> data; //나중에 String 대신 model로
    LayoutInflater inflater;
    public PologPortfoliListAdapter(Context context, List<String> data){
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
        View itemLayout=view;
        if(itemLayout==null){
            itemLayout=inflater.inflate(R.layout.portfolioinfo_layout,null);
        }

        return null;
    }
}
