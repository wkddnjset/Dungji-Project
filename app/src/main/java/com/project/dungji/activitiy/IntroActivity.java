package com.project.dungji.activitiy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import java.util.ArrayList;

import com.project.dungji.R;
import com.project.dungji.utility.PermissionUtil;

public class IntroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPermission();
    }

    private void checkPermission() {

        ArrayList<String> deniedList = PermissionUtil.checkPermisiion(IntroActivity.this);

        if (deniedList.size() == 0) {
            startActivity(new Intent(IntroActivity.this, MainActivity.class));
            finish();
        } else {
            PermissionUtil.checkRequestPermission(IntroActivity.this, PermissionUtil.REQUEST_DUNGJI_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        int status = 0;
        int i = 0;

        for (String permission : permissions) {
            if (permission.equals("android.permission.ACCESS_FINE_LOCATION")) {
                if (grantResults[i] == -1) {
                    status -= 1;
                }
            }
            i++;
        }

        // 0 이 아닐경우 한가지라도 denied
        if (status < 0) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
            startActivity(intent);
            Toast.makeText(this, "서비스 이용을 위해 접근을 허용해 주세요.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            if (isTaskRoot()) {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_intro;
    }
}
