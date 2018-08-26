package com.fastbooster.android_teamwith.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.ApplicantVO;
import com.fastbooster.android_teamwith.model.InterviewVO;
import com.fastbooster.android_teamwith.task.DecideTask;
import com.fastbooster.android_teamwith.task.ImageTask;
import com.fastbooster.android_teamwith.viewholder.ApplicantViewHolder;

import java.util.List;

public class ApplicantAdapter extends BaseAdapter {
    public static final String APPLYCOMPLETE = "0";
    public static final String PASS = "1";
    public static final String FAIL = "2";
    public static final String CANCEL = "3";

    private Context context;
    private List<ApplicantVO> data;
    private List<List<InterviewVO>> interviewList;
    private LayoutInflater layoutInflater;
  
    public ApplicantAdapter(Context context, List<ApplicantVO> data, List<List<InterviewVO>> interviewList) {
        this.context = context;
        this.data = data;
        this.interviewList = interviewList;
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
        final int ii = i;
        View itemLayout = view;
        ApplicantViewHolder viewHolder = null;
        if (itemLayout == null) {
            itemLayout = layoutInflater.inflate(R.layout.applicant_layout, null);
            viewHolder = new ApplicantViewHolder();
            viewHolder.tvMemberName = itemLayout.findViewById(R.id.tvMemberName);
            viewHolder.tvRoleId = itemLayout.findViewById(R.id.tvRoleId);
            viewHolder.tvApplicationDate = itemLayout.findViewById(R.id.tvApplicationDate);
            viewHolder.tvApplicationStatus = itemLayout.findViewById(R.id.tvApplicationStatus);
            viewHolder.ivMemberPic = itemLayout.findViewById(R.id.ivMemberPic);
            viewHolder.btnShow = itemLayout.findViewById(R.id.btnShow);
            viewHolder.btnOK = itemLayout.findViewById(R.id.btnOK);
            viewHolder.btnNO = itemLayout.findViewById(R.id.btnNO);
            itemLayout.setTag(viewHolder);
        } else {
            viewHolder = (ApplicantViewHolder) itemLayout.getTag();
        }
        viewHolder.tvMemberName.setText(data.get(i).getMemberName());
        viewHolder.tvRoleId.setText(data.get(i).getRoleId());
        viewHolder.tvApplicationDate.setText(data.get(i).getApplicationDate().substring(0, 10));
        String status = null;
        switch (data.get(i).getApplicationStatus()) {
            case APPLYCOMPLETE:
                status = "지원 완료";
                break;
            case PASS:
                status = "합류";
                break;
            case FAIL:
                status = "탈락";
                break;
            case CANCEL:
                status = "취소";
                break;
            default:
                status = "오류";
                break;
        }
        viewHolder.tvApplicationStatus.setText(status);
        viewHolder.ivMemberPic.setTag(data.get(i).getMemberPic());
        ImageTask imageTask = new ImageTask(context);
        imageTask.execute(viewHolder.ivMemberPic);
        if (!data.get(i).getApplicationStatus().equals(APPLYCOMPLETE)) {
            viewHolder.btnOK.setVisibility(View.GONE);
            viewHolder.btnNO.setVisibility(View.GONE);
        } else {
            viewHolder.btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnClick(ii, PASS);
                }
            });
            viewHolder.btnNO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnClick(ii, FAIL);
                }
            });
        }
        viewHolder.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog);
                final View dialogLayout = View.inflate(context, R.layout.application_content_layout, null);
                TextView freeWriting = dialogLayout.findViewById(R.id.freeWriting);
                freeWriting.setText(data.get(ii).getApplicationFreewriting());
                ListView interviewListView = dialogLayout.findViewById(R.id.interviewListView);
                InterviewAdapter adapter = new InterviewAdapter(context, interviewList.get(ii));
                interviewListView.setAdapter(adapter);
//                Button btnClose = dialogLayout.findViewById(R.id.btnClose);
//                btnClose.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.
//                    }
//                });
                dialog.setView(dialogLayout);
                dialog.show();
            }
        });


        return itemLayout;
    }

    public void btnClick(final int i, final String status) {
        AlertDialog.Builder dialogB = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog);
        final AlertDialog dialog = dialogB.create();
        final View dialogLayout = View.inflate(context, R.layout.decide_layout, null);
        TextView tvNO = dialogLayout.findViewById(R.id.tvNO);
        TextView tvOK=dialogLayout.findViewById(R.id.tvOK);
        if(status.equals("1")){
            tvNO.setVisibility(View.GONE);
        }
        else{
            tvOK.setVisibility(View.GONE);
        }

        TextView tvMemberName = dialogLayout.findViewById(R.id.tvMemberName);
        tvMemberName.setText(data.get(i).getMemberName());
        Button btnNO = dialogLayout.findViewById(R.id.btnNO);
        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setView(dialogLayout);
        dialog.show();
        Button btnOK = dialogLayout.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecideTask decideTask = new DecideTask(context, data.get(i).getApplicationId(), status,dialog);
                decideTask.execute();
            }
        });
    }

}
