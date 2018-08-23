package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.fastbooster.android_teamwith.adapter.MemberAdapter;
import com.fastbooster.android_teamwith.model.MemberSearchVO;

import java.util.ArrayList;

public class TeamSearchActivity extends Activity {
    final String[] roleList = {"개발자", "기획자", "디자이너", "기타"};
    final String[] regionList = {"서울", "경기", "부산", "제주"};
    final String[] skillList = {"C", "C#", "C++", "Java"};
    final String[] categoryList = {"보안", "빅데이터", "IOT", "패션"};

    final boolean[] roleChecked = new boolean[roleList.length];
    final boolean[] regionChecked = new boolean[regionList.length];
    final boolean[] skillChecked = new boolean[skillList.length];
    final boolean[] categoryChecked = new boolean[categoryList.length];

    TextView back;
    EditText keyword;
    ImageButton searchBtn;

    TextView regionSelected;
    TextView roleSelected;
    TextView skillSelected;
    TextView categorySelected;

    HorizontalScrollView selectedView;

    ListView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_search);

        back = findViewById(R.id.jbackToSearchbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeamSearchActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        keyword = findViewById(R.id.jkeyword);
        searchBtn = findViewById(R.id.jsearchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String key = keyword.getText().toString();
            }
        });

        regionSelected = findViewById(R.id.jregionSelected);
        roleSelected = findViewById(R.id.jroleSelected);
        skillSelected = findViewById(R.id.jskillSelected);
        categorySelected = findViewById(R.id.jcategorySelected);

        selectedView = findViewById(R.id.jselectedView);
        ArrayList<MemberSearchVO> data = new ArrayList<MemberSearchVO>();
        MemberSearchVO m1 = new MemberSearchVO();
        m1.setMemberName("황규진");
        m1.setRoleId("개발자");
        MemberSearchVO m2 = new MemberSearchVO();
        m2.setMemberName("김종승");
        m2.setRoleId("기타");
        MemberSearchVO m3 = new MemberSearchVO();
        m3.setMemberName("조란");
        m3.setRoleId("기획자");
        MemberSearchVO m4 = new MemberSearchVO();
        m4.setMemberName("임경준");
        m4.setRoleId("디자이너");

        data.add(m1);
        data.add(m2);
        data.add(m3);
        data.add(m4);

        MemberAdapter memberAdapter = new MemberAdapter(this, data);

        resultView = findViewById(R.id.jresultView);

        Intent intent = getIntent();
        String kind = intent.getStringExtra("kind");
        if (kind.equals("team")) {
            // resultView.setAdapter(teamAdapter);
        } else if (kind.equals("member")) {
            resultView.setAdapter(memberAdapter);
        }


    }

    public void selectDialog(View v) {
        switch (v.getId()) {

            //지역
            case R.id.jregionSelectBtn:

                AlertDialog.Builder regionDialog = new AlertDialog.Builder(TeamSearchActivity.this,
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
                AlertDialog.Builder roleDialog = new AlertDialog.Builder(TeamSearchActivity.this,
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
                AlertDialog.Builder skillDialog = new AlertDialog.Builder(TeamSearchActivity.this,
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
                AlertDialog.Builder categoryDialog = new AlertDialog.Builder(TeamSearchActivity.this,
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
