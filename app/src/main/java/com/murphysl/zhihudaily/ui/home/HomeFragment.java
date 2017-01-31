package com.murphysl.zhihudaily.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.murphysl.zhihudaily.Constants;
import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.MultiItemTypeAdapter;
import com.murphysl.zhihudaily.adapter.delegate.LatestNewsStoriesDelegate;
import com.murphysl.zhihudaily.bean.LatestNewsBean;
import com.murphysl.zhihudaily.mvpframe.base.MVPFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * HomeFragment
 *
 * @author: MurphySL
 * @time: 2017/1/30 19:02
 */


public class HomeFragment extends MVPFragment<HomeModel , HomePresenter> implements HomeContract.View {
    private static final String TAG = "HomeFragment";

    private RecyclerView recyclerview;
    private SwipeRefreshLayout swipe;

    private List<LatestNewsBean.StoriesBean> list = new ArrayList<>();
    private MultiItemTypeAdapter<LatestNewsBean.StoriesBean> adapter;

    @Override
    public void showLatestNews(LatestNewsBean latestNewsBean) {
        if(latestNewsBean != null)
            Log.i(TAG, "showLatestNews: ");
        Log.i(TAG, "showLatestNews: " + latestNewsBean.toString());

        for(int i = 0 ;i < latestNewsBean.getStories().size() ;i ++){
            list.add(latestNewsBean.getStories().get(i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
        presenter.getLatestNews();
    }

    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        adapter = new MultiItemTypeAdapter(getContext() , list);
        adapter.addItemViewDelegate(new LatestNewsStoriesDelegate());
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(adapter);
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
    }
}
