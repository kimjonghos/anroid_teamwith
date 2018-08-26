package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.fastbooster.android_teamwith.api.ApiUtil;

import org.json.JSONObject;

public class ApplicationCancelTask extends AsyncTask<String, Void, Boolean> {
    private static final String URL_STR = "/member";
    private final Context context;
    private final TextView tv;

    public ApplicationCancelTask(Context context, TextView tv) {
        this.context = context;
        this.tv = tv;
    }

    @Override
    protected Boolean doInBackground(String... query) {
        try {
            JSONObject jo = ApiUtil.getMyJsonObject(context, "/application/cancel/"
                    + query[0]);
            if (jo.getString("result").equals("true")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean data) {
        tv.setText("취소");
    }
}