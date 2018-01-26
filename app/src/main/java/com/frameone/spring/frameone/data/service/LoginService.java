package com.frameone.spring.frameone.data.service;

import com.frameone.spring.frameone.data.entity.HttpResponseEntity;
import com.frameone.spring.frameone.data.entity.UserEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/1/5.
 */

public interface LoginService {

    @POST("User/login")
    @FormUrlEncoded
    Observable<HttpResponseEntity<UserEntity>> login(
            @Field("username") String username,
            @Field("code") String code,
            @Field("mac") String mac,
            @Field("token") String token);
}
