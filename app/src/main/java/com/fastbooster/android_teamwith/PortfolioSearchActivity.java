package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fastbooster.android_teamwith.share.ApplicationShare;

import java.util.ArrayList;
import java.util.List;

public class PortfolioSearchActivity extends Activity {
    ApplicationShare as;

    String[] categoryKeyList;

    String[] categoryList;

    boolean[] categoryChecked;

    TextView back;
    EditText keyword;
    ImageButton searchBtn;

    TextView categorySelected;

    HorizontalScrollView selectedView;

    GridView resultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_search);
        //다이얼로그에 나올 선택 목록 리스트 초기화
       // PortfolioSearchActivity.InitTask initTask = new PortfolioSearchActivity.InitTask();
       // initTask.execute();

        back = findViewById(R.id.jbackToSearchbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        keyword = findViewById(R.id.jkeyword);
        searchBtn = findViewById(R.id.jsearchBtn);
//검색 버튼 클릭 시
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = keyword.getText().toString();

                //사용자가 선택한 조건 값 저장하기
                List<String> categorySelectedList = new ArrayList<>();
                for (int i = 0; i < categoryChecked.length; i++) {
                    if (categoryChecked[i]) {
                        categorySelectedList.add(categoryKeyList[i]);
                    }
                }
            }
        });

        categorySelected = findViewById(R.id.jcategorySelected);

        selectedView = findViewById(R.id.jselectedView);

        resultView = findViewById(R.id.resultView);
    }
}
