package com.murphysl.zhihudaily.ui.adapter.delegate;

import android.support.annotation.NonNull;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.ui.adapter.base.BaseViewHolder;
import com.murphysl.zhihudaily.ui.adapter.base.ItemViewDelegate;
import com.murphysl.zhihudaily.data.bean.CommentsBean;

/**
 * CommentsDelegate
 *
 * @author: MurphySL
 * @time: 2017/2/15 22:21
 */


public class CommentsDelegate implements ItemViewDelegate<CommentsBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.comment_item;
    }

    @Override
    public boolean isForViewType(@NonNull CommentsBean data) {
        return data instanceof CommentsBean;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, CommentsBean commentsBean, int position) {
        viewHolder.setImageUrl(R.id.comment_avatar, commentsBean.getAvatar());
        viewHolder.setText(R.id.comment_author, commentsBean.getAuthor());
        viewHolder.setTextBold(R.id.comment_author , true);
        viewHolder.setText(R.id.comment_like, commentsBean.getLikes() + "");
        viewHolder.setText(R.id.comment_content, commentsBean.getContent());
        viewHolder.setText(R.id.comment_time , commentsBean.getTime());
    }
}
