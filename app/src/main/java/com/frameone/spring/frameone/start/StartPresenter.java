package com.frameone.spring.frameone.start;

import android.util.Log;

import com.frameone.spring.frameone.base.BasePresenter;
import com.frameone.spring.frameone.data.entity.HttpResponseEntity;
import com.frameone.spring.frameone.data.repository.StartRepository;
import com.frameone.spring.frameone.util.ToastUtil;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/12/25.
 * 启动业务逻辑处理
 */

public class StartPresenter implements StartContract.Presenter {

    private StartRepository startManager;
    private StartContract.View startView;

    public StartPresenter(StartContract.View startView) {
        this.startView = startView;
        startManager = StartRepository.getInstance();
    }

    @Override
    public void start() {

    }

    @Override
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
                    public void onNext(Object s) {
                        ToastUtil.show(s.toString());
                    }
                });
    }
}
