package com.fastbooster.android_teamwith.model;

import com.fastbooster.android_teamwith.share.ApplicationShare;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Arrays;

public class PraiseVO {

    private String [] praiseCnt;
    private String [] praiseName;
    public PraiseVO(){

    }
    public PraiseVO(JSONArray ja) throws JSONException {
        praiseCnt=new String[ja.length()];
        praiseName=new String[ja.length()];
        for(int i=0;i<ja.length();i++){
            praiseCnt[i]=ja.getJSONObject(i).getString("praiseCnt");
            String praiseId=ja.getJSONObject(i).getString("praiseId");
            praiseName[i]=(String) ApplicationShare.praiseList.get(praiseId);
        }

    }

    public String[] getPraiseCnt() {
        return praiseCnt;
    }

    public void setPraiseCnt(String[] praiseCnt) {
        this.praiseCnt = praiseCnt;
    }

    public String[] getPraiseName() {
        return praiseName;
    }

    public void setPraiseName(String[] praiseName) {
        this.praiseName = praiseName;
    }

    @Override
    public String toString() {
        return "PraiseVO{" +
                "praiseCnt=" + Arrays.toString(praiseCnt) +
                ", praiseName=" + Arrays.toString(praiseName) +
                '}';
    }
}
