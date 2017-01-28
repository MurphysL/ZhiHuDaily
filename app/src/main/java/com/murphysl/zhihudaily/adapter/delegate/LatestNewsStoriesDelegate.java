package com.murphysl.zhihudaily.adapter.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.BaseViewHolder;
import com.murphysl.zhihudaily.adapter.base.ItemViewDelegate;
import com.murphysl.zhihudaily.bean.LatestNewsBean;

/**
 * LatestNewsStoriesDelegate
 *
 * @author: MurphySL
 * @time: 2017/1/28 22:31
 */


public class LatestNewsStoriesDelegate implements ItemViewDelegate<LatestNewsBean.StoriesBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.main_content_item;
    }

    @Override
    public boolean isForViewType(@NonNull LatestNewsBean.StoriesBean data) {
        return data instanceof LatestNewsBean.StoriesBean;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, LatestNewsBean.StoriesBean storiesBean, int position) {
        viewHolder.setText(R.id.title_main , storiesBean.getTitle());
        viewHolder.setImageUrl(R.id.img_main , storiesBean.getImages().get(0));
    }
}
