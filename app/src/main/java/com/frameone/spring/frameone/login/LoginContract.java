package com.frameone.spring.frameone.login;

import com.frameone.spring.frameone.base.BasePresenter;
import com.frameone.spring.frameone.base.BaseView;

/**
 * Created by Administrator on 2018/1/5.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        String getPhone();

        String getCode();

        void openMainActivity();
    }

    interface Presenter extends BasePresenter {
        void login();
    }
}
