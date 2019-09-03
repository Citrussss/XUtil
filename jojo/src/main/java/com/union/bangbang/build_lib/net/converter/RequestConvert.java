package com.union.bangbang.build_lib.net.converter;

import okhttp3.RequestBody;

/**
 * @author pisa
 * @version 1.0
 * @name Android
 * @date 2019-08-11 10:26
 * @effect : 自定义请求体
 */
public interface RequestConvert {
    <T> RequestBody convert(T t);
}
