package com.frameone.spring.frameone.data.annotation;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

/**
 * Created by Administrator on 2017/12/28.
 * 请求网络接口返回状态
 */
@StringDef({HttpResponseStatus.SUCCESS, HttpResponseStatus.FAIL})
public @interface HttpResponseStatus {
    String SUCCESS = "1";//成功
    String FAIL = "0";//失败
}
