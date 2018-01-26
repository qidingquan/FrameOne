package com.frameone.spring.frameone.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.frameone.spring.frameone.R;

/**
 * Created by Administrator on 2018/1/26.
 * 加载对话框
 */

public class LoadingDialog extends Dialog {

    public static final int LOADING = 1;//正在加载状态
    public static final int FAILTRUE = 2;//加载失败状态
    public static final int NOTNETWORK = 3;//没有网络状态
    public static final int EMPTY = 4;//没有数据状态


    private LinearLayout layout_loading;
    private ImageView ivLoad;
    private LinearLayout layout_network;
    private LinearLayout layout_empty;
    private LinearLayout layout_fail;

    private RotateAnimation rotateAnimation;
    private int state;//当前状态
    private int titleHeight = 40;//标题栏高度

    public LoadingDialog(Context context) {
        this(context, R.style.loading_dialog);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        layout_loading = view.findViewById(R.id.layout_loading);
        ivLoad = view.findViewById(R.id.iv_load);
        layout_network = view.findViewById(R.id.layout_network);
        layout_empty = view.findViewById(R.id.layout_empty);
        layout_fail = view.findViewById(R.id.layout_fail);

        setContentView(view);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
        getWindow().setGravity(Gravity.BOTTOM);
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LOADING == state) {
                    return;
                }
                show(LOADING);
                if (overLoadListener != null) {
                    overLoadListener.overLoad();
                }
            }
        });
    }

    public void setWidthHeight(int width, int height) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        if (width == WindowManager.LayoutParams.MATCH_PARENT) {
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
        } else if (width == WindowManager.LayoutParams.WRAP_CONTENT) {
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        } else {
            params.width = dp2px(width);
        }
        if (height == WindowManager.LayoutParams.MATCH_PARENT) {
            params.height = getScreenHeight()-dp2px(titleHeight);
        } else if (height == WindowManager.LayoutParams.WRAP_CONTENT) {
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        } else {
            params.height = dp2px(height);
        }

        getWindow().setAttributes(params);
    }

    private int dp2px(int dpValue) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5);
    }

    private int getStatusHeight() {
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    private int getScreenHeight() {
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.height();
    }

    private void startAnim(ImageView view) {
        rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setRepeatCount(-1);
        view.startAnimation(rotateAnimation);
    }

    /**
     * 显示对话框状态
     *
     * @param state
     */
    public void show(int state) {
        this.state = state;
        show();
        switch (state) {
            case LOADING:
                setWidthHeight(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                layout_loading.setVisibility(View.VISIBLE);
                layout_network.setVisibility(View.GONE);
                layout_empty.setVisibility(View.GONE);
                layout_fail.setVisibility(View.GONE);
                startAnim(ivLoad);
                break;
            case FAILTRUE:
                setWidthHeight(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                layout_fail.setVisibility(View.VISIBLE);
                layout_loading.setVisibility(View.GONE);
                layout_network.setVisibility(View.GONE);
                layout_empty.setVisibility(View.GONE);
                break;
            case NOTNETWORK:
                setWidthHeight(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                layout_network.setVisibility(View.VISIBLE);
                layout_loading.setVisibility(View.GONE);
                layout_empty.setVisibility(View.GONE);
                layout_fail.setVisibility(View.GONE);
                break;
            case EMPTY:
                setWidthHeight(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                layout_empty.setVisibility(View.VISIBLE);
                layout_loading.setVisibility(View.GONE);
                layout_network.setVisibility(View.GONE);
                layout_fail.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (rotateAnimation != null) {
            rotateAnimation.cancel();
        }
    }

    private OverLoadListener overLoadListener;

    public void setOverLoadListener(OverLoadListener overLoadListener) {
        this.overLoadListener = overLoadListener;
    }

    /**
     * 重新加载监听
     */
    public interface OverLoadListener {
        void overLoad();
    }
}
