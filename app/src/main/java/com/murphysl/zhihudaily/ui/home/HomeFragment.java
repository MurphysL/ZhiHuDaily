package com.murphysl.zhihudaily.ui.home;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.murphysl.zhihudaily.adapter.listener.LoadMoreListener;
import com.murphysl.zhihudaily.config.Constants;
import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.MultiItemTypeAdapter;
import com.murphysl.zhihudaily.adapter.delegate.StoriesDelegate;
import com.murphysl.zhihudaily.adapter.delegate.StoriesTimeDelegate;
import com.murphysl.zhihudaily.adapter.wrapper.HeaderAndFooterWrapper;
import com.murphysl.zhihudaily.bean.BeforeNewsBean;
import com.murphysl.zhihudaily.bean.LatestNewsBean;
import com.murphysl.zhihudaily.bean.NewsBean;
import com.murphysl.zhihudaily.bean.TimeBean;
import com.murphysl.zhihudaily.mvpframe.base.MVPFragment;
import com.murphysl.zhihudaily.ui.widget.Banner.Banner;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * HomeFragment
 *
 *
 * @author: MurphySL
 * @time: 2017/1/30 19:02
 */


public class HomeFragment extends MVPFragment<HomeModel , HomePresenter> implements HomeContract.View {

    private RecyclerView recyclerview;
    private SwipeRefreshLayout swipe;
    private Banner banner;

    private List<NewsBean> newsList = new ArrayList<>();
    private List<LatestNewsBean.TopStoriesBean> topList = new ArrayList<>();
    private List<String> img = new ArrayList<>();
    private List<String> title = new ArrayList<>();

    private MultiItemTypeAdapter<NewsBean> adapter;
    private HeaderAndFooterWrapper wrapper;

    private String date = Constants.latestTime;

    @Override
    public void showLatestNews(LatestNewsBean latestNewsBean) {
        if(latestNewsBean == null)
            return;
        Logger.i("showLatestNews: " + latestNewsBean.toString());

        TimeBean timeBean = new TimeBean();
        timeBean.setTime(date);
        newsList.add(timeBean);
        date = latestNewsBean.getDate();
        for(int i = 0 ;i < latestNewsBean.getStories().size() ;i ++){
            newsList.add(latestNewsBean.getStories().get(i));
        }
        wrapper.notifyDataSetChanged();

        for (int i = 0 ;i < latestNewsBean.getTop_stories().size() ;i ++){
            topList.add(latestNewsBean.getTop_stories().get(i));
            img.add(latestNewsBean.getTop_stories().get(i).getImage());
            title.add(latestNewsBean.getTop_stories().get(i).getTitle());
        }
        banner.update(img , title);

        swipe.setRefreshing(false);
    }

    @Override
    public void showBeforeNews(BeforeNewsBean beforeNewsBean) {
        if(beforeNewsBean == null)
            return;

        TimeBean timeBean = new TimeBean();
        timeBean.setTime(date);
        newsList.add(timeBean);
        date = beforeNewsBean.getDate();
        for(int i = 0 ;i < beforeNewsBean.getStories().size() ;i ++){
            newsList.add(beforeNewsBean.getStories().get(i));
        }
        wrapper.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
        presenter.getLatestNews();
    }

    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        adapter = new MultiItemTypeAdapter(getContext() , newsList);
        adapter.addItemViewDelegate(new StoriesDelegate());
        adapter.addItemViewDelegate(new StoriesTimeDelegate());
        wrapper = new HeaderAndFooterWrapper(adapter);

        swipe.setColorSchemeResources(R.color.colorPrimary);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsList.clear();
                topList.clear();
                img.clear();
                title.clear();
                presenter.getLatestNews();
            }
        });

        banner = new Banner(getContext());
        banner.isAutoPlay(true);
        banner.setDelayTime(6000);

        wrapper.addHeaderView(banner);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(manager);
        recyclerview.addOnScrollListener(new LoadMoreListener(manager) {
            @Override
            public void loadMore(int currentPage) {
                presenter.getBeforeNews(date);
            }
        });
        recyclerview.setAdapter(wrapper);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_main;
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onRequestError(String msg) {
        Snackbar.make(getView() , msg , Snackbar.LENGTH_SHORT).show();
        Logger.w(msg);
    }
}
