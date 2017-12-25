package com.frameone.spring.frameone.start;

import android.util.Log;

import com.frameone.spring.frameone.base.BasePresenter;
import com.frameone.spring.frameone.data.manager.StartManager;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/25.
 * 启动业务逻辑处理
 */

public class StartPresenter implements BasePresenter {

    private StartManager startManager;
    private StartView startView;

    public StartPresenter(StartView startView) {
        this.startView = startView;
        startManager = StartManager.getInstance();
    }

    @Override
    public void start() {

    }

    public void getAds(String location_id, String dev_type) {
        startManager.getAds(location_id, dev_type)
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.e("data", o.toString());
                    }
                });
    }
}
