package com.murphysl.zhihudaily.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.bean.LatestNewsBean;
import com.murphysl.zhihudaily.mvpframe.base.BaseFragment;
import com.murphysl.zhihudaily.mvpframe.base.TestAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MainFragment
 *
 * @author: MurphySL
 * @time: 2017/1/26 11:34
 */


public class MainFragment extends BaseFragment<MainModel , MainPresenter> implements MainContract.View {
    private static final String TAG = "MainFragment";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    //List<LatestNewsBean> latestNewsBeen = new ArrayList<>();
    List<LatestNewsBean.StoriesBean> list = new ArrayList<>();
    TestAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_frag, container, false);
        recyclerview = (RecyclerView) root.findViewById(R.id.recyclerview);
        adapter = new TestAdapter(getContext() , list);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(adapter);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.getLatestNews();
    }

    @Override
    public void showLatestNews(LatestNewsBean latestNewsBean) {
        if(latestNewsBean == null)
            Log.i(TAG, "showLatestNews: ");
        Log.i(TAG, "showLatestNews: " + latestNewsBean.toString());

        for(int i = 0 ;i < latestNewsBean.getStories().size() ;i ++){
            list.add(latestNewsBean.getStories().get(i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestError(String msg) {
        Snackbar.make(getView() , msg , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestEnd() {

    }
}
