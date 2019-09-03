package com.union.bangbang.build_lib.net.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:04
 * modify developer：  admin
 * modify time：15:04
 * modify remark：
 *
 * @version 2.0
 */


public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    /**
     * 转换成请求体，如果有自定义的转化方式，则去调用；
     *
     * @param value
     * @return
     * @throws IOException
     */
    @Override
    public RequestBody convert(T value) throws IOException {
        if (value instanceof RequestConvert) {
            return ((RequestConvert) value).convert(value);
        } else {
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
            JsonWriter jsonWriter = gson.newJsonWriter(writer);
            adapter.write(jsonWriter, value);
            jsonWriter.close();
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        }
    }
}
