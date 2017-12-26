package com.frameone.spring.frameone.data.service;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/12/25.
 * 启动数据接口
 */

public interface StartService {
    /**
     * 获取启动广告
     * @param location_id
     * @param dev_type
     * @return
     */
    @GET("Ads/main")
    Observable<String> getAds(
            @Query("location_id") String location_id,
            @Query("dev_type") String dev_type);
}
