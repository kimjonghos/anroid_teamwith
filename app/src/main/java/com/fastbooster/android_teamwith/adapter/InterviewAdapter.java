package com.fastbooster.android_teamwith.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.InterviewVO;
import com.fastbooster.android_teamwith.viewholder.MyInterviewViewHolder;

import java.util.List;

public class InterviewAdapter extends BaseAdapter {

    Context context;
    List<InterviewVO> data;
    LayoutInflater layoutInflater;

    public InterviewAdapter(Context context, List<InterviewVO> data) {
        this.context = context;
        this.data = data;
        if(data.size()==0){
           InterviewVO vo = new InterviewVO();
            vo.setInterviewQuestionContent("면접 내용이 없습니다.");
            data.add(vo);
        }
        this.layoutInflater = LayoutInflater.from(context);
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
        MyInterviewViewHolder vh;

        if (view != null) {
            vh = (MyInterviewViewHolder) view.getTag();
        } else {
            view = layoutInflater.inflate(R.layout.application_interview_layout, null);

            vh = new MyInterviewViewHolder();
            vh.question = view.findViewById(R.id.question);
            vh.answer = view.findViewById(R.id.answer);

            view.setTag(vh);
        }
        vh.question.setText(data.get(i).getInterviewQuestionContent());
        vh.answer.setText(data.get(i).getInterviewAnswerContent());

        return view;
    }


}
