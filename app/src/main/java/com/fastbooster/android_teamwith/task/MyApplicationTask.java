package com.fastbooster.android_teamwith.task;

import android.os.AsyncTask;

import com.fastbooster.android_teamwith.api.ApiUtil;
import com.fastbooster.android_teamwith.model.InterviewVO;
import com.fastbooster.android_teamwith.model.MyApplicationVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyApplicationTask extends AsyncTask<Void, Void, List<MyApplicationVO>> {
    @Override
    protected List<MyApplicationVO> doInBackground(Void... voids) {

        JSONObject jo = ApiUtil.getJsonObject("/application/myApplication");
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

    }
}
