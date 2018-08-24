package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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

public class SearchActivity extends Activity {
    static final String TAG = "member data...";
    ApplicationShare as;

    String[] roleList;
    String[] regionList;
    String[] skillList;
    String[] categoryList;

    boolean[] roleChecked;
    boolean[] regionChecked;
    boolean[] skillChecked;
    boolean[] categoryChecked;

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

        //다이얼로그에 나올 선택 목록 리스트 초기화
        InitTask initTask = new InitTask();
        initTask.execute();

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
                mtask.execute(new Criteria(1, 10));
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
            mtask.execute(new Criteria(1, 10));
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

    class InitTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            as = (ApplicationShare) getApplication();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.v("role list", ApplicationShare.roleList.size() + "");
            roleList = new String[ApplicationShare.roleList.size()];
            roleChecked = new boolean[roleList.length];
            int i = 0;
            for (Object s : ApplicationShare.roleList.values()) {
                roleList[i++] = (String) s;
            }
            regionList = new String[ApplicationShare.regionList.size()];
            regionChecked = new boolean[regionList.length];
            i = 0;
            for (Object s : ApplicationShare.regionList.values()) {
                regionList[i++] = (String) s;
            }
            skillList = new String[ApplicationShare.skillList.size()];
            skillChecked = new boolean[skillList.length];
            i = 0;
            for (Object s : ApplicationShare.skillList.values()) {
                skillList[i++] = (String) s;
            }
            categoryList = new String[ApplicationShare.categoryList.size()];
            categoryChecked = new boolean[categoryList.length];
            i = 0;
            for (Object s : ApplicationShare.categoryList.values()) {
                categoryList[i++] = (String) s;
            }
        }

    }
}
