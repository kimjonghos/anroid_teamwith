package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;

public class ImageTask extends AsyncTask<ImageView, Void, Bitmap> {

    private final Context context;
    private ImageView image;

    public ImageTask(Context context) {
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {
        image = imageViews[0];
        String urlStr = "http://192.168.30.16:8089" + (String) image.getTag();
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
