package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fastbooster.android_teamwith.share.ApplicationShare;
import com.fastbooster.android_teamwith.task.MemberSearchTask;
import com.fastbooster.android_teamwith.util.Criteria;

import java.util.Map;

public class SearchActivity extends Activity {
    static final String TAG = "member data...";
    ApplicationShare as = new ApplicationShare();

    String[] roleList = {"개발자", "기획자", "디자이너", "기타"};
    String[] regionList = {"서울", "경기", "부산", "제주"};
    String[] skillList = {"C", "C#", "C++", "Java"};
    String[] categoryList = {"C", "C#", "C++", "Java"};

    boolean[] roleChecked = new boolean[roleList.length];
    boolean[] regionChecked = new boolean[regionList.length];
    boolean[] skillChecked = new boolean[skillList.length];
    boolean[] categoryChecked = new boolean[categoryList.length];

    TextView back;
    EditText keyword;
    ImageButton searchBtn;

    TextView regionSelected;
    TextView roleSelected;
    TextView skillSelected;
    TextView categorySelected;

    HorizontalScrollView selectedView;

    GridView resultView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initList(roleList,as.roleList,roleChecked);
        initList(regionList,as.regionList,regionChecked);
        initList(skillList,as.skillList,skillChecked);
        initList(categoryList,as.projectList,categoryChecked);

        back = findViewById(R.id.jbackToSearchbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        keyword = findViewById(R.id.jkeyword);
        searchBtn = findViewById(R.id.jsearchBtn);
        final MemberSearchTask mtask = new MemberSearchTask(this);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = keyword.getText().toString();

                Log.v(TAG, "team search Activity.. excute 전");
                mtask.execute(new Criteria(1,10));
            }
        });


        regionSelected = findViewById(R.id.jregionSelected);
        roleSelected = findViewById(R.id.jroleSelected);
        skillSelected = findViewById(R.id.jskillSelected);
        categorySelected = findViewById(R.id.jcategorySelected);

        selectedView = findViewById(R.id.jselectedView);

        resultView = findViewById(R.id.jresultView);

        Intent intent = getIntent();
        String kind = intent.getStringExtra("kind");
        if (kind.equals("team")) {
            // resultView.setAdapter(teamAdapter);
        } else if (kind.equals("member")) {
            //resultView.setAdapter(memberAdapter);
            //MemberSearchTask mtask = new MemberSearchTask(this);
            Log.v(TAG, "team search Activity.. excute 전");
            mtask.execute();
        }


    }

    protected void initList(String[] list, Map<String, Object> map, boolean[] checked) {
        list = new String[map.size()];
        checked = new boolean[list.length];
        int i = 0;
        for (Object s : map.values()) {
            list[i++] = (String) s;
        }
    }

    public void selectDialog(View v) {
        switch (v.getId()) {

            //지역
            case R.id.jregionSelectBtn:

                AlertDialog.Builder regionDialog = new AlertDialog.Builder(SearchActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                regionDialog.setTitle("지역을 선택하세요.");
                regionDialog.setMultiChoiceItems(regionList, regionChecked,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            }
                        });


                regionDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuffer sb = new StringBuffer();

                        for (int j = 0; j < regionChecked.length; j++) {
                            if (regionChecked[j]) {

                                sb.append(regionList[j] + ", "); //체크된 지역
                            }
                        }
                        regionSelected.setText(sb);
                    }
                });
                regionDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                regionDialog.show();

                break;

//역할
            case R.id.jroleSelectBtn:
                AlertDialog.Builder roleDialog = new AlertDialog.Builder(SearchActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                roleDialog.setTitle("역할을 선택하세요.");
                roleDialog.setMultiChoiceItems(roleList, roleChecked,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            }
                        });


                roleDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuffer sb = new StringBuffer();

                        for (int j = 0; j < roleChecked.length; j++) {
                            if (roleChecked[j]) {
                                sb.append(roleList[j] + ", "); //체크된 지역
                            }
                        }
                        roleSelected.setText(sb);
                    }
                });
                roleDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                roleDialog.show();

                break;


            //기술
            case R.id.jskillSelectBtn:
                AlertDialog.Builder skillDialog = new AlertDialog.Builder(SearchActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                skillDialog.setTitle("기술을 선택하세요.");
                skillDialog.setMultiChoiceItems(skillList, skillChecked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                    }
                });


                skillDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuffer sb = new StringBuffer();

                        for (int j = 0; j < skillChecked.length; j++) {
                            if (skillChecked[j]) {
                                sb.append(skillList[j] + ", "); //체크된 지역
                            }
                        }
                        skillSelected.setText(sb);
                    }
                });
                skillDialog.show();


                break;


            //분야
            case R.id.jcategorySelectBtn:
                AlertDialog.Builder categoryDialog = new AlertDialog.Builder(SearchActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                categoryDialog.setTitle("분야를 선택하세요.");
                categoryDialog.setMultiChoiceItems(categoryList, categoryChecked,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            }
                        });


                categoryDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuffer sb = new StringBuffer("");

                        for (int j = 0; j < categoryChecked.length; j++) {
                            if (categoryChecked[j]) {
                                sb.append(categoryList[j] + ", "); //체크된 지역
                            }
                        }
                        categorySelected.setText(sb);
                    }
                });
                categoryDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                categoryDialog.show();
                break;
        }

    }
}
