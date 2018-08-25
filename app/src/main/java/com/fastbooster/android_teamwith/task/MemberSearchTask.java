package com.fastbooster.android_teamwith.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.SearchActivity;
import com.fastbooster.android_teamwith.adapter.MemberAdapter;
import com.fastbooster.android_teamwith.api.ApiUtil;
import com.fastbooster.android_teamwith.model.MemberSearchVO;
import com.fastbooster.android_teamwith.util.Criteria;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MemberSearchTask extends AsyncTask<Object, Void, List<MemberSearchVO>> {
    private static final String URL_STR = "/member";
    static final String TAG = "member data...";
    private final Context context;
    private ProgressDialog loading;

    public MemberSearchTask(Context context) {
        this.context = context;
        loading = new ProgressDialog(context);

    }

    @Override
    protected void onPreExecute() {
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.setMessage("회원 정보를 불러오는 중입니다...");
        loading.show();
        super.onPreExecute();
    }

    @Override
    protected List<MemberSearchVO> doInBackground(Object... condition) {

        //http url connection
        try {
            Log.v("cnt", condition.length + "");
            String query = makeQuery((Criteria) condition[0], (List<String>) condition[1],
                    (List<String>) condition[2], (List<String>) condition[3],
                    (List<String>) condition[4], (String) condition[5]);

            List<MemberSearchVO> result = new ArrayList<>();
            JSONArray array = ApiUtil.getJsonArray(URL_STR + query);
            Log.v("len", "" + array.length());
            try {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    result.add(new MemberSearchVO(obj));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Log.v(TAG, "멤버 서치 태스크 43라인 api getMem 오류");
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<MemberSearchVO> memberData) {
        loading.dismiss();

        MemberAdapter adapter = new MemberAdapter(context, memberData);
        if (context instanceof SearchActivity) {
            SearchActivity view = (SearchActivity) context;
            GridView result = view.findViewById(R.id.resultView);
            result.setAdapter(adapter);
        }

    }

    private String makeQuery(Criteria cri, List<String> region,
                            List<String> project, List<String> role, List<String> skill,
                            String keyword) {

        Uri.Builder params = new Uri.Builder();
        if (cri != null) {
            params.appendQueryParameter("page", cri.getPage() + "");
            params.appendQueryParameter("perPageNum", cri.getPerPageNum() + "");
        }
        if (keyword != null) {
            params.appendQueryParameter("keyword", keyword);
        }
        if (region != null) {
            for (String r : region) {
                params.appendQueryParameter("region", r);
            }
        }
        if (project != null) {
            for (String p : project) {
                params.appendQueryParameter("project", p);
            }
        }
        if (role != null) {
            for (String r : role) {
                params.appendQueryParameter("role", r);
            }
        }
        if (skill != null) {
            for (String s : skill) {
                params.appendQueryParameter("skill", s);
            }
        }

        return params.toString();

    }

}
