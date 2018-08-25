package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.fastbooster.android_teamwith.ApplicationActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.adapter.ApplicationAdapter;
import com.fastbooster.android_teamwith.api.ApiUtil;
import com.fastbooster.android_teamwith.model.InterviewVO;
import com.fastbooster.android_teamwith.model.MyApplicationVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyApplicationTask extends AsyncTask<Void, Void, List<MyApplicationVO>> {
    private static final String URL_STR = "/member";
    static final String TAG = "member data...";
    private final Context context;

    public MyApplicationTask(Context context) {
        this.context = context;
    }

    @Override
    protected List<MyApplicationVO> doInBackground(Void... voids) {

        JSONObject jo = ApiUtil.getMyJsonObject(context, "/application/myApplication");
        try {
            JSONObject jInterview = jo.getJSONObject("interviewMap");
            for (int i = 0; i < jInterview.length(); i++) {
                new InterviewVO();
                //고치기
            }
            List<MyApplicationVO> result = new ArrayList<>();

            JSONArray jarray = jo.getJSONArray("myApplicationList");
            for (int i = 0; i < jarray.length(); i++) {
                result.add(new MyApplicationVO(jarray.getJSONObject(i)));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<MyApplicationVO> data) {

        ApplicationAdapter adapter = new ApplicationAdapter(context, data);
        if (context instanceof ApplicationActivity) {
            ApplicationActivity view = (ApplicationActivity) context;
            ListView result = view.findViewById(R.id.resultView);
            result.setAdapter(adapter);
        }
    }
}
