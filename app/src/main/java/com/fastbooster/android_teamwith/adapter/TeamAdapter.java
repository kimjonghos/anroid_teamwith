package com.fastbooster.android_teamwith.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.model.TeamSimpleVO;
import com.fastbooster.android_teamwith.share.ApplicationShare;
import com.fastbooster.android_teamwith.task.ImageTask;
import com.fastbooster.android_teamwith.viewholder.TeamLayoutViewHolder;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TeamAdapter extends BaseAdapter {
    static final String TAG = "member data...";

    Context context;
    List<TeamSimpleVO> data;
    LayoutInflater layoutInflater;

    public TeamAdapter(Context context, List<TeamSimpleVO> data) {
        this.context = context;
        this.data = data;
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
        TeamLayoutViewHolder vh;

        if (view != null) {
            vh = (TeamLayoutViewHolder) view.getTag();
        } else {
            view = layoutInflater.inflate(R.layout.team_info_layout, null);

            vh = new TeamLayoutViewHolder();
            vh.hkTeamLayoutProjectName = view.findViewById(R.id.hkTeamLayoutProjectName);
            vh.hktvTeamLayoutTeamName = view.findViewById(R.id.hktvTeamLayoutTeamName);
            vh.hktvTeamLayoutProejctCategory = view.findViewById(R.id.hktvTeamLayoutProejctCategory);
            vh.hkivTeamLayoutTeamPic = view.findViewById(R.id.hkivTeamLayoutTeamPic);

            view.setTag(vh);
        }


        vh.hkTeamLayoutProjectName.setText(data.get(i).getTeamProjectName());
        vh.hktvTeamLayoutTeamName.setText(data.get(i).getTeamName());
        vh.hktvTeamLayoutProejctCategory.setText((String) ApplicationShare.categoryList.
                get(data.get(i).getProjectCategoryId()));
        vh.hkivTeamLayoutTeamPic.setTag(data.get(i).getTeamPic());

        ImageTask imgTask = new ImageTask(context);
        imgTask.execute(vh.hkivTeamLayoutTeamPic);

        return view;
    }

}
