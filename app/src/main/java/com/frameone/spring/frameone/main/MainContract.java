package com.frameone.spring.frameone.main;

import com.frameone.spring.frameone.base.BasePresenter;
import com.frameone.spring.frameone.base.BaseView;

/**
 * Created by Administrator on 2018/1/5.
 */

public interface MainContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void leaseStatus();
        void historyTrack();
    }
}
