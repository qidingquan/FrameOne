package com.frameone.spring.frameone.data.manager;

import com.frameone.spring.frameone.data.RetrofitFactory;
import com.frameone.spring.frameone.data.service.StartService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/25.
 * 启动页面数据操作
 */
public class StartManager {

    private static StartManager instance;
    private StartService startService;

    public static StartManager getInstance() {
        if (instance == null) {
            synchronized (StartManager.class) {
                if (instance == null) {
                    instance = new StartManager();
                }
            }
        }
        return instance;
    }

    private StartManager() {
        startService = RetrofitFactory.getInstance().getService(StartService.class);
    }

    public Observable<Object> getAds(String location_id, String dev_type) {
        return startService.getAds(location_id, dev_type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}