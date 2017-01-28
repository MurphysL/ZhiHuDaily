package com.murphysl.zhihudaily.adapter.base;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.util.SparseArray;


/**
 * ItemViewDelegateManager
 *
 * @author: MurphySL
 * @time: 2017/1/28 21:19
 */


public class ItemViewDelegateManager<T> {

    SparseArrayCompat<ItemViewDelegate<T>> delegates = new SparseArrayCompat<>();

    public int getDelegatesNum(){
        return delegates.size();
    }

    public ItemViewDelegateManager<T> addDelegate(@NonNull ItemViewDelegate<T> delegate){
        delegates.put(getDelegatesNum() , delegate);
        return this;
    }

    public int getItemViewType(@NonNull T data , int position){

        for(int i = 0;i < getDelegatesNum() ;i ++){
            ItemViewDelegate<T> d = delegates.get(i);
            if(d.isForViewType(data)){
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public ItemViewDelegate<T> getItemViewDelegate(int viewType){
        return delegates.get(viewType);
    }

    public void convert(BaseViewHolder viewHolder , T t , int position){
        for (int i = 0; i < getDelegatesNum(); i ++) {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType(t)) {
                delegate.convert(viewHolder, t , position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }

}
