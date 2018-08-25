package com.fastbooster.android_teamwith.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.fastbooster.android_teamwith.ProfileEditActivity;
import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.SearchActivity;
import com.fastbooster.android_teamwith.api.ApiUtil;
import com.fastbooster.android_teamwith.model.MemberVO;
import com.fastbooster.android_teamwith.share.ApplicationShare;

import org.json.JSONObject;

public class MyProfileTask extends AsyncTask<Void, Void, MemberVO> {
    private static final String URL_STR = "/member/getEditInfo";
    static final String TAG = "member data...";
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
            JSONObject jo = ApiUtil.getJsonObject(URL_STR);
            return new MemberVO(jo);

        } catch (Exception e) {
            e.printStackTrace();
            Log.v(TAG, "멤버 서치 태스크 43라인 api getMem 오류");
            return null;
        }
    }


}
