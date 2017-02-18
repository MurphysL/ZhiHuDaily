package com.murphysl.zhihudaily.ui.theme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.MultiItemTypeAdapter;
import com.murphysl.zhihudaily.adapter.delegate.EditorsDelegate;
import com.murphysl.zhihudaily.adapter.delegate.ThemeNewsDelegate;
import com.murphysl.zhihudaily.adapter.wrapper.HeaderAndFooterWrapper;
import com.murphysl.zhihudaily.bean.NewsBean;
import com.murphysl.zhihudaily.bean.ThemeNewsBean;
import com.murphysl.zhihudaily.config.Constants;
import com.murphysl.zhihudaily.mvpframe.base.BaseMVPFragment;
import com.murphysl.zhihudaily.ui.detail.DetailActivity;
import com.murphysl.zhihudaily.ui.widget.Banner;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * ThemeFragment
 *
 * @author: MurphySL
 * @time: 2017/2/6 15:54
 */


public class ThemeFragment extends BaseMVPFragment<ThemeModel , ThemePresenter> implements ThemeContract.View{

    private RecyclerView recyclerview;
    private SwipeRefreshLayout swipe;
    private Banner banner;

    private List<NewsBean> newsList = new ArrayList<>();
    private List<String> imgs = new ArrayList<>();
    private List<String> title = new ArrayList<>();

    private MultiItemTypeAdapter<NewsBean> adapter;
    private HeaderAndFooterWrapper wrapper;

    public int themeId = 0;
    @Override
    protected void initData() {
        presenter.getThemeNews(themeId);
    }

    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swipe.setColorSchemeResources(R.color.colorPrimary);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsList.clear();
                imgs.clear();
                title.clear();
                presenter.getThemeNews(themeId);
            }
        });

        adapter = new MultiItemTypeAdapter(getContext() , newsList);
        adapter.addItemViewDelegate(new ThemeNewsDelegate());
        adapter.addItemViewDelegate(new EditorsDelegate());
        wrapper = new HeaderAndFooterWrapper(adapter);

        banner = new Banner(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , 650);
        banner.setLayoutParams(params);

        wrapper.addHeaderView(banner);

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(wrapper);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_theme;
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onRequestError(String msg) {
        Snackbar.make(recyclerview , msg , Snackbar.LENGTH_SHORT).show();
        Logger.w(msg);
    }

    @Override
    public void showThemeNews(ThemeNewsBean themeNewsBean) {
        if(themeNewsBean == null)
            return;

        Logger.i("showLatestNews: " + themeNewsBean.toString());

        imgs.add(themeNewsBean.getImage());
        title.add(themeNewsBean.getName());

        banner.update(imgs , title);

        newsList.add(themeNewsBean);

        for(int i = 0 ;i < themeNewsBean.getStories().size() ;i ++)
            newsList.add(themeNewsBean.getStories().get(i));

        wrapper.notifyDataSetChanged();

    }
}
