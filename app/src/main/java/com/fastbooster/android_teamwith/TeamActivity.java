package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import java.net.HttpURLConnection;
import java.net.URL;

public class TeamActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        TeamDetailTask task=new TeamDetailTask(this);
        task.execute();
    }
}
class TeamDetailTask extends AsyncTask<String,Void,Void>{
    private final Context context;

    public TeamDetailTask(Context context) {
        this.context=context;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //어댑터를 만든다.
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(String... teamId) {
        String serverName = "http://localhost:8089/";
        HttpURLConnection conn=null;

        try {
            URL url = new URL(serverName+"teamSearch/"+teamId[0]);
            conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(1000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            int responseCode=conn.getResponseCode();
            if(responseCode==HttpURLConnection.HTTP_OK){
                //JSON object 받아오기
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            conn.disconnect();
        }
        return null;
    }
}