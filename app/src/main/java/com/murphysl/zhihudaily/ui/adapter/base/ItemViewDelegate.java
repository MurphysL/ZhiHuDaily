package com.murphysl.zhihudaily.ui.adapter.base;

import android.support.annotation.NonNull;

/**
 * ItemViewDelegate
 *
 * @author: MurphySL
 * @time: 2017/1/28 21:10
 */


public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(@NonNull T data );

    void convert(BaseViewHolder viewHolder , T t , int position);

}
