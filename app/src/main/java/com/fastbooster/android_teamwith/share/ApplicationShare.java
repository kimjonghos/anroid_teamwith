package com.fastbooster.android_teamwith.share;

import android.app.Application;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//모든 액티비티가 다 공유할 수 있게됨.
public class ApplicationShare extends Application {
    public static Map<String, Object> praiseList = new HashMap<>();
    public static Map<String, Object> categoryList = new HashMap<>();
    public static Map<String, Object> regionList = new HashMap<>();
    public static Map<String, Object> roleList = new HashMap<>();
    public static Map<String, Object> developerSkillList = new HashMap<>();
    public static Map<String, Object> plannerSkillList = new HashMap<>();
    public static Map<String, Object> designerSkillList = new HashMap<>();
    public static Map<String, Object> etcSkillList = new HashMap<>();
    public static Map<String, Object> skillList = new HashMap<>();
    public static Map<String, Object> tendencyList = new HashMap<>();

    static {
        FileReadThread fileReadThread = new FileReadThread();
        fileReadThread.start();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //  FileReadThread fileReadThread = new FileReadThread();
        //  fileReadThread.start();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    static class FileReadThread extends Thread {
        static final String TAG = "file data...";
        private static final String URL_STR = "http://192.168.30.64:8089/api/file";

        public void run() {
            try {

                URL url = new URL(URL_STR);
                Log.v(TAG, url.toString());
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
                }
                //json

                JSONObject object = new JSONObject(sb.toString());
                //return WeatherForecast

                toMap(object, "roleList", roleList);
                toMap(object, "praiseList", praiseList);
                toMap(object, "projectList", categoryList);
                toMap(object, "regionList", regionList);
                toMap(object, "developerSkillList", developerSkillList);
                toMap(object, "plannerSkillList", plannerSkillList);
                toMap(object, "designerSkillList", designerSkillList);
                toMap(object, "etcSkillList", etcSkillList);
                toMap(object, "skillList", skillList);
                toMap(object, "tendencyList", tendencyList);

            } catch (Exception e) {
                Log.d("Weather app error", e.getMessage());
                e.printStackTrace();
            }

        }

        public void toMap(JSONObject object, String listName, Map<String, Object> target) {
            try {
                JSONObject list = object.getJSONObject(listName);
                Iterator<String> pKey = list.keys();
                while (pKey.hasNext()) {
                    String key = pKey.next();
                    Log.v("file", listName + "하나씩 읽고잇음 " + key + ":" + list.getString(key));

                    target.put(key, list.getString(key));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}




