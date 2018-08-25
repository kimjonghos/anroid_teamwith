package com.fastbooster.android_teamwith.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.MyApplicationVO;
import com.fastbooster.android_teamwith.share.ApplicationShare;
import com.fastbooster.android_teamwith.task.ImageTask;
import com.fastbooster.android_teamwith.viewholder.ApplicationViewHolder;

import java.util.List;

public class ApplicationAdapter extends BaseAdapter {
    static final String TAG = "member data...";

    Context context;
    List<MyApplicationVO> data;
    LayoutInflater layoutInflater;

    public ApplicationAdapter(Context context, List<MyApplicationVO> data) {
        this.context = context;
        this.data = data;
        Log.v("myApplication adapter size", data.size() + "");
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
        ApplicationViewHolder vh;

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

            view.setTag(vh);
        }
        Log.v("myApplication data adapter", data.get(i).toString());
        vh.teamPic.setTag(data.get(i).getTeamPic());
        vh.teamName.setText(data.get(i).getTeamName());
        vh.recruitingRole.setText((String) ApplicationShare.roleList.
                get(data.get(i).getRoleId()));
        vh.regDate.setText(data.get(i).getApplicationDate());

        switch (data.get(i).getApplicationStatus()) {
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

        ImageTask imgTask = new ImageTask(context);
        imgTask.execute(vh.teamPic);

        return view;
    }
}