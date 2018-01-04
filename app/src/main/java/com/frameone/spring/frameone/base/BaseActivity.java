package com.frameone.spring.frameone.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/12/22.
 * activity基类
 */

public class BaseActivity extends AppCompatActivity {

    private Unbinder unBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        unBinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        if (unBinder != null) {
            unBinder.unbind();
        }
        super.onDestroy();
    }
}
