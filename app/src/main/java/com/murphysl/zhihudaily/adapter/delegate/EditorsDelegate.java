package com.murphysl.zhihudaily.adapter.delegate;

import android.support.annotation.NonNull;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.BaseViewHolder;
import com.murphysl.zhihudaily.adapter.base.ItemViewDelegate;
import com.murphysl.zhihudaily.bean.NewsBean;
import com.murphysl.zhihudaily.bean.ThemeNewsBean;

/**
 * EditorsDelegate
 *
 * @author: MurphySL
 * @time: 2017/2/6 19:56
 */


public class EditorsDelegate implements ItemViewDelegate<NewsBean> {

    private int[] avatars = {
            R.id.editor_avatar1,R.id.editor_avatar2,R.id.editor_avatar3,R.id.editor_avatar4,
            R.id.editor_avatar5,R.id.editor_avatar6,R.id.editor_avatar7,R.id.editor_avatar8,
    };
    @Override
    public int getItemViewLayoutId() {
        return R.layout.editor_item;
    }

    @Override
    public boolean isForViewType(@NonNull NewsBean data) {
        return data  instanceof ThemeNewsBean;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, NewsBean editorsBean, int position) {

        for(int i = 0 ;i <((ThemeNewsBean)editorsBean).getEditors().size() ;i ++){
            viewHolder.setVisible(avatars[i] , true);
            viewHolder.setImageUrl(avatars[i] , ((ThemeNewsBean)editorsBean).getEditors().get(i).getAvatar());
        }
    }
}
