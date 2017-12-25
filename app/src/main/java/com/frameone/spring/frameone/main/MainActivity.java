package com.frameone.spring.frameone.main;

import android.os.Bundle;
import android.widget.TextView;

import com.frameone.spring.frameone.R;
import com.frameone.spring.frameone.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_show)
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        tvShow.setText("你好吗");
    }
}
