package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;

public class MemberImageTask extends AsyncTask<ImageView, Void, Bitmap> {

    private final Context context;
    private ImageView image;

    public MemberImageTask(Context context) {
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {
        image = imageViews[0];
        if (image.getTag() == null) {
            return null;
        }
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
        bitmap = getCircleCroppedBitmap(bitmap);
        if (bitmap != null) {
            image.setImageBitmap(bitmap);
        }

    }

    private static Bitmap getCircleCroppedBitmap(Bitmap bitmap) {
        // use scaleType="centerCrop" on the ImageView to generate desire output
        Bitmap output = null;
        try {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            if (bitmap.getWidth() < bitmap.getHeight()) {
                // Bitmap is in portrait
                canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                        bitmap.getWidth() / 2, paint);
            } else {
                // Bitmap is in landscape or has a 1:1 aspect ratio
                canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                        bitmap.getHeight() / 2, paint);
            }
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }
}
