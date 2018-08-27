package com.fastbooster.android_teamwith.share;

import android.app.Application;
import android.util.Log;

import com.fastbooster.android_teamwith.api.ApiUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//모든 액티비티가 다 공유할 수 있게됨.
public class ApplicationShare extends Application {
    public static Map<String, String> praiseList = new HashMap<>();
    public static Map<String, String> categoryList = new HashMap<>();
    public static Map<String, String> regionList = new HashMap<>();
    public static Map<String, String> roleList = new HashMap<>();
    public static Map<String, String> developerSkillList = new HashMap<>();
    public static Map<String, String> plannerSkillList = new HashMap<>();
    public static Map<String, String> designerSkillList = new HashMap<>();
    public static Map<String, String> etcSkillList = new HashMap<>();
    public static Map<String, String[]> skillList = new HashMap<>();
    public static Map<String, String> tendencyList = new HashMap<>();
    public static Map<String, String> applicationStatus = new HashMap<>();

    static {
        FileReadThread fileReadThread = new FileReadThread();
        fileReadThread.start();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    static class FileReadThread extends Thread {
        static final String TAG = "file data...";

        public void run() {
            try {

                JSONObject object = ApiUtil.getJsonObject("/file");
                //return WeatherForecast

                toMap(object, "roleList", roleList);
                toMap(object, "praiseList", praiseList);
                toMap(object, "projectList", categoryList);
                toMap(object, "regionList", regionList);
                toMap(object, "developerSkillList", developerSkillList);
                toMap(object, "plannerSkillList", plannerSkillList);
                toMap(object, "designerSkillList", designerSkillList);
                toMap(object, "etcSkillList", etcSkillList);
                toMapArray(object, "skillList", skillList);
                toMap(object, "tendencyList", tendencyList);

                roleList = sortByComparator(roleList);
                praiseList = sortByComparator(praiseList);
                categoryList = sortByComparator(categoryList);
                regionList = sortByComparator(regionList);
                developerSkillList = sortByComparator(developerSkillList);
                plannerSkillList = sortByComparator(plannerSkillList);
                designerSkillList = sortByComparator(designerSkillList);
                etcSkillList = sortByComparator(etcSkillList);
                skillList = sortByComparatorArray(skillList);
                tendencyList = sortByComparator(tendencyList);

                applicationStatus.put("0", "지원 완료");
                applicationStatus.put("1", "합류");
                applicationStatus.put("2", "탈락");
                applicationStatus.put("3", "취소");


            } catch (Exception e) {
                Log.d("Teamwith app file error", e.getMessage());
                e.printStackTrace();
            }

        }

        public void toMap(JSONObject object, String listName, Map<String, String> target) {
            try {
                JSONObject list = object.getJSONObject(listName);
                Iterator<String> pKey = list.keys();
                while (pKey.hasNext()) {
                    String key = pKey.next();
                    target.put(key, list.getString(key));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void toMapArray(JSONObject object, String listName, Map<String, String[]> target) {
            try {
                JSONObject list = object.getJSONObject(listName);
                Iterator<String> pKey = list.keys();
                while (pKey.hasNext()) {
                    String key = pKey.next();
                    String[] val = new String[2];
                    JSONArray ja = new JSONArray(list.getString(key));
                    val[0] = (String) ja.get(0);
                    val[1] = (String) ja.get(1);

                    target.put(key, val);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private Map<String, String> sortByComparator(Map<String, String> unsortMap) {
            List<Entry<String, String>> list = new LinkedList<Entry<String, String>>(unsortMap.entrySet());

            Collections.sort(list, new Comparator<Entry<String, String>>() {
                @Override
                public int compare(Entry<String, String> o1, Entry<String, String> o2) {
                    Integer v1 = Integer.parseInt(o1.getKey().split("-")[1]);
                    Integer v2 = Integer.parseInt(o2.getKey().split("-")[1]);

                    return v1.compareTo(v2);
                }
            });

            Map<String, String> sortedMap = new LinkedHashMap<String, String>();
            for (Entry<String, String> entry : list) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }

            return sortedMap;
        }
        private Map<String, String[]> sortByComparatorArray(Map<String, String[]> unsortMap) {
            List<Entry<String, String[]>> list = new LinkedList<Entry<String, String[]>>(unsortMap.entrySet());

            Collections.sort(list, new Comparator<Entry<String, String[]>>() {
                @Override
                public int compare(Entry<String, String[]> o1, Entry<String, String[]> o2) {
                    Integer v1 = Integer.parseInt(o1.getKey().split("-")[1]);
                    Integer v2 = Integer.parseInt(o2.getKey().split("-")[1]);

                    return v1.compareTo(v2);
                }
            });

            Map<String, String[]> sortedMap = new LinkedHashMap<String, String[]>();
            for (Entry<String, String[]> entry : list) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }

            return sortedMap;
        }
    }
}




