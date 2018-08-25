package com.fastbooster.android_teamwith.task;

import android.content.Context;
import android.os.AsyncTask;

public class ApplyTask extends AsyncTask<Void,Void,Void> {
    private final Context context;

    public ApplyTask(Context context){
        this.context=context;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }
}
