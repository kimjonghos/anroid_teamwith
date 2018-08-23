package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileEditActivity extends Activity {
    final String[] roleList = {"개발자", "기획자", "디자이너", "기타"};
    final String[] regionList = {"서울", "경기", "부산", "제주"};
    final boolean[] checked = {true, false, true, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

/*
        final TextView role = findViewById(R.id.memberRoleTv);
        final TextView region = findViewById(R.id.memberRegionTv);



        //역할
        LinearLayout roleLayout = findViewById(R.id.roleLayout);

        roleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileEditActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                dialog.setTitle("역할을 선택하세요.");
                dialog.setItems(roleList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        role.setText(roleList[i] + "  >");
                    }
                });
                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "확인 클릭", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "취소 클릭", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }

        });

        //지역
        LinearLayout regionLayout = findViewById(R.id.regionLayout);

        regionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileEditActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                dialog.setTitle("지역은 2개까지 선택할 수 있습니다.");
                dialog.setMultiChoiceItems(regionList, checked,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                int cnt = 0;
                                for (int j = 0; j < checked.length; j++) {
                                    if (checked[j]) {
                                        cnt++;

                                        if (cnt > 2) {
                                            checked[i] = false;
                                            Toast.makeText(getApplicationContext(),
                                                    "2개까지 선택할 수 있습니다.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                            }
                        });

                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int cnt = 0;
                        StringBuffer regionStr = new StringBuffer();

                        for (int j = 0; j < checked.length; j++) {
                            if (checked[j]) {
                                cnt++;
                                if (cnt == 2) {
                                    regionStr.append(", " + regionList[j]); //체크된 지역
                                    break;
                                }
                                regionStr.append(regionList[j]); //체크된 지역
                            }
                        }
                        region.setText(regionStr + "  >");
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();

            }
        });


        //자기소개
        LinearLayout introLayout = findViewById(R.id.memberIntroLayout);

        introLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileEditActivity.this, MemberIntroActivity.class);

                startActivity(intent);
            }
        });
*/
    }
}


