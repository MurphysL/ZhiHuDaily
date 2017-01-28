package com.murphysl.zhihudaily.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * MultiItemTypeAdapter
 *
 * @author: MurphySL
 * @time: 2017/1/28 20:06
 */


public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context context;
    protected List<T> data;

    protected ItemViewDelegateManager<T> itemViewDelegateManager;

    public MultiItemTypeAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;
        itemViewDelegateManager = new ItemViewDelegateManager<>();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(itemViewDelegateManager.getDelegatesNum() == 0)
            return super.getItemViewType(position);
        else
            return itemViewDelegateManager.getItemViewType(data.get(position) , position);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, data.get(position));
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewDelegate<T> itemViewDelegate = itemViewDelegateManager.getItemViewDelegate(viewType);
        BaseViewHolder viewHolder = BaseViewHolder.createViewHolder(context , parent ,
                itemViewDelegate.getItemViewLayoutId());
        return viewHolder;
    }

    public void convert(BaseViewHolder holder, T t) {
        itemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    public MultiItemTypeAdapter<T> addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        itemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }
}
