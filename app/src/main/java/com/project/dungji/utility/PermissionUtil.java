package com.project.dungji.utility;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

public class PermissionUtil {
    public final static int REQUEST_DUNGJI_PERMISSIONS = 101;

    // ACCESS_FINE_LOCATION - 위치
    public static String[] PERMISSIONS_LIST = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };


    //buildTarget 23 미만
    public static ArrayList<String> checkPermisiion(Activity activity) {

        ArrayList<String> deniedList = new ArrayList<String>();
        deniedList.clear();
        for (String permission : PERMISSIONS_LIST) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                deniedList.add(permission);
            }
        }

        return deniedList;

    }


    /**
     * Permission check.
     */
    @SuppressLint("NewApi")
    public static boolean checkRequestPermission(final Activity activity, final int requestCode) {


        ArrayList<String> deniedList = new ArrayList<String>();
        deniedList.clear();

        String[] permissions = null;
        if (requestCode == REQUEST_DUNGJI_PERMISSIONS)
            permissions = PERMISSIONS_LIST;


        for (String permission : permissions) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                deniedList.add(permission);
            }
        }

        if (deniedList.size() > 0) {
            final String[] deniedPermissions = deniedList.toArray(new String[deniedList.size()]);
            activity.requestPermissions(deniedPermissions, requestCode);
            return true;
        } else {
            return false;
        }
    }


    //build target 23부터 적용.
    @SuppressLint("NewApi")
    public static ArrayList<String> getDeniedListOnlyEssence(final Activity activity) {
        ArrayList<String> deniedList = new ArrayList<String>();
        deniedList.clear();

        for (String permission : PERMISSIONS_LIST) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                deniedList.add(permission);
            }
        }

        return deniedList;
    }

    public static String getDeniedMessage(final Activity activity, String[] deniedList, boolean isAll) {

        boolean isDeniedLocation = false;

        String message = "";

        for (String denied : deniedList) {

            if (denied.equalsIgnoreCase(Manifest.permission.ACCESS_FINE_LOCATION)) {
                isDeniedLocation = true;
            }
        }

        if (isDeniedLocation) {
            message += "Deniend Location";
        }

        message += "\n";

        return message;
    }
}
