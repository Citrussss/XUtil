package com.union.bangbang.build_lib.net.converter;

import android.text.TextUtils;

import java.lang.reflect.Field;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author pisa
 * @version 1.0
 * @name Android
 * @date 2019-08-11 10:28
 * @effect :  multipart/form-data
 */
public class MultipartConvertHelper implements RequestConvert {
    private volatile static MultipartConvertHelper mInstance;

    private MultipartConvertHelper() {
    }

    public static MultipartConvertHelper getInstance() {
        if (mInstance == null) mInstance = new MultipartConvertHelper();
        return mInstance;
    }

    @Override
    public <T> RequestBody convert(T t) {
        MultipartBody.Builder builder = new MultipartBody.Builder(UTF_8.toString());
//        FormBody.Builder builder = new FormBody.Builder(UTF_8);
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            if ("serialVersionUID".equals(name)) continue;
            if ("$change".equals(name)) continue;
            try {
                Object o = field.get(t);
                if (o == null) continue;
                String s = String.valueOf(field.get(t));
                if (TextUtils.isEmpty(s)) continue;
                builder.addFormDataPart(name, s);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return builder.build();
    }
}
