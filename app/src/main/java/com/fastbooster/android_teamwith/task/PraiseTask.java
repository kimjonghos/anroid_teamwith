package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.api.ApiUtil;

import org.json.JSONArray;

import java.util.List;

public class PraiseTask extends AsyncTask<List<String>, Void, Boolean> {

    private final Context context;
    LayoutInflater inflater;

    TextView[] prTv;

    List<String> nowList;
    List<String> prevList;

    public PraiseTask(Context context, TextView[] prTv) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.prTv = prTv;
    }

    @Override
    protected Boolean doInBackground(List<String>... praise) {
        Uri.Builder params = new Uri.Builder();
        params.appendQueryParameter("target", praise[0].get(0));
        for (String p : praise[1]) {
            params.appendQueryParameter("praise", p);
        }
        nowList = praise[1];
        prevList = praise[2];
        JSONArray array = ApiUtil.getMyJsonArray(context, "/praise/update" + params.toString());
        if (array != null) {
            return true;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean data) {
        super.onPostExecute(data);
        if (data == true) {
            //   FrameLayout fl = ((PologActivity) context).findViewById(R.id.k_fl_portfolioList);

            // ViewGroup parent = inflater.inflate(R.layout.activity_polog, null);
            View profile = inflater.inflate(R.layout.profile_layout, null);
           /* TextView[] prTv = null;
            for (int i = 1; i < 6; i++) {
                prTv = new TextView[]{
                        profile.findViewById(R.id.k_polog_praise1)
                        , profile.findViewById(R.id.k_polog_praise2)
                        , profile.findViewById(R.id.k_polog_praise3)
                        , profile.findViewById(R.id.k_polog_praise4)
                        , profile.findViewById(R.id.k_polog_praise5)
                };
            }*/
            //이전이나 지금도 없거나 잇음->횟수변화 없음

            //이전에는 있었는데 지금은 없음-> 횟수 감소


            for (int j = 0; j < nowList.size(); j++) {
                if (!prevList.contains(nowList.get(j))) { //이전에는 없엇고 지금은 잇음-> 횟수 추가
                    int id = Integer.parseInt(nowList.get(j).substring(7, 8));
                    String prStr = prTv[id-1].getText().toString();
                    String[] strings = prStr.split("회");
                    int cnt = Integer.parseInt(strings[0]);

                    prTv[id-1].setText(cnt + 1 + "회");
                }
            }
            //for (int i = 1; i < prevList.size(); i++) {
            for (int j = 0; j < prevList.size(); j++) {
                if (!nowList.contains(prevList.get(j))) {//전에는 있었고 지금은 없음-> 횟수 감소
                    int id = Integer.parseInt(prevList.get(j).substring(7, 8));
                    String prStr = prTv[id-1].getText().toString();
                    String[] strings = prStr.split("회");
                    int cnt = Integer.parseInt(strings[0]);
                    prTv[id-1].setText(cnt - 1 + "회");
                }
            }
            // }

        }

    }
}
