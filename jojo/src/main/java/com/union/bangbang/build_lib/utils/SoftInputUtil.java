package com.union.bangbang.build_lib.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * author pisa
 * date  2019/8/21
 * version 1.0
 * effect : 软键盘辅助器
 */
public class SoftInputUtil {
    /**
     * 强制收起虚拟键盘
     */
    public static Boolean hideInputMethod(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return false;
    }

    /**
     * 强制收起虚拟键盘
     */
    public static Boolean hideInputMethod(View v) {
        return hideInputMethod(v.getContext(), v);
    }

    /**
     * 强制打开虚拟键盘
     */
    public static Boolean toggleInputMethod(View v) {
        return toggleInputMethod(v.getContext(), v);
    }

    /**
     * 强制打开虚拟键盘
     */
    public static Boolean toggleInputMethod(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
        }
        return false;
//        InputMethodManager imm = (InputMethodManager) context
//                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (imm != null) {
//            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//        }
    }
}
