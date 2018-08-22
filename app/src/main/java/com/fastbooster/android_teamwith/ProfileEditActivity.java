package com.fastbooster.android_teamwith;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class ProfileEditActivity extends Activity {
    final String[] menu = {"개발자", "기획자", "디자이너", "기타"};
    final boolean[] checked = {true, false, true, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        LinearLayout introLayout = findViewById(R.id.memberIntroLayout);

        introLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileEditActivity.this, MemberIntroActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout regionLayout = findViewById(R.id.regionLayout);

        regionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinner = null;
                String[] data = getResources().getStringArray(R.array.region_list);
                spinner = findViewById(R.id.spinner);
                spinner.performClick();
                ArrayAdapter<String> adapter
                        = new ArrayAdapter<String>(ProfileEditActivity.this, android.R.layout.simple_spinner_dropdown_item, data);
                spinner.setAdapter(adapter);
            }
        });

        LinearLayout roleLayout = findViewById(R.id.roleLayout);

        roleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileEditActivity.this);
                dialog.setTitle("Title!!");
                dialog.setItems(menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ProfileEditActivity.this, menu[i] + "가 선택되었다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setMessage("헤헤");
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

        introLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileEditActivity.this, MemberIntroActivity.class);
                startActivity(intent);
            }
        });
    }
}


