package com.fastbooster.android_teamwith.task;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.fastbooster.android_teamwith.api.ApiUtil;
import com.fastbooster.android_teamwith.share.ApplicationShare;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PraiseCheckTask extends AsyncTask<Void, Void, List<String>> {

    private final Context context;
    AlertDialog.Builder dialog;
    String target;
    TextView[] prTv;

    public PraiseCheckTask(Context context, AlertDialog.Builder dialog, String target, TextView[] prTv) {
        this.context = context;
        this.dialog = dialog;
        this.target = target;
        this.prTv = prTv;
    }

    @Override
    protected List<String> doInBackground(Void... data) {
        JSONObject jo = ApiUtil.getMyJsonObject(context, "/praise/check?target=" + target);
        if (jo.has("myPraiseList")) {


            try {
                JSONArray array = jo.getJSONArray("myPraiseList");
                List<String> result = null;
                if (array == null) {
                    Log.v("praise", "칭찬불가");
                    //칭찬 불가능.
                } else if (array.getJSONObject(0).getString("praiseId").equals("never")) {
                    Log.v("praise", "칭찬ok");
                    //칭찬 가능하지만 칭찬한 적이 없음.
                    result = new ArrayList<>();
                } else {
                    //이전에 칭찬한 정보를 불러옴.
                    result = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        result.add(array.getJSONObject(i).getString("praiseId"));
                    }
                }
                return result;


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<String> data) {
        super.onPostExecute(data);
        final List<String> prevList = data;
        if (data == null) {
            Toast.makeText(context, "같은 팀원만 칭찬할 수 있습니다.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        final String[] praiseKeyList;

        final String[] praiseList;

        final boolean[] praiseChecked;

        praiseList = new String[ApplicationShare.praiseList.size()];
        praiseKeyList = new String[ApplicationShare.praiseList.size()];
        praiseChecked = new boolean[praiseList.length];
        int i = 0;
        for (Object s : ApplicationShare.praiseList.keySet()) {
            String k = (String) s;
            praiseList[i] = (String) ApplicationShare.praiseList.get(k);
            praiseKeyList[i++] = k;
        }
        for (i = 0; i < data.size(); i++) {
            for (int j = 0; j < praiseKeyList.length; j++) {
                if (data.get(i).equals(praiseKeyList[j])) {
                    praiseChecked[j] = true;
                }
            }
        }

        dialog = new AlertDialog.Builder(context,
                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        dialog.setTitle("칭찬할 항목을 선택하세요.");
        dialog.setMultiChoiceItems(praiseList, praiseChecked,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                    }
                });


        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuffer sb = new StringBuffer();

                //사용자가 선택한 값 저장하기
                List<String> praiseSelectedList = new ArrayList<>();
                for (int j = 0; j < praiseChecked.length; j++) {
                    if (praiseChecked[j]) {
                        praiseSelectedList.add(praiseKeyList[j]);
                    }
                }

                PraiseTask pt = new PraiseTask(context, prTv);
                List<String> targetList = new ArrayList<>();
                targetList.add(target);
                pt.execute(targetList, praiseSelectedList, prevList);

            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialog.show();
    }
}
