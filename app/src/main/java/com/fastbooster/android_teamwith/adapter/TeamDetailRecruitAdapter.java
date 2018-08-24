package com.fastbooster.android_teamwith.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.InterviewQuestionDTO;
import com.fastbooster.android_teamwith.model.RecruitVO;
import com.fastbooster.android_teamwith.model.RequireSkillVO;
import com.fastbooster.android_teamwith.task.ApplyTask;
import com.fastbooster.android_teamwith.viewholder.RecruitViewHolder;

import java.util.List;

public class TeamDetailRecruitAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    List<RecruitVO> data;
    List<InterviewQuestionDTO> interview;
    List<RequireSkillVO> requireSkillList;

    public TeamDetailRecruitAdapter(Context context, List<RecruitVO> data, List<InterviewQuestionDTO> interview, List<RequireSkillVO> requireSkillList) {
        this.context = context;
        this.data = data;
        this.interview = interview;
        this.requireSkillList=requireSkillList;
        layoutInflater = LayoutInflater.from(context);
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

        View itemLayout = view;
        RecruitViewHolder viewHolder = null;
        if (itemLayout == null) {
            itemLayout = layoutInflater.inflate(R.layout.recruit_layout, null);
            viewHolder = new RecruitViewHolder();
            viewHolder.hktvRecruitRole = (TextView) itemLayout.findViewById(R.id.hktvRecruitRole);
            viewHolder.hktvRecruitPeopleNum = (TextView) itemLayout.findViewById(R.id.hktvRecruitPeopleNum);
            viewHolder.hktvRecruitExplain = (TextView) itemLayout.findViewById(R.id.hktvRecruitExplain);
            viewHolder.hktvRecruitPreference = (TextView) itemLayout.findViewById(R.id.hktvRecruitPreference);
            viewHolder.hktvRecruitSkill = (TextView) itemLayout.findViewById(R.id.hktvRequireSkill);
            viewHolder.applyBtn = (Button) itemLayout.findViewById(R.id.applyBtn);
            itemLayout.setTag(viewHolder);
        } else {
            viewHolder = (RecruitViewHolder) itemLayout.getTag();
        }
        viewHolder.hktvRecruitRole.setText(data.get(i).getRoleId());
        viewHolder.hktvRecruitPeopleNum.setText(data.get(i).getRecruitPeopleNum());
        viewHolder.hktvRecruitExplain.setText(data.get(i).getRecruitExplain());
        viewHolder.hktvRecruitPreference.setText(data.get(i).getRecruitPreference());
        StringBuilder sb=new StringBuilder();
        if(requireSkillList!=null&&!requireSkillList.isEmpty())
        for(RequireSkillVO requireSkill : requireSkillList){
            if(requireSkill.getRecruitId().equals(data.get(i).getRecruitId())){
                sb.append(requireSkill.getSkillId()+" ");
            }
        }
        Log.d("@@@@@@@@@@@@@@@@",sb.toString());
        viewHolder.hktvRecruitSkill.setText(sb.toString());
        final String roleId = data.get(i).getRoleId();

        viewHolder.applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                dialog.setTitle("지원하기");

                final View dialogLayout = View.inflate(context, R.layout.apply_layout, null);
                ListView hkInterviewList = (ListView) dialogLayout.findViewById(R.id.hkInterviewList);
                TextView hktvRecruitRole = (TextView) dialogLayout.findViewById(R.id.hktvRecruitRole);
                Button hkBtnApply=(Button)dialogLayout.findViewById(R.id.hkBtnApply);
                hktvRecruitRole.setText(roleId);
                InterviewQuestionAdapter adapter = new InterviewQuestionAdapter(context, interview);
                hkInterviewList.setAdapter(adapter);
                hkBtnApply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //지원을 해라
                        ApplyTask applyTask=new ApplyTask(context);
                        applyTask.execute();

                        //그리고 어딘가로 가라!

                    }
                });
                dialog.setView(dialogLayout);
                dialog.show();
            }
        });

        return itemLayout;
    }
}
