package com.murphysl.zhihudaily.ui.adapter.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * LoadMoreListener
 *
 * @author: MurphySL
 * @time: 2017/2/11 19:26
 */


public abstract class LoadMoreListener extends RecyclerView.OnScrollListener {

    public static final int LOAD_MORE_NUM = 3;

    private boolean loading = true;
    private int currentPage = 1;
    private LinearLayoutManager linearLayoutManager;
    private int firstVisibleItem, visibleItemCount, totalItemCount , lastVisibleItem;

    public LoadMoreListener(LinearLayoutManager linearLayoutManager){
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount  = recyclerView.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItem  = linearLayoutManager.findFirstVisibleItemPosition();
        lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

        if(!loading && visibleItemCount + firstVisibleItem >= totalItemCount-3){
            currentPage ++;
            loadMore(currentPage);
        }else{
            loading = false;
        }
    }

    public abstract void loadMore(int currentPage);
}

