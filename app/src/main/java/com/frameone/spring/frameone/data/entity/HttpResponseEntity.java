package com.frameone.spring.frameone.data.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/19.
 * 网络返回数据实体
 */

public class HttpResponseEntity<T> implements Serializable {

    private int status;
    private String info;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
