package com.frameone.spring.frameone.base;

import android.app.Application;

/**
 * Created by Administrator on 2017/12/28.
 *
 */

public class BaseApplication extends Application {

    public static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }
}
