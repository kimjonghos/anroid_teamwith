package com.fastbooster.android_teamwith.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.TeamActivity;
import com.fastbooster.android_teamwith.TeamLeaderActivity;
import com.fastbooster.android_teamwith.model.InterviewQuestionDTO;
import com.fastbooster.android_teamwith.model.RecruitVO;
import com.fastbooster.android_teamwith.model.RequireSkillVO;
import com.fastbooster.android_teamwith.share.ApplicationShare;
import com.fastbooster.android_teamwith.task.ApplyTask;
import com.fastbooster.android_teamwith.viewholder.RecruitViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TeamDetailRecruitAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private final String teamId;
    private final List<RecruitVO> data;
    private final List<InterviewQuestionDTO> interview;
    private List<RequireSkillVO> requireSkillList;
    private final String teamStatus;
    private final int dDay;

    public TeamDetailRecruitAdapter(Context context, String teamId, List<RecruitVO> data, List<InterviewQuestionDTO> interview, List<RequireSkillVO> requireSkillList, int dDay, String teamStatus) {
        this.context = context;
        this.teamId = teamId;
        this.data = data;
        this.interview = interview;
        this.requireSkillList = requireSkillList;
        this.dDay = dDay;
        this.teamStatus = teamStatus;
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
            viewHolder.hktvRecruitExplainConst = itemLayout.findViewById(R.id.hktvRecruitExplainConst);
            viewHolder.hktvRecruitPreference = (TextView) itemLayout.findViewById(R.id.hktvRecruitPreference);
            viewHolder.hktvRecruitpreferenceConst = itemLayout.findViewById(R.id.hktvRecruitPreferenceConst);
            viewHolder.hktvRecruitSkill = (TextView) itemLayout.findViewById(R.id.hktvRequireSkill);
            viewHolder.hktvRequireSkillConst = itemLayout.findViewById(R.id.hktvRequireSkillConst);
            viewHolder.recruitExplainRow = itemLayout.findViewById(R.id.recruitExplainRow);
            viewHolder.recruitPreferenceRow = itemLayout.findViewById(R.id.recruitPreferenceRow);
            viewHolder.requireSkillRow = itemLayout.findViewById(R.id.requireSkillRow);
            viewHolder.applyBtn = (Button) itemLayout.findViewById(R.id.applyBtn);
            itemLayout.setTag(viewHolder);
        } else {
            viewHolder = (RecruitViewHolder) itemLayout.getTag();
        }

        viewHolder.hktvRecruitRole.setText(ApplicationShare.roleList.get(data.get(i).getRoleId()).toString());
        viewHolder.hktvRecruitPeopleNum.setText(data.get(i).getRecruitPeopleNum() + " 명");
        if (data.get(i).getRecruitExplain() == null || data.get(i).getRecruitExplain().equals("null")) {
            viewHolder.hktvRecruitExplainConst.setVisibility(View.GONE);
            viewHolder.hktvRecruitExplain.setVisibility(View.GONE);
            viewHolder.recruitExplainRow.setVisibility(View.GONE);
        } else {
            viewHolder.hktvRecruitExplain.setText(data.get(i).getRecruitExplain());
        }
        if (data.get(i).getRecruitPreference() == null || data.get(i).getRecruitPreference().equals("null")) {
            viewHolder.hktvRecruitpreferenceConst.setVisibility(View.GONE);
            viewHolder.hktvRecruitPreference.setVisibility(View.GONE);
            viewHolder.recruitPreferenceRow.setVisibility(View.GONE);
        } else {
            viewHolder.hktvRecruitPreference.setText(data.get(i).getRecruitPreference());
        }
        StringBuilder sb = new StringBuilder();

        for (RequireSkillVO requireSkill : requireSkillList) {
            if (requireSkill.getRecruitId().equals(data.get(i).getRecruitId())) {
                sb.append((ApplicationShare.skillList.get(requireSkill.getSkillId()))[0] + " ");
            }
        }

        if (sb.toString().isEmpty() || sb == null || sb.toString().equals("null")) {
            viewHolder.hktvRecruitSkill.setVisibility(View.GONE);
            viewHolder.hktvRequireSkillConst.setVisibility(View.GONE);
            viewHolder.requireSkillRow.setVisibility(View.GONE);
        } else {
            viewHolder.hktvRecruitSkill.setText(sb.toString());
        }
        final String roleId = data.get(i).getRoleId();
        if (context instanceof TeamLeaderActivity) {
            viewHolder.applyBtn.setVisibility(View.GONE);
        } else if (context instanceof TeamActivity) {
            if (dDay < 0 || teamStatus.equals("1")) {
                viewHolder.applyBtn.setVisibility(View.GONE);
            } else {
                viewHolder.applyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder dialogB = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                        final AlertDialog dialog = dialogB.create();
                        final View dialogLayout = View.inflate(context, R.layout.apply_layout, null);
                        TextView hktvRecruitRole = (TextView) dialogLayout.findViewById(R.id.hktvRecruitRole);
                        TextView interviewQ1 = (TextView) dialogLayout.findViewById(R.id.tvInterviewQuestion1);
                        TextView interviewQ2 = (TextView) dialogLayout.findViewById(R.id.tvInterviewQuestion2);
                        TextView interviewQ3 = (TextView) dialogLayout.findViewById(R.id.tvInterviewQuestion3);
                        EditText interviewA1 = (EditText) dialogLayout.findViewById(R.id.etInterviewAnswer1);
                        EditText interviewA2 = (EditText) dialogLayout.findViewById(R.id.etInterviewAnswer2);
                        EditText interviewA3 = (EditText) dialogLayout.findViewById(R.id.etInterviewAnswer3);
                        TextView interviewTitle1 = (TextView) dialogLayout.findViewById(R.id.tvInterviewTitle1);
                        TextView interviewTitle2 = (TextView) dialogLayout.findViewById(R.id.tvInterviewTitle2);
                        TextView interviewTitle3 = (TextView) dialogLayout.findViewById(R.id.tvInterviewTitle3);
                        final TextView[] interviewTitleAry = new TextView[]{interviewTitle1, interviewTitle2, interviewTitle3};
                        final EditText[] interviewAnswers = new EditText[]{interviewA1, interviewA2, interviewA3};
                        final TextView[] textViews = new TextView[]{interviewQ1, interviewQ2, interviewQ3};

                        if (interview == null || interview.isEmpty()) {
                            for (int i = 0; i < interview.size(); i++) {
                                interviewTitleAry[i].setVisibility(View.GONE);
                                interviewAnswers[i].setVisibility(View.GONE);
                                textViews[i].setVisibility(View.GONE);
                            }
                        } else {
                            for (int i = 0; i < interview.size(); i++) {
                                textViews[i].setText(interview.get(i).getInterviewQuestionContent());
                            }
                            for (int i = 2; i >= interview.size(); i--) {
                                interviewTitleAry[i].setVisibility(View.GONE);
                                interviewAnswers[i].setVisibility(View.GONE);
                                textViews[i].setVisibility(View.GONE);
                            }
                        }
                        Button hkBtnApply = (Button) dialogLayout.findViewById(R.id.hkBtnApply);
                        Button hkBtnApplyCancel = (Button) dialogLayout.findViewById(R.id.hkBtnApplyCancle);
                        hktvRecruitRole.setText(ApplicationShare.roleList.get(roleId));
                        hkBtnApply.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //지원 정보를 가져와라
                                List<String> interviewQuestionId = new ArrayList<String>();
                                List<String> interviewAnswer = new ArrayList<String>();
                                for (int i = 0; i < interview.size(); i++) {
                                    interviewQuestionId.add(interview.get(i).getInterviewQuestionId());
                                    interviewAnswer.add(interviewAnswers[i].getText().toString());
                                }
                                TextView tvFreewriting = (TextView) dialogLayout.findViewById(R.id.hketFreewriting);
                                String freewrting = tvFreewriting.getText().toString();

                                //지원을 해라
                                ApplyTask applyTask = new ApplyTask(context, teamId, interviewAnswer, interviewQuestionId, freewrting, roleId);
                                applyTask.execute();

                                //그리고 어딘가로 가라!
                                Toast.makeText(context, "지원이 완료되었습니다.", Toast.LENGTH_LONG).show();
                                dialog.dismiss();

                            }
                        });
                        hkBtnApplyCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        dialog.setView(dialogLayout);
                        dialog.show();
                    }
                });
            }
        }

        return itemLayout;
    }
}
