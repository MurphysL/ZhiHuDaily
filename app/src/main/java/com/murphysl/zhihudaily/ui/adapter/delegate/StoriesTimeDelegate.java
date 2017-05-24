package com.murphysl.zhihudaily.ui.adapter.delegate;

import android.support.annotation.NonNull;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.ui.adapter.base.BaseViewHolder;
import com.murphysl.zhihudaily.ui.adapter.base.ItemViewDelegate;
import com.murphysl.zhihudaily.data.NewsBean;
import com.murphysl.zhihudaily.data.bean.TimeBean;

/**
 * StoriesTimeDelegate
 *
 * @author: MurphySL
 * @time: 2017/2/4 19:18
 */


public class StoriesTimeDelegate implements ItemViewDelegate<NewsBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.stories_time_item;
    }

    @Override
    public boolean isForViewType(@NonNull NewsBean data) {
        return data instanceof TimeBean;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, NewsBean s, int position) {
        viewHolder.setText(R.id.time , ((TimeBean)s).getDate());
    }
}
