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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MyApplicationTask extends AsyncTask<Void, Void, Object[]> {
    private static final String URL_STR = "/member";
    static final String TAG = "member data...";
    private final Context context;

    public MyApplicationTask(Context context) {
        this.context = context;
    }

    @Override
    protected Object[] doInBackground(Void... voids) {

        JSONObject jo = ApiUtil.getMyJsonObject(context, "/application/myApplication");
        try {
            JSONObject jInterviews = jo.getJSONObject("interviewMap");
            Iterator<String> keys = jInterviews.keys();
            Map<String, List<InterviewVO>> interviewMap = new HashMap<>();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONArray interviews = jInterviews.getJSONArray(key);
                List<InterviewVO> list = new ArrayList<>();
                for (int i = 0; i < interviews.length(); i++) {
                    list.add(new InterviewVO(interviews.getJSONObject(i)));
                }
                interviewMap.put(key, list);
            }
            List<MyApplicationVO> applicationlist = new ArrayList<>();

            JSONArray jarray = jo.getJSONArray("myApplicationList");
            for (int i = 0; i < jarray.length(); i++) {
                applicationlist.add(new MyApplicationVO(jarray.getJSONObject(i)));
            }
            return new Object[]{applicationlist, interviewMap};
        } catch (
                Exception e)

        {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(Object[] data) {

        ApplicationAdapter adapter = new ApplicationAdapter(context, data);
        if (context instanceof ApplicationActivity) {
            ApplicationActivity view = (ApplicationActivity) context;
            ListView result = view.findViewById(R.id.resultView);
            result.setAdapter(adapter);
        }
    }
}
