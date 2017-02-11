package com.murphysl.zhihudaily.adapter.delegate;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.murphysl.zhihudaily.config.Constants;
import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.BaseViewHolder;
import com.murphysl.zhihudaily.adapter.base.ItemViewDelegate;
import com.murphysl.zhihudaily.bean.NewsBean;
import com.murphysl.zhihudaily.bean.StoriesBean;
import com.murphysl.zhihudaily.ui.detail.DetailActivity;

/**
 * StoriesDelegate
 *
 * @author: MurphySL
 * @time: 2017/1/28 22:31
 */


public class StoriesDelegate implements ItemViewDelegate<NewsBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.stories_item;
    }

    @Override
    public boolean isForViewType(@NonNull NewsBean data) {
        return data instanceof StoriesBean;
    }

    @Override
    public void convert(final BaseViewHolder viewHolder, final NewsBean storiesBean, int position) {
        viewHolder.setText(R.id.title_main , ((StoriesBean)storiesBean).getTitle());
        viewHolder.setImageUrl(R.id.img_main , ((StoriesBean)storiesBean).getImages().get(0));
        if( ((StoriesBean)storiesBean).getImages().size() > 1)
            viewHolder.setVisible(R.id.img_hint , true);


        viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = viewHolder.getItemView().getContext();
                Intent intent = new Intent(context , DetailActivity.class);
                intent.putExtra(Constants.newsId , ((StoriesBean)storiesBean).getId());
                context.startActivity(intent);
            }
        });
    }
}
