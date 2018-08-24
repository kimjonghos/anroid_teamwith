package com.fastbooster.android_teamwith.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.SearchActivity;
import com.fastbooster.android_teamwith.adapter.MemberAdapter;
import com.fastbooster.android_teamwith.model.MemberSearchVO;
import com.fastbooster.android_teamwith.api.MemberSearchApi;
import com.fastbooster.android_teamwith.util.Criteria;

import java.util.List;

public class MemberSearchTask extends AsyncTask<Object, Void, List<MemberSearchVO>> {

    static final String TAG="member data...";
    private final Context context;
    private ProgressDialog loading;

    public MemberSearchTask(Context context) {
        this.context = context;
        loading = new ProgressDialog(context);

    }

    @Override
    protected void onPreExecute() {
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.setMessage("멤버 데이터를 불러오는 중입니다...");
        loading.show();
        super.onPreExecute();
    }

    @Override
    protected List<MemberSearchVO> doInBackground(Object... condition) {

        //http url connection
        try {
            return MemberSearchApi.getMember(context, (Criteria)condition[0], new String[]{"region-2","region-3"},
                    null,null,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.v(TAG,"멤버 서치 태스크 43라인 api getMem 오류");
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<MemberSearchVO> memberData) {
        loading.dismiss();

        MemberAdapter adapter = new MemberAdapter(context, memberData);
     //   Log.v(TAG,"member search task 53라인 어댑터 설정 후 데이터 사이즈"+","+memberData.size());
        if (context instanceof SearchActivity) {
            SearchActivity view = (SearchActivity) context;
            GridView result = view.findViewById(R.id.jresultView);
            result.setAdapter(adapter);
        }

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.v("city", "canceled");
    }
}
