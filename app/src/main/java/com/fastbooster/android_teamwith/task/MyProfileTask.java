package com.fastbooster.android_teamwith.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.ProfileEditActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.SearchActivity;
import com.fastbooster.android_teamwith.adapter.MemberAdapter;
import com.fastbooster.android_teamwith.api.MemberSearchApi;
import com.fastbooster.android_teamwith.api.MyProfileApi;
import com.fastbooster.android_teamwith.model.MemberSearchVO;
import com.fastbooster.android_teamwith.model.MemberVO;
import com.fastbooster.android_teamwith.util.Criteria;

import java.util.List;

public class MyProfileTask extends AsyncTask<Void, Void, MemberVO> {

    static final String TAG="member data...";
    private final Context context;
    private ProgressDialog loading;

    public MyProfileTask(Context context) {
        this.context = context;
        loading = new ProgressDialog(context);

    }

    @Override
    protected void onPreExecute() {
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.setMessage("회원님의 정보를 불러오는 중입니다...");
        loading.show();
        super.onPreExecute();
    }

    @Override
    protected MemberVO doInBackground(Void... condition) {
        try {
            return MyProfileApi.getMember(context);
        } catch (Exception e) {
            e.printStackTrace();
            Log.v(TAG,"멤버 서치 태스크 43라인 api getMem 오류");
            return null;
        }
    }

    @Override
    protected void onPostExecute(MemberVO memberData) {
        loading.dismiss();

        if (context instanceof SearchActivity) {
            ProfileEditActivity view = (ProfileEditActivity) context;
           // TextView result = view.findViewById(R.id.);
        }

    }

}
