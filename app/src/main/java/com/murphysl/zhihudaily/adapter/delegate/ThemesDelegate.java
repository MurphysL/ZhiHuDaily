package com.murphysl.zhihudaily.adapter.delegate;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.BaseViewHolder;
import com.murphysl.zhihudaily.adapter.base.ItemViewDelegate;
import com.murphysl.zhihudaily.bean.ThemesBean;

/**
 * ThemesDelegate
 *
 * @author: MurphySL
 * @time: 2017/1/29 16:08
 */


public class ThemesDelegate implements ItemViewDelegate<ThemesBean.OthersBean> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.drawer_menu_item;
    }

    @Override
    public boolean isForViewType(@NonNull ThemesBean.OthersBean data) {
        return data instanceof ThemesBean.OthersBean;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, ThemesBean.OthersBean othersBean, int position) {
        viewHolder.setText(R.id.title_drawer , othersBean.getName());
    }
}
