package com.frameone.spring.frameone.data;

import android.support.annotation.NonNull;

import com.frameone.spring.frameone.data.annotation.HttpResponseStatus;
import com.frameone.spring.frameone.data.entity.HttpResponseEntity;
import com.frameone.spring.frameone.data.exception.HttpFailException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/26.
 */

public class DataRepository {

    public DataRepository() {

    }

    protected <T> Observable<T> transform(@NonNull Observable<HttpResponseEntity<T>> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<HttpResponseEntity<T>, Observable<T>>() {//实体转换 返回data数据实体
                    @Override
                    public Observable<T> call(HttpResponseEntity<T> responseEntity) {
                        if (responseEntity != null) {//返回有数据
                            if (HttpResponseStatus.SUCCESS == responseEntity.getStatus()) {
                                //成功
                                return Observable.just(responseEntity.getData());
                            } else if (HttpResponseStatus.FAIL == responseEntity.getStatus()) {
                                //失败
                                return Observable.error(new HttpFailException(responseEntity.getInfo()));
                            } else {//其他异常状态
                                return Observable.error(new HttpFailException(responseEntity.getStatus(), responseEntity.getInfo()));
                            }
                        }
                        return Observable.error(new HttpFailException("服务器无响应"));
                    }
                });
    }
}
