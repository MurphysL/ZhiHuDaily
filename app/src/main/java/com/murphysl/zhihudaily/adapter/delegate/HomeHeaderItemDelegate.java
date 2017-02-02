package com.murphysl.zhihudaily.adapter.delegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.BaseViewHolder;
import com.murphysl.zhihudaily.adapter.base.ItemViewDelegate;
import com.murphysl.zhihudaily.bean.LatestNewsBean;
import com.murphysl.zhihudaily.ui.detail.DetailActivity;
import com.murphysl.zhihudaily.ui.widget.Banner.Banner;


import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */

public class HomeHeaderItemDelegate implements ItemViewDelegate<LatestNewsBean.TopStoriesBean>, Banner.OnBannerClickListener {

    private static final String TAG = "HomeHeaderItemDelegate";

    private Context mContext;

    private List<Integer> mIds;


    @Override
    public int getItemViewLayoutId() {
        return R.layout.home_header;
    }

    @Override
    public boolean isForViewType(@NonNull LatestNewsBean.TopStoriesBean data) {
        return data instanceof LatestNewsBean.TopStoriesBean;
    }

    @Override
    public void convert(BaseViewHolder holder, LatestNewsBean.TopStoriesBean topStoriesBean, int position) {
//        mContext = holder.getItemView().getContext();
//        Banner banner = holder.getView(R.id.banner);
//        HomeHeaderItem item = (HomeHeaderItem) displaybleItem;
//        mIds = item.getIds();
//        banner.setImages(item.getImages())
//                .setBannerTitles(item.getTitles())
//                .setImageLoader(GlideImageLoader.getInstance())
//                .setOnBannerClickListener(this)
//                .start();
    }

    @Override
    public void OnBannerClick(int position) {
        if (mIds == null) {
            Log.e(TAG, "error : id列表为空！");
            return;
        }
        Intent intent = new Intent(mContext, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ID", mIds.get(position));
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

}
