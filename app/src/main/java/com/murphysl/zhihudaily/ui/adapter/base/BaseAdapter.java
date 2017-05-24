package com.murphysl.zhihudaily.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.List;


/**
 * BaseAdapter
 *
 * @author: MurphySL
 * @time: 2017/1/28 14:49
 */


public abstract class BaseAdapter <T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context context;
    protected int layoutId;
    protected List<T> data;
    protected LayoutInflater layoutInflater;

    public BaseAdapter(Context context, int layoutId, List<T> data) {
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = BaseViewHolder.createViewHolder(context , parent , layoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder , data.get(position));
    }

    public abstract void convert(BaseViewHolder viewHolder, T t);

    @Override
    public int getItemCount() {
        return data.size();
    }
}
