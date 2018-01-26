package com.frameone.spring.frameone.data;

import com.frameone.spring.frameone.util.MD5Util;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2018/1/5.
 * 添加统一参数
 */

public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String userToken = "";
        String phone = "18380473976";
        String signature = "";
        signature = "username=" + phone + "&token=" + userToken + "&key=hjWeiKLnJKBjkbnDAvidjkbJ2#$W%HJknJKLc";
        signature = MD5Util.MD5(signature);//md5加密

        //添加请求参数
        HttpUrl url = original.url().newBuilder()
                .addQueryParameter("username", phone)
                .addQueryParameter("token", userToken)
                .addQueryParameter("app_signture", signature)
                .build();

        //添加请求头
        Request request = original.newBuilder()
//                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                .addHeader("Connection", "keep-alive")
                .method(original.method(), original.body())
                .url(url)
                .build();

        return chain.proceed(request);
    }
}
