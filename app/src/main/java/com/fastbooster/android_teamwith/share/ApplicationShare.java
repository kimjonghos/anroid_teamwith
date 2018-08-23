package com.fastbooster.android_teamwith.share;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import com.fastbooster.android_teamwith.R;
import com.fastbooster.android_teamwith.TeamSearchActivity;
import com.fastbooster.android_teamwith.adapter.MemberAdapter;
import com.fastbooster.android_teamwith.model.MemberSearchVO;
import com.fastbooster.android_teamwith.service.MemberSearchApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//모든 액티비티가 다 공유할 수 있게됨.
public class ApplicationShare extends Application {
    private String apiKey = null;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Map<String,String> regionList = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        regionList.put("region-1","서울");
        apiKey = null;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

class MemberSearchTask extends AsyncTask<String, Void, List<MemberSearchVO>> {
    static final String TAG="member data...";
    private static final String URL_STR = "http://192.168.30.64:8089/api";
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
    protected List<MemberSearchVO> doInBackground(String... locations) {

        //http url connection
        try {
            try {
                URL url = new URL(URL_STR);
                Log.v(TAG,url.toString());
                HttpURLConnection conn = null;
                StringBuilder sb = new StringBuilder();

                conn = (HttpURLConnection) url.openConnection();
//connection.
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setConnectTimeout(1000);
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                } else {
                    Log.d("Teamwith app error", "URL=" + URL_STR);
                    return null;
                }
                //json

                JSONArray array = new JSONArray(sb.toString());

                //return WeatherForecast
                List<MemberSearchVO> result = new ArrayList<>();


                for (int i = 0; i < array.length(); i++) {
                    JSONObject f = array.getJSONObject(i);
                    boolean bb = f.isNull("roleId");
                    Log.v(TAG, bb + "null");
                    result.add(new MemberSearchVO(f));
                }
                return result;

            } catch (Exception e) {
                Log.d("Weather app error", e.getMessage());
                e.printStackTrace();
                return null;
            }
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
        if (context instanceof TeamSearchActivity) {
            TeamSearchActivity view = (TeamSearchActivity) context;
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

