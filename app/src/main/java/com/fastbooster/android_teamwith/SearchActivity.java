package com.fastbooster.android_teamwith;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fastbooster.android_teamwith.share.ApplicationShare;
import com.fastbooster.android_teamwith.task.MemberSearchTask;
import com.fastbooster.android_teamwith.task.PortfolioSearchTask;
import com.fastbooster.android_teamwith.task.TeamSearchTask;
import com.fastbooster.android_teamwith.util.Criteria;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BarActivity {
    static final String TAG = "member data...";
    ApplicationShare as;

    String[] roleKeyList;
    String[] regionKeyList;
    String[] skillKeyList;
    String[] categoryKeyList;

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

        //팀 검색인지 회원 검색인지 포트폴리오 검색인지 체크
        Intent intent = getIntent();
        final String kind = intent.getStringExtra("kind");

        if (kind.equals("portfolio")) {
            setContentView(R.layout.activity_portfolio_search);
            back = findViewById(R.id.jbackToSearch);
            keyword = findViewById(R.id.jKeyword);
            searchBtn = findViewById(R.id.jpSearchBtn);

            categorySelected = findViewById(R.id.categorySelected);

            selectedView = findViewById(R.id.jselectedView);

            resultView = findViewById(R.id.resultView);
            PortfolioSearchTask ptask = new PortfolioSearchTask(this);
            ptask.execute(new Criteria(1, 10), null, null);

        } else {
            setContentView(R.layout.activity_search);

            back = findViewById(R.id.jbackToSearchbtn);
            keyword = findViewById(R.id.jkeyword);
            searchBtn = findViewById(R.id.jsearchBtn);

            regionSelected = findViewById(R.id.jregionSelected);
            roleSelected = findViewById(R.id.jroleSelected);
            skillSelected = findViewById(R.id.jskillSelected);
            categorySelected = findViewById(R.id.jcategorySelected);

            selectedView = findViewById(R.id.jselectedView);

            resultView = findViewById(R.id.resultView);

            if (kind.equals("team")) {
                TeamSearchTask ttask = new TeamSearchTask(this);
                ttask.execute(new Criteria(1, 10), null, null, null, null, null);
            } else if (kind.equals("member")) {
                MemberSearchTask mtask = new MemberSearchTask(this);
                mtask.execute(new Criteria(1, 10), null, null, null, null, null);
            }
        }

        //다이얼로그에 나올 선택 목록 리스트 초기화
        InitTask initTask = new InitTask();
        initTask.execute();

/*
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"back",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
*/


        //검색 버튼 클릭 시
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = keyword.getText().toString();

                //사용자가 선택한 조건 값 저장하기
                List<String> regionSelectedList = new ArrayList<>();
                for (int i = 0; i < regionChecked.length; i++) {
                    if (regionChecked[i]) {
                        regionSelectedList.add(regionKeyList[i]);
                    }
                }
                List<String> roleSelectedList = new ArrayList<>();
                for (int i = 0; i < roleChecked.length; i++) {
                    if (roleChecked[i]) {
                        roleSelectedList.add(roleKeyList[i]);
                    }
                }
                List<String> skillSelectedList = new ArrayList<>();
                for (int i = 0; i < skillChecked.length; i++) {
                    if (skillChecked[i]) {
                        skillSelectedList.add(skillKeyList[i]);
                    }
                }
                List<String> categorySelectedList = new ArrayList<>();
                for (int i = 0; i < categoryChecked.length; i++) {
                    if (categoryChecked[i]) {
                        categorySelectedList.add(categoryKeyList[i]);
                    }
                }

                //팀 검색인지 회원 검색인지 체크
                if (kind.equals("team")) {
                    TeamSearchTask ttask = new TeamSearchTask(SearchActivity.this);
                    ttask.execute(new Criteria(1, 10), regionSelectedList,
                            categorySelectedList, roleSelectedList, skillSelectedList, key);
                } else if (kind.equals("member")) {
                    MemberSearchTask mtask = new MemberSearchTask(SearchActivity.this);
                    mtask.execute(new Criteria(1, 10), regionSelectedList,
                            categorySelectedList, roleSelectedList, skillSelectedList, key);
                } else if (kind.equals("portfolio")) {
                    PortfolioSearchTask ptask = new PortfolioSearchTask(SearchActivity.this);
                    ptask.execute(new Criteria(1, 10), categorySelectedList, key);
                }
            }
        });


    }


    //조건 선택 다이얼로그
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
                                // ApplicationShare.regionList.
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
            case R.id.categorySelectBtn:
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
                                sb.append(categoryList[j] + ", "); //체크된 분야
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
            int i = 0;

            regionList = new String[ApplicationShare.regionList.size()];
            regionKeyList = new String[ApplicationShare.regionList.size()];

            regionChecked = new boolean[regionList.length];
            for (Object s : ApplicationShare.regionList.keySet()) {
                String k = (String) s;
                regionList[i] = (String) ApplicationShare.regionList.get(k);
                regionKeyList[i++] = k;
            }

            roleList = new String[ApplicationShare.roleList.size()];
            roleKeyList = new String[ApplicationShare.roleList.size()];
            roleChecked = new boolean[roleList.length];
            i = 0;
            for (Object s : ApplicationShare.roleList.keySet()) {
                String k = (String) s;
                roleList[i] = (String) ApplicationShare.roleList.get(k);
                roleKeyList[i++] = k;
            }

            categoryList = new String[ApplicationShare.categoryList.size()];
            categoryKeyList = new String[ApplicationShare.categoryList.size()];
            categoryChecked = new boolean[categoryList.length];
            i = 0;
            for (Object s : ApplicationShare.categoryList.keySet()) {
                String k = (String) s;
                categoryList[i] = (String) ApplicationShare.categoryList.get(k);
                categoryKeyList[i++] = k;
            }

            skillList = new String[ApplicationShare.skillList.size()];
            skillKeyList = new String[ApplicationShare.skillList.size()];
            skillChecked = new boolean[skillList.length];
            i = 0;
            for (Object s : ApplicationShare.skillList.keySet()) {
                String k = (String) s;
                skillList[i] = ((String[]) ApplicationShare.skillList.get(k))[0];
                skillKeyList[i++] = k;
            }
        }

    }
}
