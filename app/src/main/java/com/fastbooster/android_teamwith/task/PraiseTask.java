package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.api.ApiUtil;

import org.json.JSONObject;

import java.util.List;

public class PraiseTask extends AsyncTask<List<String>, Void, List<String>> {

    private final Context context;
    LayoutInflater inflater;

    public PraiseTask(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected List<String> doInBackground(List<String>... praise) {
        Uri.Builder params = new Uri.Builder();
        params.appendQueryParameter("target", praise[0].get(0));
        for (String p : praise[1]) {
            params.appendQueryParameter("praise", p);
        }
        JSONObject jo = ApiUtil.getMyJsonObject(context, "/praise/update" + params.toString());
        if (jo != null) {
            return praise[1];
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<String> data) {
        super.onPostExecute(data);
        if (data != null) {
            View profile = inflater.inflate(R.layout.profile_layout, null);
            TextView[] pr = null;
            for (int i = 1; i < 6; i++) {
                pr = new TextView[]{
                        profile.findViewById(R.id.k_polog_praise1)
                        , profile.findViewById(R.id.k_polog_praise2)
                        , profile.findViewById(R.id.k_polog_praise3)
                        , profile.findViewById(R.id.k_polog_praise4)
                        , profile.findViewById(R.id.k_polog_praise5)
                };
            }
            for (int i = 1; i < 6; i++) {
                if (data.contains("praise-" + i)) {
                    String prStr = pr[i - 1].getText().toString();
                    prStr = prStr.substring(0, prStr.length() - 1);
                    int cnt = Integer.parseInt(prStr) + 1;
                    pr[i].setText(cnt + "회");
                }
            }

        }

    }
}
