package com.union.bangbang.build_lib.net.converter;

import android.text.TextUtils;

import java.lang.reflect.Field;

import okhttp3.FormBody;
import okhttp3.RequestBody;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author pisa
 * @version 1.0
 * @name Android
 * @date 2019-08-11 10:28
 * @effect :  application/x-www-form-urlencoded
 */
public class FormConvertHelper implements RequestConvert {
    private volatile static FormConvertHelper mInstance;

    private FormConvertHelper() {
    }

    public static FormConvertHelper getInstance() {
        if (mInstance == null) mInstance = new FormConvertHelper();
        return mInstance;
    }

    @Override
    public <T> RequestBody convert(T t) {
        FormBody.Builder builder = new FormBody.Builder(UTF_8);
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            if ("serialVersionUID".equals(name)) continue;
            if ("$change".equals(name)) continue;
            try {
                Object o = field.get(t);
                if (o==null)continue;
                String s = String.valueOf(field.get(t));
                if (TextUtils.isEmpty(s)) continue;
                builder.addEncoded(name, s);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return builder.build();
    }
}
