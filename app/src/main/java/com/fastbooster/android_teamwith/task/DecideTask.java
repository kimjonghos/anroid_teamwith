package com.fastbooster.android_teamwith.task;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.fastbooster.android_teamwith.ApplicantActivity;
import com.fastbooster.android_teamwith.adapter.ApplicantAdapter;
import com.fastbooster.android_teamwith.api.ApiUtil;

public class DecideTask extends AsyncTask<Void,Void,Void>{
    private final Context context;
    private String applicationId;
    private String status;
    private AlertDialog dialog;

    public DecideTask(Context context, String applicationId, String status, AlertDialog dialog){
        this.context=context;
        this.applicationId=applicationId.substring(12);
        this.status=status;
        this.dialog=dialog;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(status.equals(ApplicantAdapter.PASS)){
            Toast.makeText(context,"합류되었습니다.",Toast.LENGTH_LONG).show();
        }
        else if(status.equals(ApplicantAdapter.FAIL)){
            Toast.makeText(context,"탈락되었습니다.",Toast.LENGTH_LONG).show();
        }
        dialog.dismiss();
        if(context instanceof Activity){
            Activity ac=(Activity) context;
            Intent intent=ac.getIntent();
            ac.finish();
            ac.startActivity(intent);
        }
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            ApiUtil.getMyJsonObject(context,"/application/change/"+applicationId+"?status="+status);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
