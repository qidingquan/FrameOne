package com.frameone.spring.frameone.main;

import android.util.Log;

import com.frameone.spring.frameone.data.repository.MainRepository;
import com.frameone.spring.frameone.util.DateTimeHelper;
import com.frameone.spring.frameone.util.ToastUtil;

import java.util.Date;

import rx.Subscriber;

/**
 * Created by Administrator on 2018/1/5.
 */

public class MainPresenter implements MainContract.Presenter {


    @Override
    public void leaseStatus() {

        MainRepository.getInstance()
                .leaseStatus()
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        ToastUtil.show(e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.e("data", o.toString());
                    }
                });
    }

    @Override
    public void historyTrack() {
        String date = DateTimeHelper.formatDateTimetoString(new Date(), "yyyy-MM-dd");
        MainRepository.getInstance().historyTrack(date).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.show(e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                ToastUtil.show(o.toString());

            }
        });
    }

    @Override
    public void start() {

    }
}
