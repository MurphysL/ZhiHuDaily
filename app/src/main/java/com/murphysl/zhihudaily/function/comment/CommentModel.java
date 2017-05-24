package com.murphysl.zhihudaily.function.comment;

import com.murphysl.zhihudaily.data.api.Network;
import com.murphysl.zhihudaily.data.bean.LongCommentsBean;
import com.murphysl.zhihudaily.data.bean.ShortCommentsBean;
import com.murphysl.zhihudaily.util.rx.RxSchedulerHelper;

import io.reactivex.Observable;

/**
 * CommentModel
 *
 * @author: MurphySL
 * @time: 2017/2/15 11:31
 */


public class CommentModel implements CommentContract.Model{

    @Override
    public Observable<LongCommentsBean> getLongComments(int id) {
        return Network.getInstance().getCommonApi().getLongCommentsBean(id)
                .compose(RxSchedulerHelper.<LongCommentsBean>io_main());
    }

    @Override
    public Observable<ShortCommentsBean> getShortComments(int id) {
        return Network.getInstance().getCommonApi().getShortCommentsBean(id)
                .compose(RxSchedulerHelper.<ShortCommentsBean>io_main());
    }
}
