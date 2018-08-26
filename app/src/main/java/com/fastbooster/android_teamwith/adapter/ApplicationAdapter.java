package com.fastbooster.android_teamwith.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.InterviewVO;
import com.fastbooster.android_teamwith.model.MyApplicationVO;
import com.fastbooster.android_teamwith.share.ApplicationShare;
import com.fastbooster.android_teamwith.task.ApplicationCancelTask;
import com.fastbooster.android_teamwith.task.ImageTask;
import com.fastbooster.android_teamwith.viewholder.ApplicationViewHolder;

import java.util.List;
import java.util.Map;

public class ApplicationAdapter extends BaseAdapter {
    static final String TAG = "member data...";

    Context context;
    List<MyApplicationVO> applicationList;
    Map<String, List<InterviewVO>> interviewMap;
    LayoutInflater layoutInflater;

    public ApplicationAdapter(Context context, Object[] data) {
        this.context = context;
        this.applicationList = (List<MyApplicationVO>) data[0];
        this.interviewMap = (Map<String, List<InterviewVO>>) data[1];
        Log.v("myApplication adapter size", applicationList.size() + "");
        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return applicationList.size();
    }

    @Override
    public Object getItem(int i) {
        return applicationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ApplicationViewHolder vh;
        final int fi = i;
        if (view != null) {
            vh = (ApplicationViewHolder) view.getTag();
        } else {
            view = layoutInflater.inflate(R.layout.application_layout, null);

            vh = new ApplicationViewHolder();
            vh.teamPic = view.findViewById(R.id.teamPic);
            vh.teamName = view.findViewById(R.id.teamName);
            vh.recruitingRole = view.findViewById(R.id.recruitingRole);
            vh.regDate = view.findViewById(R.id.regDate);
            vh.status = view.findViewById(R.id.status);
            vh.cancelBtn = view.findViewById(R.id.cancelBtn);
            vh.interviewBtn = view.findViewById(R.id.interviewBtn);

            view.setTag(vh);
        }
        Log.v("myApplication data adapter", applicationList.get(i).toString());
        vh.teamPic.setTag(applicationList.get(i).getTeamPic());
        ImageTask imgTask = new ImageTask(context);
        imgTask.execute(vh.teamPic);

        vh.teamName.setText(applicationList.get(i).getTeamName());
        vh.recruitingRole.setText((String) ApplicationShare.roleList.
                get(applicationList.get(i).getRoleId()));
        vh.regDate.setText(applicationList.get(i).getApplicationDate());

        switch (applicationList.get(i).getApplicationStatus()) {
            case "0":
                vh.status.setText("지원 완료");
                break;
            case "1":
                vh.status.setText("합류");
                break;
            case "2":
                vh.status.setText("탈락");
                break;
            case "3":
                vh.status.setText("취소");
                break;
        }
        vh.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                dialog.setTitle("지원 취소");
                dialog.setMessage("정말 [ " + applicationList.get(fi).getTeamName() + " ] 지원을 취소하시겠습니까?");
                dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ApplicationCancelTask act = new ApplicationCancelTask(context,vh.status);
                        act.execute(applicationList.get(fi).getApplicationId());
                        /* JSONObject jo = ApiUtil.getMyJsonObject(context, "/cancel/"
                                + applicationList.get(fi).getApplicationId());
                        try {
                            if (jo.getString("result").equals("true")) {
                                vh.status.setText("취소");
                            }
                        } catch (Exception e) {
                            Toast.makeText(context, "서버에 오류가 발생하였습니다. 다시 시도해주세요."
                                    , Toast.LENGTH_SHORT).show();
                            e.printStackTrace();

                        }*/
                    }
                });
                dialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                dialog.show();

            }
        });

        vh.interviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                dialog.setTitle("지원 내용");
                final View contentView = View.inflate(context, R.layout.application_content_layout, null);
                TextView freeWriting = contentView.findViewById(R.id.freeWriting);
                freeWriting.setText(applicationList.get(fi).getApplicationFreewriting());
                ListView listView = contentView.findViewById(R.id.interviewListView);
                List<InterviewVO> itvList = interviewMap.get(applicationList.get(fi).getApplicationId());
                Log.v("interview", itvList.size() + "");
                listView.setAdapter(new InterviewAdapter(context, itvList));

                dialog.setView(contentView);


                dialog.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                dialog.show();

            }
        });


        return view;
    }

}