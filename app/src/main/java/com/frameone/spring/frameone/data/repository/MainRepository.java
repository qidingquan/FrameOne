package com.frameone.spring.frameone.data.repository;

import com.frameone.spring.frameone.data.DataRepository;
import com.frameone.spring.frameone.data.RetrofitFactory;
import com.frameone.spring.frameone.data.service.MainService;

import rx.Observable;

/**
 * Created by Administrator on 2018/1/5.
 */

public class MainRepository extends DataRepository {

    private static final MainRepository ourInstance = new MainRepository();

    public static MainRepository getInstance() {
        return ourInstance;
    }

    private MainService mainService;

    private MainRepository() {
        mainService = RetrofitFactory.getInstance().getService(MainService.class);
    }

    //检查租赁状态
    public Observable<Object> leaseStatus() {
        return transform(mainService.leaseStatus());
    }

    //获取历史轨迹
    public Observable<Object> historyTrack(String date) {
        return transform(mainService.historyTrack(date));
    }
}
