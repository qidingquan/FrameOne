package com.frameone.spring.frameone.login;

import android.text.TextUtils;
import android.util.Log;

import com.frameone.spring.frameone.data.entity.UserEntity;
import com.frameone.spring.frameone.data.repository.LoginRepository;
import com.frameone.spring.frameone.start.StartActivity;
import com.frameone.spring.frameone.util.DeviceUtil;
import com.frameone.spring.frameone.util.ToastUtil;

import rx.Subscriber;

/**
 * Created by Administrator on 2018/1/5.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void login() {
        String username = view.getPhone();
        String code = view.getCode();
        String mac = DeviceUtil.getDeviceMac();
        String token = "";
        if (TextUtils.isEmpty(username)) {
            ToastUtil.show("电话号码为空");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtil.show("验证码为空");
            return;
        }
        LoginRepository.getInstance()
                .login(username, code, mac, token)
                .subscribe(new Subscriber<UserEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(UserEntity data) {

                        view.openMainActivity();
                    }
                });
    }

    @Override
    public void start() {

    }
}
