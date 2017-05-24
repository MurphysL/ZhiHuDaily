package com.murphysl.zhihudaily.function.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.murphysl.zhihudaily.ui.adapter.listener.LoadMoreListener;
import com.murphysl.zhihudaily.util.config.Constants;
import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.ui.adapter.base.MultiItemTypeAdapter;
import com.murphysl.zhihudaily.ui.adapter.delegate.StoriesDelegate;
import com.murphysl.zhihudaily.ui.adapter.delegate.StoriesTimeDelegate;
import com.murphysl.zhihudaily.ui.adapter.wrapper.HeaderAndFooterWrapper;
import com.murphysl.zhihudaily.data.news.BeforeNewsBean;
import com.murphysl.zhihudaily.data.news.LatestNewsBean;
import com.murphysl.zhihudaily.data.NewsBean;
import com.murphysl.zhihudaily.data.bean.TimeBean;
import com.murphysl.zhihudaily.base.mvp.BaseMVPFragment;

import com.murphysl.zhihudaily.function.detail.DetailActivity;
import com.murphysl.zhihudaily.ui.widget.Banner;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * HomeFragment
 *
 * popThemeFragment 导致内容重复：目前先清空list，待改进
 * 多次刷新导致无法加载以前新闻
 *
 * author: MurphySL
 * time: 2017/1/30 19:02
 */


public class HomeFragment extends BaseMVPFragment<HomeModel , HomePresenter> implements HomeContract.View {

    private RecyclerView recyclerview;
    private SwipeRefreshLayout swipe;
    private Banner banner;

    private List<NewsBean> newsList = new ArrayList<>();
    private List<LatestNewsBean.TopStoriesBean> topList = new ArrayList<>();
    private List<String> img = new ArrayList<>();
    private List<String> title = new ArrayList<>();

    private MultiItemTypeAdapter<NewsBean> adapter;
    private HeaderAndFooterWrapper wrapper;

    private String date = Constants.LATEST_TIME;
    private String newDate;

    @Override
    public void showLatestNews(LatestNewsBean latestNewsBean) {
        if(latestNewsBean == null)
            return;
        Logger.i("showLatestNews: " + latestNewsBean.toString());

        newsList.clear();
        title.clear();
        img.clear();
        topList.clear();
        
        TimeBean timeBean = new TimeBean();
        timeBean.setTime(date);
        newsList.add(timeBean);
        newDate = date;
        date = latestNewsBean.getDate();
        for(int i = 0 ;i < latestNewsBean.getStories().size() ;i ++){
            newsList.add(latestNewsBean.getStories().get(i));
            //NewsDBManager.getDataBase(getContext()).insertStorie(latestNewsBean.getStories().get(i));
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
        Logger.i(beforeNewsBean.toString());

        date = beforeNewsBean.getDate();
        TimeBean timeBean = new TimeBean();
        timeBean.setTime(date);
        newsList.add(timeBean);
        for(int i = 0 ;i < beforeNewsBean.getStories().size() ;i ++)
            newsList.add(beforeNewsBean.getStories().get(i));

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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , 650);
        banner.setLayoutParams(params);
        banner.setOnBannerClickListenr(new Banner.OnBannerClickListenr() {
            @Override
            public void onClick(int i) {
                Intent intent = new Intent();
                intent.setClass(getActivity() , DetailActivity.class);
                int id = topList.get(i).getId();
                intent.putExtra(Constants.NEWS_ID , id);
                startActivity(intent);
            }
        });

        wrapper.addHeaderView(banner);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(manager);
        recyclerview.addOnScrollListener(new LoadMoreListener(manager) {
            @Override
            public void loadMore(int currentPage) {
                Logger.i(date + " " + newDate);
                if(! newDate.equals(date)){
                    newDate = date;
                    presenter.getBeforeNews(date);
                }

            }
        });
        recyclerview.setAdapter(wrapper);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
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
