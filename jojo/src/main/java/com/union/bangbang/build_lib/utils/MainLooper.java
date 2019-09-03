package com.union.bangbang.build_lib.utils;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

/**
 * author pisa
 * date  2019/8/24
 * version 1.0
 * effect :
 */
public class MainLooper extends Handler {
    private volatile static MainLooper instance;

    public MainLooper(@NonNull Looper looper) {
        super(looper);
    }

    public static MainLooper getInstance() {
        if (instance == null) {
            synchronized (MainLooper.class) {
                if (instance == null) {
                    instance = new MainLooper(Looper.getMainLooper());
                }
            }
        }
        return instance;
    }
    public static void runUiThread(Runnable task){
        getInstance().post(task);
    }
}
