package com.murphysl.zhihudaily.adapter.wrapper;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.murphysl.zhihudaily.adapter.base.BaseViewHolder;


/**
 * HeaderAndFooterWrapper
 *
 * @author: MurphySL
 * @time: 2017/1/28 23:33
 */


public class HeaderAndFooterWrapper extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int HEADER_ITEM_TYPE = 100000;
    private static final int FOOTER_ITEM_TYPE = 200000;

    private RecyclerView.Adapter adapter;

    private SparseArrayCompat<View> headers;
    private SparseArrayCompat<View> footers;

    public HeaderAndFooterWrapper(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        headers = new SparseArrayCompat<>();
        footers = new SparseArrayCompat<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return headers.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return footers.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return adapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headers.get(viewType) != null) {
            BaseViewHolder holder = BaseViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
            return holder;
        } else if (footers.get(viewType) != null) {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mFootViews.get(viewType));
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private int getRealItemCount() {
        return adapter.getItemCount();
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }


    public void addHeaderView(View view)
    {
        headers.put(headers.size() + HEADER_ITEM_TYPE, view);
    }

    public void addFootView(View view)
    {
        footers.put(footers.size() + FOOTER_ITEM_TYPE, view);
    }

    public int getHeadersCount()
    {
        return headers.size();
    }

    public int getFootersCount()
    {
        return footers.size();
    }
}
