package com.frameone.spring.frameone.start;

import com.frameone.spring.frameone.base.BasePresenter;
import com.frameone.spring.frameone.base.BaseView;

/**
 * Created by Administrator on 2017/12/26.
 */

public interface StartContract {

    interface View extends BaseView<Presenter> {
        void showAds();
    }

    interface Presenter extends BasePresenter {
        void getAds(String location_id, String dev_type);
    }
}
