package org.citra.citra_android.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class PermissionsHandler {
    public static final int REQUEST_CODE_WRITE_PERMISSION = 500;

    // We use permissions acceptance as an indicator if this is a first boot for the user.
    public static boolean isFirstBoot(final FragmentActivity activity) {
        return ContextCompat.checkSelfPermission(activity, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkWritePermission(final FragmentActivity activity) {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        if (isFirstBoot(activity)) {
            activity.requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_WRITE_PERMISSION);
            return false;
        }

        return true;
    }

    public static boolean hasWriteAccess(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasWritePermission = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
            return hasWritePermission == PackageManager.PERMISSION_GRANTED;
        }

        return true;
    }
}
