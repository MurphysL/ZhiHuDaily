package com.murphysl.zhihudaily.adapter.delegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.murphysl.zhihudaily.Constants;
import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.BaseViewHolder;
import com.murphysl.zhihudaily.adapter.base.ItemViewDelegate;
import com.murphysl.zhihudaily.bean.LatestNewsBean;
import com.murphysl.zhihudaily.ui.detail.DetailActivity;

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
    public void convert(final BaseViewHolder viewHolder, final LatestNewsBean.StoriesBean storiesBean, int position) {
        viewHolder.setText(R.id.title_main , storiesBean.getTitle());
        viewHolder.setImageUrl(R.id.img_main , storiesBean.getImages().get(0));

        viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = viewHolder.getItemView().getContext();
                Intent intent = new Intent(context , DetailActivity.class);
                intent.putExtra(Constants.newsId , storiesBean.getId());
                context.startActivity(intent);
            }
        });
    }
}
