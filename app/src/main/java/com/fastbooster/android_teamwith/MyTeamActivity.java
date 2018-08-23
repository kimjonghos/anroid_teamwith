package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import java.util.zip.Inflater;

public class MyTeamActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);

        //ListView myTeamListView=(ListView)findViewById(R.id.myTeamListView);


        //어씽크 태스크 생성 및 호출
        //어씽크 태스크에서 Api 호출, 받은 데이터로 어댑터 생성
            //어댑터에서 레이아웃인플레이터로 열 세팅해줘야한다.

        //리스트뷰에 어댑터 세팅하면 끝!
    }
}
