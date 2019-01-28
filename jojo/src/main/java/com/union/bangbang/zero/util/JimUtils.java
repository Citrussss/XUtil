package com.union.bangbang.zero.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class JimUtils {

    private final static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd", new Locale("cn"));

    public static String getRightTime(String time) {
        try {
            return dateformat.format(dateformat.parse(time).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String getStringTime(long currentTimeMillis) {
        return dateformat.format(currentTimeMillis);
    }

    public static String getNowTime() {
        return getStringTime(System.currentTimeMillis());
    }

    public static boolean checkPermission(Activity activity, String... permissions) {
        if (activity == null) {
            return false;
        }
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void checkPermission(FragmentActivity activity, Consumer<Boolean> onNext, String... permissions) {
        if (checkPermission(activity, permissions)) {
            try {
                onNext.accept(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Disposable disposable = new RxPermissions(activity)
                    .request(permissions)
                    .subscribe(onNext, Throwable::printStackTrace);
        }
    }
}
