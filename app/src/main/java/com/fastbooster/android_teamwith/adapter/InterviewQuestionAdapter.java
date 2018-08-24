package com.fastbooster.android_teamwith.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.FaqVO;
import com.fastbooster.android_teamwith.model.InterviewQuestionDTO;
import com.fastbooster.android_teamwith.viewholder.ApplyInterviewHolder;
import com.fastbooster.android_teamwith.viewholder.FaqViewHolder;

import java.util.List;

public class InterviewQuestionAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    List<InterviewQuestionDTO> data;

    public InterviewQuestionAdapter(Context context, List<InterviewQuestionDTO> data){
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
        ApplyInterviewHolder viewHolder=null;
        if(itemLayout==null){
            itemLayout=layoutInflater.inflate(R.layout.apply_interview_layout,null);
            viewHolder=new ApplyInterviewHolder();
            viewHolder.hktvApplyInterviewQuestion=(TextView)itemLayout.findViewById(R.id.hktvApplyInterviewQuestion);
            viewHolder.hketApplyInterviewAnswer=(EditText)itemLayout.findViewById(R.id.hketApplyInterviewAnswer);
            itemLayout.setTag(viewHolder);
        }
        else{
            viewHolder=(ApplyInterviewHolder)itemLayout.getTag();
        }
        viewHolder.hktvApplyInterviewQuestion.setText(data.get(i).getInterviewQuestionContent());
        return itemLayout;
    }
}
