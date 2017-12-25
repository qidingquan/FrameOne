package com.frameone.spring.frameone.data;

import com.frameone.spring.frameone.data.entity.Constant;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/12/25.
 */

public class RetrofitFactory {

    private static RetrofitFactory instance;
    private OkHttpClient okHttpClient;
    private GsonConverterFactory converterFactory;
    private Retrofit retrofit;

    private RetrofitFactory() {

        okHttpClient = new OkHttpClient();
        converterFactory = GsonConverterFactory.create(new GsonBuilder().create());

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持rxJava
                .build();
    }

    public static RetrofitFactory getInstance() {

        if (instance == null) {
            synchronized (RetrofitFactory.class) {
                if (instance == null) {
                    instance = new RetrofitFactory();
                }
            }
        }
        return instance;
    }

    public  <T> T getService(Class<T> tClass) {
        return retrofit.create(tClass);
    }
}
