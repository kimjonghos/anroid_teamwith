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
import com.fastbooster.android_teamwith.model.MemberSearchVO;
import com.fastbooster.android_teamwith.viewholder.MemberViewHolder;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MemberAdapter extends BaseAdapter {
    static final String TAG = "member data...";

    Context context;
    List<MemberSearchVO> data;
    LayoutInflater layoutInflater;

    public MemberAdapter(Context context, List<MemberSearchVO> data) {
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
        MemberViewHolder vh;

        if (view != null) {
            vh = (MemberViewHolder) view.getTag();
        } else {
            view = layoutInflater.inflate(R.layout.member_info_layout, null);

            vh = new MemberViewHolder();
            vh.memberName = view.findViewById(R.id.jmemberName1);
            vh.memberRole = view.findViewById(R.id.jmemberRole1);
            vh.memberPic = view.findViewById(R.id.jmemberPic1);

            view.setTag(vh);
        }


        vh.memberName.setText(data.get(i).getMemberName());
        vh.memberRole.setText(data.get(i).getRoleId());
        vh.memberPic.setTag(data.get(i).getMemberPic());

        ImageViewTask imgTask = new ImageViewTask(context);
        imgTask.execute(vh.memberPic);

        return view;
    }

    static class ImageViewTask extends AsyncTask<ImageView, Void, Bitmap> {

        private final Context context;
        private ImageView image;

        public ImageViewTask(Context context) {
            this.context = context;

        }

        @Override
        protected Bitmap doInBackground(ImageView... imageViews) {
            image = imageViews[0];
            String urlStr = "http://192.168.30.64:8089" + (String) image.getTag();
            try {
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(10000);
                conn.setDoInput(true);
                conn.connect();

                return BitmapFactory.decodeStream(conn.getInputStream());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                image.setImageBitmap(bitmap);
            }

        }
    }

}
