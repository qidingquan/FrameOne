package com.frameone.spring.frameone.data.repository;

import com.frameone.spring.frameone.data.DataRepository;
import com.frameone.spring.frameone.data.RetrofitFactory;
import com.frameone.spring.frameone.data.service.StartService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/25.
 * 启动页面数据操作
 */
public class StartRepository extends DataRepository {

    private static StartRepository instance;
    private StartService startService;

    public static StartRepository getInstance() {
        if (instance == null) {
            synchronized (StartRepository.class) {
                if (instance == null) {
                    instance = new StartRepository();
                }
            }
        }
        return instance;
    }

    private StartRepository() {
        startService = RetrofitFactory.getInstance().getService(StartService.class);
    }

    public Observable<Object> getAds(String location_id, String dev_type) {
        return transform(startService.getAds(location_id, dev_type));
    }
}