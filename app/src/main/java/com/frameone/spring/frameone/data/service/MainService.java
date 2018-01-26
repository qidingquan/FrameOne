package com.frameone.spring.frameone.data.service;

import com.frameone.spring.frameone.data.entity.HttpResponseEntity;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/1/5.
 * 主页接口
 */

public interface MainService {
    /**
     * 检查租借状态
     *
     * @return
     */
    @POST("Main/leaseStatus")
    Observable<HttpResponseEntity<Object>> leaseStatus();

    /**
     * 获取历史轨迹
     *
     * @return
     */
    @POST("Main/historyTrack")
    Observable<HttpResponseEntity<Object>> historyTrack(
            @Query("date") String date
    );
}
