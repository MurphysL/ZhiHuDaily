package com.murphysl.zhihudaily.adapter.delegate;

import android.app.Activity;
import android.app.ActivityOptions;
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
 * ThemeNewsDelegate
 *
 * @author: MurphySL
 * @time: 2017/2/6 17:28
 */


public class ThemeNewsDelegate implements ItemViewDelegate<NewsBean> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.stories_item;
    }

    @Override
    public boolean isForViewType(@NonNull NewsBean data) {
        return data instanceof StoriesBean;
    }

    @Override
    public void convert(final BaseViewHolder viewHolder, final NewsBean newsBean, int position) {
        viewHolder.setText(R.id.title_main , ((StoriesBean)newsBean).getTitle());

        if( ((StoriesBean)newsBean).getImages() == null)
            viewHolder.setVisible(R.id.img , false);
        else
            viewHolder.setImageUrl(R.id.img_main , ((StoriesBean)newsBean).getImages().get(0));


        viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = viewHolder.getItemView().getContext();
                Intent intent = new Intent(context , DetailActivity.class);
                intent.putExtra(Constants.NEWS_ID, ((StoriesBean)newsBean).getId());
                context.startActivity(intent ,
                        ActivityOptions.makeSceneTransitionAnimation((Activity)context ,viewHolder.getView(R.id.img) ,  "transitionImg").toBundle());
            }
        });
    }
}
