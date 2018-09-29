package com.project.dungji.activitiy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.project.dungji.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);

        ConnectivityManager cm = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if(networkInfo == null || !networkInfo.isAvailable() || !networkInfo.isConnected()){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("LTE 또는 Wifi의 연결상태를 확인해주세요.");
            builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }else{

            if(CheckPermission(this)){

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        startActivity(new Intent(IntroActivity.this, MainActivity.class));
                        finish();
                    }
                }, 1500);

            }
        }
    }


    /**
     * 권한 있는지 없는지 체크하는거
     *
     * @param act
     * @return
     */
    public static Boolean CheckPermission(Activity act) {

        Boolean Permission = false;

        String[] PermissionCheckType = new String[1];
        PermissionCheckType[0] = android.Manifest.permission.ACCESS_FINE_LOCATION;


        // 권한이 없는 경우
        if (ActivityCompat.checkSelfPermission(act, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            // 최초 요청 및 다시보지않기는 fasle
            if (ActivityCompat.shouldShowRequestPermissionRationale(act, android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(act, PermissionCheckType, 0);
            } else { // 사용자가 거절한 경우

                ActivityCompat.requestPermissions(act, PermissionCheckType, 0);
            }


        } else {
            // 사용 권한이 있음을 확인한 경우
            Permission = true;
        }
        return Permission;
    }


    @SuppressLint("Override")
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case 0:
                // 권한이 없는 경우
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    //여기서 다시 보지 않기를 체크한다
                    //이쪽으로 들어오는 false는 다시보지 않기 체크로 인한 false

                    ActivityCompat.requestPermissions(this, permissions, 0);
                }else{


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            startActivity(new Intent(IntroActivity.this, MainActivity.class));
                            finish();
                        }
                    }, 1500);
                }
        }
    }

}
