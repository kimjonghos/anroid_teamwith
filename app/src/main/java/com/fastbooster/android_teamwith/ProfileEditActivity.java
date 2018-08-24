package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fastbooster.android_teamwith.share.ApplicationShare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfileEditActivity extends Activity {
    String[] roleKeyList; //역할의 키 값 목록
    String[] regionKeyList; //지역의 키값 목록

    String[] roleList; //역할의 이름 목록, 다이얼로그에서 보여줄 용도
    String[] regionList; //직역의 이름 목록, 다이얼로그에서 보여줄 용도

    boolean[] roleChecked; //사용자가 선택시 체크할 배열
    boolean[] regionChecked; //사용자가 선택시 체크할 배열

    TextView roleSelected; //사용자가 선택한 역할 화면에 보여줌
    TextView regionSelected;//사용자가 선택한 지역 화면에 보여줌
    TextView profileEdit; //사용자가 입력한 정보를 저장

    String roleSelectedKey; //사용자가 선택한 역할
    String[] regionSelectedKey; //사용자가 선택한 지역

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile_edit);

        roleSelected = findViewById(R.id.memberRoleTv);
        regionSelected = findViewById(R.id.memberRegionTv);
        profileEdit = findViewById(R.id.jprofileEditBtn);


        //저장하기 버튼 눌렀을 때
        profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                class ProfileEditThread extends Thread {
                    static final String TAG = "file data...";
                    private String URL_STR = "http://192.168.30.7:8089/api/member/getEditInfo/";
                    SharedPreferences sp = getSharedPreferences("memberPref", MODE_PRIVATE);

                    public void run() {
                        try {

                            //shared preference에서 내 아이디 빼서 요청 주소 뒤에 붙임
                            URL url = new URL(URL_STR);
                            Log.v(TAG, url.toString());
                            HttpURLConnection conn = null;
                            StringBuilder sb = new StringBuilder();

                            conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestProperty("Cookie", sp.getString("sessionId", ""));
//connection.
                            conn.setRequestMethod("GET");
                            conn.setDoInput(true);
                            conn.setConnectTimeout(1000);
                            int responseCode = conn.getResponseCode();
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                String line = null;
                                while ((line = br.readLine()) != null) {
                                    sb.append(line);
                                }
                            } else {
                                Log.d("Teamwith app error", "URL=" + URL_STR);
                            }

                            Log.v("pref", sp.getString("memberId", "jo"));
                            Log.v("pref", sp.getString("memberName", "jo"));
                            Log.v("pref", sp.getString("memberPic", "jo"));
                            Log.v("pref", sp.getString("memberAuth", "jo"));

                        } catch (Exception e) {

                        }
                    }
                }

                ProfileEditThread pet = new ProfileEditThread();
                pet.start();
            }
        });
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
                        roleSelected.setText(roleList[i] + "  >");
                        roleSelectedKey = roleKeyList[i];
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

                final AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileEditActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                dialog.setTitle("지역은 2개까지 선택할 수 있습니다.");
                dialog.setMultiChoiceItems(regionList, regionChecked,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                int cnt = 0;
                                for (int j = 0; j < regionChecked.length; j++) {
                                    if (regionChecked[j]) {
                                        cnt++;

                                        if (cnt > 2) {
                                            ((AlertDialog) dialogInterface).getListView().
                                                    setItemChecked(i, false);
                                            regionChecked[i] = false;
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

                        for (int j = 0; j < regionChecked.length; j++) {
                            if (regionChecked[j]) {
                                cnt++;
                                if (cnt == 2) {
                                    regionStr.append(", " + regionList[j]); //체크된 지역
                                    break;
                                }
                                regionStr.append(regionList[j]); //체크된 지역
                            }
                        }
                        regionSelected.setText(regionStr + "  >");
                        //사용자가 선택한 지역
                        regionSelectedKey = new String[2];
                        for (int j = 0; j < regionChecked.length; j++) {
                            if (regionChecked[i]) {
                                if (regionSelectedKey[0] == null) {
                                    regionSelectedKey[0] = regionKeyList[i];
                                } else {
                                    regionSelectedKey[1] = regionKeyList[i];
                                }
                            }
                        }
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


    }
}


