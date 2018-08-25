package com.fastbooster.android_teamwith.api;

import android.net.Uri;
import android.util.Log;

import com.fastbooster.android_teamwith.model.PortfolioSimpleVO;
import com.fastbooster.android_teamwith.util.Criteria;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PortfolioApi {

    public static final String URL_STR = "http://192.168.30.64:8089/api/portfolioSearch";

    public static JSONObject getPortfolioDetail(String portfolioId) throws Exception {
        JSONObject json = null;
        HttpURLConnection conn = null;
        portfolioId = portfolioId.split("-")[1];
        URL url = new URL(URL_STR + portfolioId);
        try {
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setReadTimeout(1000);
            conn.setDoInput(true);


            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-type", "application/json");
            int responseCode = conn.getResponseCode();

            StringBuilder sb = new StringBuilder();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                //JSON object 받아오기
                br.close();
                json = new JSONObject(sb.toString());
                conn.disconnect();
                return json;
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();

        }

        return null;
    }

    public static List<PortfolioSimpleVO> getPortfolioList(Criteria cri, List<String> project, String keyword) throws Exception {

        Uri.Builder params = new Uri.Builder();

        if ((keyword == null || keyword.trim().equals("")) && project == null) {
            params.appendPath("recent");
        } else {
            if (keyword != null) {
                params.appendQueryParameter("keyword", keyword);
            }
            if (project != null) {
                for (String p : project) {
                    params.appendQueryParameter("project", p);
                }
            }
        }
        if (cri != null) {
            params.appendQueryParameter("page", cri.getPage() + "");
            params.appendQueryParameter("perPageNum", cri.getPerPageNum() + "");
        }
        URL url = new URL(URL_STR + params.toString());
        Log.v("url", url.toString());
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(1000);
            conn.setDoInput(true);

            int responseCode = conn.getResponseCode();
            StringBuilder sb = new StringBuilder();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                List<PortfolioSimpleVO> list = new ArrayList<>();
                JSONObject json = new JSONObject(sb.toString());

                JSONArray jarray = json.getJSONArray("portfolioList");
                PortfolioSimpleVO portfolioSimpleVO = null;
                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jo = jarray.getJSONObject(i);
                    String portfolioId = jo.getString("portfolioId");
                    String portfolioPic = jo.getString("portfolioPic");
                    String memberId = jo.getString("memberId");
                    String memberName = jo.getString("memberName");
                    String portfolioTitle = jo.getString("portfolioTitle");
                    String portfolioBest = jo.getString("portfolioBest");
                    String projectCategory = jo.getString("projectCategoryId");
                    String portfolioIntro = jo.getString("portfolioIntro");
                    String portfolioUpdateDate = jo.getString("portfolioUpdateDate");
                    portfolioSimpleVO = new PortfolioSimpleVO(portfolioId, portfolioPic, memberId, memberName, portfolioTitle, portfolioBest, projectCategory,
                            portfolioIntro, Date.valueOf(portfolioUpdateDate));
                    list.add(portfolioSimpleVO);
                }
                br.close();
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return null;
    }

    public static List<PortfolioSimpleVO> getMemberPortfolioList(String pmemberId) throws Exception {
        URL url = new URL(URL_STR + "/member/" + pmemberId);
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(1000);
            conn.setDoInput(true);

            int responseCode = conn.getResponseCode();
            StringBuilder sb = new StringBuilder();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                List<PortfolioSimpleVO> list = new ArrayList<>();
                JSONObject json = new JSONObject(sb.toString());

                JSONArray jarray = json.getJSONArray("portfolioList");
                PortfolioSimpleVO portfolioSimpleVO = null;
                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jo = jarray.getJSONObject(i);
                    String portfolioId = jo.getString("portfolioId");
                    String portfolioPic = jo.getString("portfolioPic");
                    String memberId = jo.getString("memberId");
                    String memberName = jo.getString("memberName");
                    String portfolioTitle = jo.getString("portfolioTitle");
                    String portfolioBest = jo.getString("portfolioBest");
                    String projectCategory = jo.getString("projectCategoryId");
                    String portfolioIntro = jo.getString("portfolioIntro");
                    String portfolioUpdateDate = jo.getString("portfolioUpdateDate");
                    portfolioSimpleVO = new PortfolioSimpleVO(portfolioId, portfolioPic, memberId, memberName, portfolioTitle, portfolioBest, projectCategory,
                            portfolioIntro, Date.valueOf(portfolioUpdateDate));
                    list.add(portfolioSimpleVO);
                }
                br.close();
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return null;
    }
}
