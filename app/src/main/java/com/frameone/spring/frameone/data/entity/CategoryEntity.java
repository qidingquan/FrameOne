package com.frameone.spring.frameone.data.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/10.
 * 标签分类
 */

public class CategoryEntity implements Serializable {

    private String name;//名称
    private int localImgRes;//本地资源

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocalImgRes() {
        return localImgRes;
    }

    public void setLocalImgRes(int localImgRes) {
        this.localImgRes = localImgRes;
    }
}
