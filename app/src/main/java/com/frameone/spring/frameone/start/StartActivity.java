package com.frameone.spring.frameone.start;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.frameone.spring.frameone.R;
import com.frameone.spring.frameone.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/25.
 * 启动页面
 */

public class StartActivity extends BaseActivity implements StartContract.View {

    @BindView(R.id.view_waiting)
    LottieAnimationView viewWaiting;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_turn_to)
    TextView tvTurnTo;

    StartContract.Presenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        presenter=new StartPresenter(this);
        presenter.getAds("1","4");
    }


    @Override
    public void setPresenter(StartContract.Presenter presenter) {

    }

    @Override
    public void showAds() {

    }
}
