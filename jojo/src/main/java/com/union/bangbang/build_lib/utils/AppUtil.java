package com.union.bangbang.build_lib.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Stack;

/**
 * @author pisa
 * @version 1.0
 * @name Android
 * @date 2019-08-07 21:11
 * @effect :
 */
public class AppUtil implements Application.ActivityLifecycleCallbacks {
    private Application mApplication;
    private static AppUtil mInstance;
    private Stack<Activity> mActivityStack;

    public static void init(Application mApplication) {
        mInstance = new AppUtil();
        mInstance.mApplication = mApplication;
        mInstance.mActivityStack = new Stack<>();
        mApplication.registerActivityLifecycleCallbacks(mInstance);
    }

    public static AppUtil getInstance() {
        if (mInstance == null) throw new RuntimeException("did you forget run fun init()?");
        return mInstance;
    }

    public Application getApplication() {
        return mApplication;
    }

    /**
     * 设置百分比宽度
     *
     * @param percent
     * @param view
     */
    public static void setPercentageWidth(double percent, View view) {
        ViewGroup.LayoutParams mLayoutParams = view.getLayoutParams();
        mLayoutParams.width = (int) (percent * getInstance().getApplication().getResources().getDisplayMetrics().widthPixels);
        view.setLayoutParams(mLayoutParams);
    }

    /**
     * dp转px
     *
     * @param dpValue
     * @return
     */
    public static int dip2px(float dpValue) {
        final float scale = getInstance().getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        mActivityStack.push(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        mActivityStack.remove(activity);
    }

    /**
     * 获取当前最顶上的activity
     *
     * @return
     */
    public static Activity peekActivity() {
        return getInstance().mActivityStack.peek();
    }

    public static Stack<Activity> getActivists(){
        return getInstance().mActivityStack;
    }


}
