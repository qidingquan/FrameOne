package com.frameone.spring.frameone.data.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.frameone.spring.frameone.R;
import com.frameone.spring.frameone.data.entity.CategoryEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/10.
 * 手环功能分类
 */

public class BraceletFunctionAdapter extends BaseRecyclerAdapter<CategoryEntity> {


    public BraceletFunctionAdapter(Context mContext, int layoutResId, List<CategoryEntity> dataList) {
        super(mContext, layoutResId, dataList);
    }

    @Override
    protected void bindData(BaseViewHolder holder, CategoryEntity data, int position) {
        ImageView ivCategory = holder.getView(R.id.iv_category);
        TextView tvCategory = holder.getView(R.id.tv_category);
        tvCategory.setText(data.getName());
        ivCategory.setImageResource(data.getLocalImgRes());
    }
}
