package com.frameone.spring.frameone.data.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/12.
 * 用户相关数据
 */

public class UserEntity implements Serializable {

    private String phone;//手机号
    private String username;//用户名
    private String token;//登录token

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
