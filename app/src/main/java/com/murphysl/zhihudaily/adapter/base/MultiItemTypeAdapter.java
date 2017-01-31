package com.murphysl.zhihudaily.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

    protected OnItemClickListener onItemClickListener;
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
        setListener(parent , viewHolder , viewType);
        return viewHolder;
    }

    public void convert(BaseViewHolder holder, T t) {
        itemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    public MultiItemTypeAdapter<T> addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        itemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, RecyclerView.ViewHolder holder,  int position);

        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    protected void setListener(final ViewGroup parent, final BaseViewHolder viewHolder, int viewType) {
        viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    onItemClickListener.onItemClick(v, viewHolder , position);
                }
            }
        });

        viewHolder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return onItemClickListener.onItemLongClick(v, viewHolder, position);
                }
                return false;
            }
        });
    }


}
